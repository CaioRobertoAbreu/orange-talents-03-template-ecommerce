package br.com.zup.mercadolivre.finalizacompra;

public enum StatusPagseguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.SUCESSO;
        }

        if(this.equals(ERRO)){
            return StatusTransacao.FALHA;
        }

        return StatusTransacao.FALHA;
    }
}
