package br.com.zup.mercadolivre.customvalidation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

    @PersistenceContext
    private final EntityManager entityManager;
    private Class<?> domain;
    private String field;

    public ExistsIdValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(ExistsId param) {
        this.domain = param.domain();
        this.field = param.field();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if(value != null) {
            List<?> valores = entityManager.createQuery("SELECT 1 FROM " + domain.getSimpleName() + " WHERE " +
                    field + " = :valor")
                    .setParameter("valor", value).getResultList();

            Assert.state(valores.size() <= 1, "O campo " + field + " tem valores duplicados");

            return !valores.isEmpty();
        }

        return true;
    }
}
