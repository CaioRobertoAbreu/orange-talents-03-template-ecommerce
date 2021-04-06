package br.com.zup.mercadolivre.handler;

public class Error {

    private String campo;
    private String mesangem;

    public Error(String campo, String mesangem) {
        this.campo = campo;
        this.mesangem = mesangem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMesangem() {
        return mesangem;
    }
}
