package dk.feb.ddd.domain.model.car;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

@Disabled
class CarTest {


    @Test
    public void weCanBuildAnInvalidCarWhenWeDoNotValidate() {
        new Car.Factory(null, null)
                .withColor("AW", Boolean.FALSE)
                .withPrice(new BigDecimal(100000))
                .withTopSpeed(301)
                .buildWithoutValidation();
    }

    @Test
    public void weCanNotBuildAnInvalidCarWhenWeValidate() {
          // Expecting 5 validation errors when creating car with 5 errors
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(5));
        new Car.Factory(new CarId("123"), Brand.FORD)
                .withTopSpeed(null)
                .withColor("AA", null)
                .withPrice(BigDecimal.valueOf(-2))
                .build();
    }

    @Test
    public void weCanBuildCarWithValidationIfAllValuesAreValid() {
        Car car = new Car.Factory(new CarId("1234567"), Brand.MAZDA)
                .withColor("Blue", Boolean.TRUE)
                .withPrice(BigDecimal.valueOf(259000.00))
                .withTopSpeed(220)
                .build();

        // Expecting that all values have been set on aggregate
//        assertEquals(new CarId("1234567"), car.getId());
//        assertEquals(Brand.MAZDA, car.getBrand());
//        assertEquals(new TopSpeed(220), car.getTopSpeed().get());
//        assertEquals(BigDecimal.valueOf(259000.00), car.getPrice().get());
//        assertEquals("Blue", car.getColor().get().getValue());
    }



    @Test
    public void weCanChangeStateOnCarEntityAfterItIsCreated() {
        Car car = new Car.Factory(new CarId("1234567"), Brand.VOLVO)
                .withPrice(BigDecimal.TEN)
                .build();
//        assertEquals(Brand.VOLVO, car.getBrand());
        car.setBrand(Brand.MAZDA);
//        assertEquals(Brand.MAZDA, car.getBrand());
    }

    @Test
    public void beanValidationIsCheckedWhenSetterMethodsAreCalled() {
        Car car = new Car.Factory(new CarId("1234567"), Brand.VOLVO)
                .withPrice(BigDecimal.TEN)
                .build();

        // Expecting setting of invalid value
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        car.setPrice(BigDecimal.valueOf(-1));

        // Expecting setting of invalid value
//        exception.expect(ConstraintViolationException.class);
//        exception.expect(numberOfConstraints(1));
        car.setBrand(null);

        // Expecting no validation errors when setting valid valud
        car.setTopSpeed(new TopSpeed(300));
    }

}