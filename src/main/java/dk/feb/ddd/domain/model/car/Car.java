package dk.feb.ddd.domain.model.car;

import dk.feb.ddd.domain.common.Entity;
import dk.feb.ddd.domain.common.documentation.DDDAggregateRoot;
import dk.feb.ddd.domain.common.documentation.DDDFactory;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

import static dk.feb.ddd.domain.common.validation.Validator.validate;
import static dk.feb.ddd.domain.common.validation.Validator.validateProperty;

@DDDAggregateRoot
public class Car implements Entity<Car, CarId> {

    @Valid
    @NotNull
    private CarId id;

    @NotNull
    private Brand brand;

    @Valid
    private TopSpeed topSpeed;

    @Min(0)
    private BigDecimal price;

    @Valid
    private Color color;

    private Car(CarId id, Brand brand) {
        this.id = id;
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
        validateProperty(this, "brand");
    }

    public Optional<TopSpeed> getTopSpeed() {
        return Optional.ofNullable(topSpeed);
    }

    public void setTopSpeed(TopSpeed topSpeed) {
        this.topSpeed = topSpeed;
        validateProperty(this, "topSpeed");
    }

    public Optional<BigDecimal> getPrice() {
        return Optional.ofNullable(price);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        validateProperty(this, "price");
    }

    public Optional<Color> getColor() {
        return Optional.ofNullable(color);
    }

    public void setColor(Color color) {
        this.color = color;
        validateProperty(this, "color");
    }

    @Override
    public boolean sameIdentityAs(Car other) {
        return getId().sameValueAs(other.getId());
    }

    @Override
    public CarId getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id.toString() +
                ", brand=" + brand +
                ", topSpeed=" + (topSpeed != null ? topSpeed.toString() : "[null]") +
                ", price=" + (price != null ? price.toString() : "[null]") +
                ", color=" + (color != null ? color.toString() : "[null]") +
                '}';
    }

    @DDDFactory
    public static class Factory {

        private Car car;

        public Factory(CarId carId, Brand brand) {
            this.car = new Car(carId, brand);
        }

        public Factory withColor(String color, Boolean metal) {
            this.car.color = new Color(color, metal);
            return this;
        }

        public Factory withTopSpeed(Integer topSpeed) {
            this.car.topSpeed = new TopSpeed(topSpeed);
            return this;
        }

        public Factory withPrice(BigDecimal price) {
            this.car.price = price;
            return this;
        }

        public Car build() {
            validate(car);
            return this.car;
        }

        public Car buildWithoutValidation() {
            return this.car;
        }

    }

}