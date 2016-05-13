package dk.feb.ddd.domain.model.car;

import dk.feb.ddd.domain.common.ValueObject;
import dk.feb.ddd.domain.common.documentation.DDDValueObject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@DDDValueObject
public class Color implements ValueObject<Color> {

    @NotNull
    @Size(min = 3, max = 10)
    private String color;

    @NotNull
    private Boolean metal;

    public Color(String color, Boolean metal) {
        this.color = color;
        this.metal = metal;
    }

    public String getValue() {
        return color;
    }

    public Boolean getMetal() {
        return metal;
    }

    @Override
    public boolean sameValueAs(Color other) {
        return other != null
                && Objects.equals(color, other.color)
                && Objects.equals(metal, other.metal);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Color that = (Color) other;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, metal);
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                ", metal=" + metal +
                '}';
    }

}
