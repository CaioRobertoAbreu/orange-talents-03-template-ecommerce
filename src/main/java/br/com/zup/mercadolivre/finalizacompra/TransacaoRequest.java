package br.com.zup.mercadolivre.finalizacompra;

import br.com.zup.mercadolivre.pedido.Pedido;

public interface TransacaoRequest {

    /**
     *
     * @param pedido
     * @return retorna uma nova transação
     * dependendo do tipo de pagamento
     * (paypal, pagseguro, entre outros, se houver)
     */
    Transacao toModel(Pedido pedido);
}
