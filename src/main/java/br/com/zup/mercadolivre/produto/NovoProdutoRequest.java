package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.customvalidation.ExistsId;
import br.com.zup.mercadolivre.customvalidation.NotDuplicatedField;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasRequest;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

public class NovoProdutoRequest {

    @NotBlank
    @NotDuplicatedField(domain = Produto.class, field = "nome")
    private String nome;
    @NotNull
    @Min(0) @Digits(integer = 8, fraction = 2)
    @Positive
    private BigDecimal valor;
    @Min(0)
    @NotNull
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ExistsId(domain = Categoria.class, field = "id")
    private Long idCategoria;
    @Size(min = 3)
    @Valid
    @UniqueElements
    private List<CaracteristicasRequest> caracteristicas = new ArrayList<>();


    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao,
                              Long idCategoria, @Valid List<CaracteristicasRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario){
        Optional<Categoria> categoria = categoriaRepository.findById(this.idCategoria);
        Assert.isTrue(categoria.isPresent(), "Não existe uma categoria com id " + this.idCategoria);

        Produto produto = new Produto(this.nome, this.valor, this.quantidade, this.descricao,
                categoria.get(), usuario, this.caracteristicas);

        return produto;
    }




}
