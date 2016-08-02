package dk.feb.ddd.domain.model.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A car")
class CarTest {

    @Nested
    @DisplayName("when created")
    class Creation {

        @Test
        @DisplayName("can be build as an invalid instance if we do not validate")
        void weCanBuildAnInvalidCarWhenWeDoNotValidate() {
            assertNotNull(
                    new Car.Factory(null, null)
                            .withColor("AW", Boolean.FALSE)
                            .withPrice(new BigDecimal(100000))
                            .withTopSpeed(301)
                            .buildWithoutValidation()
            );
        }

        @Test
        @DisplayName("can not be build as an invalid instance if we validate")
        void weCanNotBuildAnInvalidCarWhenWeValidate() {
            ConstraintViolationException cve = expectThrows(
                    ConstraintViolationException.class, () -> {
                        new Car.Factory(new CarId("123"), Brand.FORD)
                                .withTopSpeed(null)
                                .withColor("AA", null)
                                .withPrice(BigDecimal.valueOf(-2))
                                .build();
                    });
            assertEquals(5, cve.getConstraintViolations().stream().count());
        }

        @Test
        @DisplayName("can build as a valid instance if all values are valid")
        public void weCanBuildCarWithValidationIfAllValuesAreValid() {
            Car car = new Car.Factory(new CarId("1234567"), Brand.MAZDA)
                    .withColor("Blue", Boolean.TRUE)
                    .withPrice(BigDecimal.valueOf(259000.00))
                    .withTopSpeed(220)
                    .build();

            assertEquals(new CarId("1234567"), car.getId());
            assertEquals(Brand.MAZDA, car.getBrand());
            assertEquals(new TopSpeed(220), car.getTopSpeed().get());
            assertEquals(BigDecimal.valueOf(259000.00), car.getPrice().get());
            assertEquals("Blue", car.getColor().get().getValue());
        }

        @Nested
        @DisplayName("use it's set methods")
        class ChangeState {

            @Test
            @DisplayName("to change state")
            public void weCanChangeStateOnCarEntityAfterItIsCreated() {
                Car car = new Car.Factory(new CarId("1234567"), Brand.VOLVO)
                        .withPrice(BigDecimal.TEN)
                        .build();
                assertEquals(Brand.VOLVO, car.getBrand());
                car.setBrand(Brand.MAZDA);
                assertEquals(Brand.MAZDA, car.getBrand());
            }

            @Test
            @DisplayName("to validate with bean validation")
            public void beanValidationIsCheckedWhenSetterMethodsAreCalled() {
                Car car = new Car.Factory(new CarId("1234567"), Brand.VOLVO)
                        .withPrice(BigDecimal.TEN)
                        .build();

                assertEquals(1,
                        expectThrows(
                                ConstraintViolationException.class, () -> car.setPrice(BigDecimal.valueOf(-1))
                        )
                                .getConstraintViolations().stream().count()
                );

                assertEquals(1,
                        expectThrows(
                                ConstraintViolationException.class, () -> car.setBrand(null)
                        )
                                .getConstraintViolations().stream().count()
                );

                car.setTopSpeed(new TopSpeed(300));
                assertEquals(300, car.getTopSpeed().get().getValue().intValue());
            }

        }
    }

}