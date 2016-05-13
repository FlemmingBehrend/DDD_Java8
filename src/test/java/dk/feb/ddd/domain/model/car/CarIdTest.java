package dk.feb.ddd.domain.model.car;

import dk.feb.ddd.test.util.BaseTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ConstraintViolationException;

import static dk.feb.ddd.domain.common.validation.Validator.validate;
import static dk.feb.ddd.test.util.NumberOfConstraintViolationMatcher.numberOfConstraints;

public class CarIdTest extends BaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void idCanNotBeNull() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new CarId(null));
    }

    @Test
    public void idMustBe7Characters() {
        validate(new CarId("1234567"));
    }

    @Test
    public void idCanNotBe6Characters() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new CarId("123456"));
    }

    @Test
    public void idCanNotBe8Characters() {
        exception.expect(ConstraintViolationException.class);
        exception.expect(numberOfConstraints(1));
        validate(new CarId("12345678"));
    }

    @Test
    public void twoCarIdWithSameValueAreEqual() {
        CarId carId1 = new CarId("1234567");
        CarId carId2 = new CarId("1234567");
        assertEquals(carId1.getValue(), carId2.getValue());
        assertTrue(carId1.equals(carId2));
        assertTrue(carId1.sameValueAs(carId2));
    }

}