package dk.feb.ddd.domain.model.car;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static dk.feb.ddd.domain.common.validation.Validator.validate;

@Disabled
class TopSpeedTest {

    @Test
    public void topSpeedCanMaxBe300() {
        validate(new TopSpeed(300));
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        validate(new TopSpeed(301));
    }

    @Test
    public void topSpeedCanNotBeNegative() {
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        validate(new TopSpeed(-1));
    }

    @Test
    public void topSpeedCanNotBeNull() {
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        validate(new TopSpeed(null));
    }

}