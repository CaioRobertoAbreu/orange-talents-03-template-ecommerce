package br.com.zup.mercadolivre.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistsIdValidator.class)
public @interface ExistsId{

    String message() default "Valor não encontrado";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    Class<?> domain();
    String field();

}
