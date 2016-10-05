package dk.feb.ddd.domain.common.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Validator {

    static javax.validation.Validator INSTANCE;

    public static <T> T validate(T o) {
        Set<ConstraintViolation<T>> validate = getValidatorInstance().validate(o);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
        return o;
    }

    public static <T> T validateProperty(T o, String field) {
        Set<ConstraintViolation<T>> validate = getValidatorInstance().validateProperty(o, field);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
        return o;
    }

    private static javax.validation.Validator getValidatorInstance() {
        if (INSTANCE == null) {
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            INSTANCE = validatorFactory.getValidator();
        }
        return INSTANCE;
    }

}