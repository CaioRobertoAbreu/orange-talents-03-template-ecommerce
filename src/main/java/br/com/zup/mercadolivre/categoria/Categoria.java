package br.com.zup.mercadolivre.categoria;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;

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
