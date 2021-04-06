package br.com.zup.mercadolivre.customvalidation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotDuplicatedFieldValidator implements ConstraintValidator<NotDuplicatedField, Object> {

    @PersistenceContext
    private final EntityManager entityManager;
    private Class<?> domainClass;
    private String field;

    public NotDuplicatedFieldValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(NotDuplicatedField param) {
        domainClass = param.domain();
        field = param.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        List<?> lista = entityManager.createQuery("SELECT 1 FROM " + domainClass.getSimpleName() + " WHERE " +
                field + " = :valor")
                .setParameter("valor", value)
                .getResultList();

        Assert.state(lista.size() <= 1, "Há mais de um registro para o campo" +
                field + "na classe de dominio " + domainClass.getName() + " com valor " + value);


        if(!lista.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(field + " já cadastrado com valor " + value)
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
