package br.com.zup.mercadolivre.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = VerifyStockValidator.class)
public @interface VerifyStock {

    String message() default "Sem estoque suficiente para o produto selecionado";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

    Class<?> domain();

}
