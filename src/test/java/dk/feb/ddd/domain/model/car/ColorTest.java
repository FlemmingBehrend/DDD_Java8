package dk.feb.ddd.domain.model.car;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static dk.feb.ddd.domain.common.validation.Validator.validate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.expectThrows;

@DisplayName("A color")
class ColorTest {

    @Nested
    @DisplayName("when created")
    class Creation {

        @Test
        @DisplayName("color attribute can not be null")
        public void colorCanNotBeNull() {
            expectThrows(
                    ConstraintViolationException.class, () -> validate(new Color(null, Boolean.FALSE))
            )
                    .getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("can not be null", constraintViolation.getMessage())
            );
        }

        @Test
        @DisplayName("metal attribute can not be null")
        public void metalCanNotBeNull() {
            expectThrows(
                    ConstraintViolationException.class, () -> validate(new Color("Red", null))
            )
                    .getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("can not be null", constraintViolation.getMessage())
            );
        }

        @Test
        @DisplayName("color attribute must be minimum 3 characters")
        public void colorMustBe3Characters() {
            expectThrows(
                    ConstraintViolationException.class, () -> validate(new Color("RR", Boolean.FALSE))
            )
                    .getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("must be between 3 and 10 characters", constraintViolation.getMessage())
            );
        }

        @Test
        @DisplayName("color attribute must be maximum 10 characters")
        public void colorCanNotBeMoreThan10Characters() {
            expectThrows(
                    ConstraintViolationException.class, () -> validate(new Color("My favourite color red", Boolean.FALSE))
            )
                    .getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("must be between 3 and 10 characters", constraintViolation.getMessage())
            );
        }

    }

}