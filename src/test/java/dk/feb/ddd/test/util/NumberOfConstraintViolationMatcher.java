package dk.feb.ddd.test.util;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

public class NumberOfConstraintViolationMatcher extends TypeSafeMatcher<ConstraintViolationException> {

    private int numberOfConstraints;
    private final int expectedNumberOfConstraints;
    private final String property;
    private final boolean debug;

    public NumberOfConstraintViolationMatcher(int number, String property, boolean debug) {
        this.debug = debug;
        this.property = property;
        this.expectedNumberOfConstraints = number;
    }

    public static NumberOfConstraintViolationMatcher numberOfConstraints(int number) {
        return new NumberOfConstraintViolationMatcher(number, null, false);
    }

    public static NumberOfConstraintViolationMatcher debugNumberOfConstraints(int number) {
        return new NumberOfConstraintViolationMatcher(number, null, true);
    }

    public static NumberOfConstraintViolationMatcher numberOfConstraints(int number, String property) {
        return new NumberOfConstraintViolationMatcher(number, property, false);
    }

    public static NumberOfConstraintViolationMatcher debugNumberOfConstraints(int number, String property) {
        return new NumberOfConstraintViolationMatcher(number, property, true);
    }

    @Override
    protected boolean matchesSafely(ConstraintViolationException e) {
        if (debug) {
            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                System.out.println("constraintViolation = " + constraintViolation);
            }
        }
        if (this.property != null) {
            int propertyConstraintViolation = 0;
            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                Path propertyPath = constraintViolation.getPropertyPath();
                if (this.property.equalsIgnoreCase(propertyPath.toString())) {
                    propertyConstraintViolation++;
                }
            }
            this.numberOfConstraints = propertyConstraintViolation;
        } else {
            this.numberOfConstraints = e.getConstraintViolations().size();
        }
        return this.numberOfConstraints == this.expectedNumberOfConstraints;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format("got %s constraint, but expected %s", this.numberOfConstraints, this.expectedNumberOfConstraints));
    }

}