package dk.feb.ddd.domain.model.car;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static dk.feb.ddd.domain.common.validation.Validator.validate;

@Disabled
class ColorTest {


    @Test
    public void colorCanNotBeNull() {
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        validate(new Color(null, Boolean.FALSE));
    }

    @Test
    public void metalCanNotBeNull() {
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        validate(new Color("Red", null));
    }

    @Test
    public void colorMustBe3Characters() {
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        validate(new Color("RR", Boolean.FALSE));
    }

    @Test
    public void colorCanNotBeMoreThan10Characters() {
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        validate(new Color("My favourite color red", Boolean.FALSE));
    }

}