package br.com.zup.mercadolivre.customvalidation;

import br.com.zup.mercadolivre.pedido.PedidoRequest;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class VerifyStockValidator implements ConstraintValidator<VerifyStock, Object> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> domain;


    @Override
    public void initialize(VerifyStock constraintAnnotation) {
        domain = constraintAnnotation.domain();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        PedidoRequest pedidoRequest = (PedidoRequest)value;

        List resultList = entityManager.createQuery("SELECT p FROM " + domain.getSimpleName() +
                " p WHERE p.id = " + pedidoRequest.getIdProduto() +
                " AND p.quantidade >= " + pedidoRequest.getQuantidade())
                .getResultList();

        Assert.isTrue(resultList.size() <= 1, "Há mais de um produto com mesmo ID: " + value);

        return !resultList.isEmpty();
    }
}
