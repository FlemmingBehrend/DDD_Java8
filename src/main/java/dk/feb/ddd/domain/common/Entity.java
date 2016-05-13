package dk.feb.ddd.domain.common;

public interface Entity<T, ID> {

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardles of other attributes.
     */
    boolean sameIdentityAs(T other);

    /**
     * Entities have identity and it is type safe.
     *
     * @return identifier object for entity
     */
    ID getId();

}