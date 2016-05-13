package dk.feb.ddd.domain.common.documentation;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.SOURCE)
@Target(value = ElementType.TYPE)
public @interface DDDAggregateRoot {}