package dk.feb.ddd.domain.model.car;

public interface CarRepository {

    Car find(CarId carId);

    void update(Car car);

}