package br.com.zup.mercadolivre.pedido;

import br.com.zup.mercadolivre.customvalidation.ExistsId;
import br.com.zup.mercadolivre.customvalidation.VerifyStock;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@VerifyStock(domain = Produto.class)
public class PedidoRequest {

    private String metodoPagamento;
    @NotNull
    @ExistsId(domain = Produto.class, field = "id")
    private Long idProduto;
    @NotNull
    @Positive
    private Integer quantidade;

    public PedidoRequest(String metodoPagamento, Long idProduto, Integer quantidade) {

        this.metodoPagamento = metodoPagamento;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Pedido toModel(ProdutoRepository produtoRepository, Usuario usuario) {
        Optional<Produto> produto = produtoRepository.findById(idProduto);
        Assert.isTrue(produto.isPresent(), "Este produto não existe");

        return new Pedido(this.metodoPagamento, produto.get(), this.quantidade, usuario, Status.INICIADO);
    }

    public String getMetodoPagamento() {
        return metodoPagamento.toUpperCase();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
