package dk.feb.ddd.domain.model.car;

import dk.feb.ddd.test.util.BaseTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ConstraintViolationException;

import static dk.feb.ddd.domain.common.validation.Validator.validate;
import static dk.feb.ddd.test.util.NumberOfConstraintViolationMatcher.numberOfConstraints;

public class ColorTest extends BaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void colorCanNotBeNull() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new Color(null, Boolean.FALSE));
    }

    @Test
    public void metalCanNotBeNull() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new Color("Red", null));
    }

    @Test
    public void colorMustBe3Characters() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new Color("RR", Boolean.FALSE));
    }

    @Test
    public void colorCanNotBeMoreThan10Characters() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new Color("My favourite color red", Boolean.FALSE));
    }

}