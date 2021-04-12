package br.com.zup.mercadolivre.produto.detalheproduto;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasResponse;
import br.com.zup.mercadolivre.produto.caracteristica.Caracteristicas;
import br.com.zup.mercadolivre.produto.opiniao.Opiniao;
import br.com.zup.mercadolivre.produto.opiniao.OpiniaoResponse;
import br.com.zup.mercadolivre.produto.pergunta.Pergunta;
import br.com.zup.mercadolivre.produto.pergunta.PerguntaResponse;
import br.com.zup.mercadolivre.produto.uploadimage.Imagem;
import br.com.zup.mercadolivre.produto.uploadimage.ImagemResponse;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalheProdutoResponse {

    private List<ImagemResponse> imagens = new ArrayList<>();
    private String nomeProduto;
    private BigDecimal preco;
    private List<CaracteristicasResponse> caracteristicas = new ArrayList<>();
    private String descricao;
    private Double notaMedia;
    private Integer totalOpinioes;
    private List<OpiniaoResponse> opinioes = new ArrayList<>();
    private List<PerguntaResponse> perguntas = new ArrayList<>();

    public DetalheProdutoResponse(Produto produto) {
        this.imagens.addAll(produto.getImagens().stream().map(ImagemResponse::new).collect(Collectors.toList()));
        this.nomeProduto = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas.addAll(produto.getCaracteristicas().stream().map(CaracteristicasResponse::new).collect(Collectors.toList()));
        this.descricao = produto.getDescricao();
        this.opinioes.addAll(produto.getOpinioes().stream().map(OpiniaoResponse::new).collect(Collectors.toList()));
        this.notaMedia = produto.getOpinioes().stream().mapToDouble(media -> media.getNota()).average().orElse(0.);
        this.totalOpinioes = produto.getOpinioes().size();
        this.perguntas.addAll(produto.getPerguntas().stream().map(PerguntaResponse::new).collect(Collectors.toList()));
    }

    public List<ImagemResponse> getImagens() {
        return imagens;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<CaracteristicasResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public Integer getTotalOpinioes() {
        return totalOpinioes;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
