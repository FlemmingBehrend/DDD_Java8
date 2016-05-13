package dk.feb.ddd.domain.common.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Validator {

    static javax.validation.Validator INSTANCE;

    public static <T> void validate(T o) {
        Set<ConstraintViolation<T>> validate = getValidatorInstance().validate(o);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

    public static <T> void validateProperty(T o, String field) {
        Set<ConstraintViolation<T>> validate = getValidatorInstance().validateProperty(o, field);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

    private static javax.validation.Validator getValidatorInstance() {
        if (INSTANCE == null) {
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            INSTANCE = validatorFactory.getValidator();
        }
        return INSTANCE;
    }

}