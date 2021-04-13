package br.com.zup.mercadolivre.produto.pergunta;

import br.com.zup.mercadolivre.pedido.Pedido;

public interface Email {

    void enviarPergunta(NovaPerguntaRequest texto);

    void avisoDeCompra(Pedido pedido);
}
