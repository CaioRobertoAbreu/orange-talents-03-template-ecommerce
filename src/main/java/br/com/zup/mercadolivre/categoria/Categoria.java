package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.customvalidation.NotDuplicatedField;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String categoriaMae;

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

    public void setCategoriaMae(String categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
