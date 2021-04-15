package br.com.zup.mercadolivre.finalizacompra;

import br.com.zup.mercadolivre.pedido.Pedido;

public interface EventoPedido {

    void processa(Pedido pedido);
}
