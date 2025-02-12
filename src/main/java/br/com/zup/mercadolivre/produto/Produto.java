package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.handler.Error;
import br.com.zup.mercadolivre.produto.caracteristica.Caracteristicas;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasRequest;
import br.com.zup.mercadolivre.produto.opiniao.NovaOpiniaoRequest;
import br.com.zup.mercadolivre.produto.opiniao.Opiniao;
import br.com.zup.mercadolivre.produto.pergunta.NovaPerguntaRequest;
import br.com.zup.mercadolivre.produto.pergunta.Pergunta;
import br.com.zup.mercadolivre.produto.uploadimage.Imagem;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal valor;
    private Integer quantidade;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;
    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Caracteristicas> caracteristicas = new HashSet<>();
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<Imagem> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Opiniao> opinioes = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Pergunta> perguntas = new ArrayList<>();



    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao,
                   Categoria categoria, Usuario usuario, List<CaracteristicasRequest> caracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas
                .stream()
                .map(c -> c.toModel(this))
                .collect(Collectors.toSet()));

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisar ter pelo menos" +
                " três características");
    }

    @Deprecated
    public Produto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getLoginUsuario() {
        return usuario.getLogin();
    }

    public Long getIdUsuario(){
        return this.usuario.getId();
    }

    public void addImagens(Set<String> links) {
        Set<Imagem> imagens = links.stream().map(link -> new Imagem(link, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public void addOpiniao(NovaOpiniaoRequest novaOpiniao, Usuario usuario){

        Opiniao opiniao = new Opiniao(novaOpiniao.getNota(), novaOpiniao.getTitulo(), novaOpiniao.getDescricao(),
                usuario, this);

        this.opinioes.add(opiniao);
    }

    public void addPergunta(NovaPerguntaRequest novaPergunta, Usuario usuario) {
        Pergunta pergunta = new Pergunta(novaPergunta.getTitulo(), usuario, this);

        this.perguntas.add(pergunta);
    }

    public void abaterEstoque(int quantidade){
        this.quantidade = this.quantidade - quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<Caracteristicas> getCaracteristicas() {
        return caracteristicas;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

}
