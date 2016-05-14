package dk.feb.ddd.infrastructure;

import javax.ejb.EJBException;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static dk.feb.ddd.infrastructure.ErrorMessages.Type.*;
import static dk.feb.ddd.infrastructure.ErrorMessages.createErrorObject;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException ex) {
        Throwable cause = ex.getCause();
        if (cause == null) {
            Response unknownError = Response.serverError().entity(createErrorObject(
                    UNKNOWN_ERROR, "Unexpected error", ex.getMessage()
            )).build();
            return unknownError;
        }
        if (cause instanceof ConstraintViolationException) {
            return handleConstraintViolationError((ConstraintViolationException) cause);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private Response handleConstraintViolationError(ConstraintViolationException cause) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (ConstraintViolation<?> constraintViolation : cause.getConstraintViolations()) {
            Class<?> rootBeanClass = constraintViolation.getRootBeanClass();
            String field = constraintViolation.getPropertyPath().toString();
            Object invalidValue = constraintViolation.getInvalidValue();
            String message = rootBeanClass.getSimpleName() + "." + field + " = " + invalidValue;
            String detailedMessage =
                    "A constraint was violated in class " +
                            constraintViolation.getRootBeanClass().getName() +
                            " on field " + field + ". The constraint was " + field + " "
                            + constraintViolation.getMessage();
            arrayBuilder.add(createErrorObject(VALIDATION_CONSTRAINT, message, detailedMessage));
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(arrayBuilder.build())
                .build();
    }

}