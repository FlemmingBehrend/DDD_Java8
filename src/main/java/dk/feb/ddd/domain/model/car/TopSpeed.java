package dk.feb.ddd.domain.model.car;

import dk.feb.ddd.domain.common.ValueObject;
import dk.feb.ddd.domain.common.documentation.DDDValueObject;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@DDDValueObject
public class TopSpeed implements ValueObject<TopSpeed> {

    @NotNull
    @Min(0)
    @Max(300)
    private Integer topSpeed;

    public TopSpeed(Integer topSpeed) {
        this.topSpeed = topSpeed;
    }

    public Integer getValue() {
        return topSpeed;
    }

    @Override
    public boolean sameValueAs(TopSpeed other) {
        return other != null
                && Objects.equals(topSpeed, other.getValue());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TopSpeed that = (TopSpeed) other;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(topSpeed);
    }

    @Override
    public String toString() {
        return "TopSpeed{" +
                "topSpeed=" + topSpeed +
                '}';
    }

}