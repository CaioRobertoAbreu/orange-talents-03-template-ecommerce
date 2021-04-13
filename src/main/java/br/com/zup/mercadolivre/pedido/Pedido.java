package br.com.zup.mercadolivre.pedido;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @ManyToMany
    private List<Produto> produtos = new ArrayList<>();
    @Column(nullable = false)
    private Integer quantidade;
    @ManyToOne
    private Usuario usuario;
    private BigDecimal valorUnitario;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Pedido(String metodoPagamento, Produto produto, Integer quantidade, Usuario usuario,
                  Status status) {
        this.metodoPagamento = MetodoPagamento.valueOf(metodoPagamento.toUpperCase());
        this.produtos.add(produto);
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.valorUnitario = produto.getValor();
        this.status = status;

        produto.abaterEstoque(this.quantidade);
    }

    @Deprecated
    public Pedido() {
    }

    public Long getId() {
        return id;
    }


    public List<Produto> getProdutos() {
        return produtos;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
