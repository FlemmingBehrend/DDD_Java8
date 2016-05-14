package dk.feb.ddd.infrastructure.persistence.memory;

import dk.feb.ddd.domain.model.car.Brand;
import dk.feb.ddd.domain.model.car.Car;
import dk.feb.ddd.domain.model.car.CarId;
import dk.feb.ddd.domain.model.car.CarRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarRepositoryImpl implements CarRepository {

    private final static Map<CarId, Car> cars = new HashMap<>();

    static {
        cars.put(new CarId("0000001"), new Car.Factory(new CarId("0000001"), Brand.MAZDA)
                .withColor("Blue", Boolean.FALSE)
                .withPrice(BigDecimal.valueOf(200000))
                .build());
        cars.put(new CarId("0000002"), new Car.Factory(new CarId("0000002"), Brand.FORD)
                .withColor("Red", Boolean.TRUE)
                .withPrice(BigDecimal.valueOf(255000))
                .withTopSpeed(210)
                .build());
        cars.put(new CarId("0000003"), new Car.Factory(new CarId("0000003"), Brand.VOLVO)
                .withColor("Black", Boolean.FALSE)
                .withPrice(BigDecimal.valueOf(230000))
                .withTopSpeed(220)
                .build());
    }

    @Override
    public Car find(CarId carId) {
        printDB(cars);
        return cars.get(carId);
    }

    @Override
    public void update(Car car) {
        Car carToUpdate = cars.get(car.getId());
        carToUpdate.setPrice(car.getPrice().orElse(null));
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setTopSpeed(car.getTopSpeed().orElse(null));
        carToUpdate.setColor(car.getColor().orElse(null));
        cars.put(car.getId(), carToUpdate);
        printDB(cars);
    }

    private void printDB(Map<CarId, Car> cars) {
        cars.values().stream().forEach(System.out::println);
    }

}