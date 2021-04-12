package br.com.zup.mercadolivre.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @JsonCreator
    public NovaPerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "MENSAGEM: " + this.titulo;
    }
}
