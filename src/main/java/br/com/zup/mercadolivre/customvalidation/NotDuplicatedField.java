package br.com.zup.mercadolivre.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NotDuplicatedFieldValidator.class)
public @interface NotDuplicatedField {

    String message() default "Campo já existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };

    Class domain();
    String field();
}
