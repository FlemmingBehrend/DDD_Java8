package dk.feb.ddd.domain.model.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static dk.feb.ddd.domain.common.validation.Validator.validate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.expectThrows;

@DisplayName("CarIds")
public class CarIdTest {

    @Test
    @DisplayName("can not be constructed with null")
    void idCanNotBeNull() {
        expectThrows(
                ConstraintViolationException.class, () -> validate(new CarId(null))
        )
                .getConstraintViolations().stream().forEach(cv -> assertEquals("may not be null", cv.getMessage())
        );
    }

    @Test
    @DisplayName("is valid if they have a length of 7")
    public void idMustBe7Characters() {
        CarId carId = new CarId("1234567");
        validate(carId);
        assertEquals(7, carId.getValue().length());
    }

    @Test
    @DisplayName("is not valid if it has a length of 6")
    public void idCanNotBe6Characters() {
        expectThrows(
                ConstraintViolationException.class, () -> validate(new CarId("123456"))
        )
                .getConstraintViolations().stream().forEach(violation -> assertEquals("must have a length of 7", violation.getMessage())
        );
    }

    @Test
    @DisplayName("is not valid if it has a length of 8")
    public void idCanNotBe8Characters() {
        expectThrows(
                ConstraintViolationException.class, () -> validate(new CarId("12345678"))
        )
                .getConstraintViolations().stream().forEach(violation -> assertEquals("must have a length of 7", violation.getMessage())
        );
    }

    @Test
    @DisplayName("with the same id is considered equal")
    public void twoCarIdWithSameValueAreEqual() {
        CarId carId1 = new CarId("1234567");
        CarId carId2 = new CarId("1234567");
        assertEquals(carId1.getValue(), carId2.getValue());
        assertTrue(carId1.equals(carId2));
        assertTrue(carId1.sameValueAs(carId2));
    }

}