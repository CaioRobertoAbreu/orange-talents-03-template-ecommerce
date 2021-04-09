package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.produto.Produto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    public Categoria(String nome) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    @Deprecated
    public Categoria() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
