package dk.feb.ddd.domain.model.car;

import dk.feb.ddd.domain.common.ValueObject;
import dk.feb.ddd.domain.common.documentation.DDDValueObject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@DDDValueObject
public class CarId implements ValueObject<CarId> {

    @NotNull
    @Size(min = 7, max = 7, message = "must have a length of 7")
    private String id;

    public CarId(String id) {
        this.id = id;
    }

    public String getValue() {
        return id;
    }

    @Override
    public boolean sameValueAs(CarId other) {
        return other != null && Objects.equals(id, other.id);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        CarId that = (CarId) other;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CarId{" +
                "id='" + id + '\'' +
                '}';
    }

}
