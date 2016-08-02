package dk.feb.ddd.domain.model.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static dk.feb.ddd.domain.common.validation.Validator.validate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.expectThrows;

@DisplayName("A TopSpeed")
class TopSpeedTest {

    @Test
    @DisplayName("can max be 300")
    public void topSpeedCanMaxBe300() {
        TopSpeed topSpeed = new TopSpeed(300);
        validate(topSpeed);
        assertEquals(300, topSpeed.getValue().intValue());
        expectThrows(
                ConstraintViolationException.class, () -> validate(new TopSpeed(301))
        )
                .getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("must be less than or equal to 300", constraintViolation.getMessage())
        );
    }

    @Test
    @DisplayName("can not be negative")
    public void topSpeedCanNotBeNegative() {
        expectThrows(
                ConstraintViolationException.class, () -> validate(new TopSpeed(-1))
        )
                .getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("must be greater than or equal to 0", constraintViolation.getMessage())
        );
    }

    @Test
    @DisplayName("can not be null")
    public void topSpeedCanNotBeNull() {
        expectThrows(
                ConstraintViolationException.class, () -> validate(new TopSpeed(null))
        )
                .getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("may not be null", constraintViolation.getMessage())
        );
    }

}