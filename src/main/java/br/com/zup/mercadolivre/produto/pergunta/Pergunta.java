package br.com.zup.mercadolivre.produto.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();
    @NotBlank
    private String titulo;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario usuario;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public Pergunta() {
    }

    public String getTitulo() {
        return titulo;
    }
}
