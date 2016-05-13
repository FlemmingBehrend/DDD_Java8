package dk.feb.ddd.domain.model.car;

import dk.feb.ddd.test.util.BaseTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ConstraintViolationException;

import static dk.feb.ddd.domain.common.validation.Validator.validate;
import static dk.feb.ddd.test.util.NumberOfConstraintViolationMatcher.numberOfConstraints;

public class TopSpeedTest extends BaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void topSpeedCanMaxBe300() {
        new TopSpeed(300);
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new TopSpeed(301));
    }

    @Test
    public void topSpeedCanNotBeNegative() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new TopSpeed(-1));
    }

    @Test
    public void topSpeedCanNotBeNull() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new TopSpeed(null));
    }

}