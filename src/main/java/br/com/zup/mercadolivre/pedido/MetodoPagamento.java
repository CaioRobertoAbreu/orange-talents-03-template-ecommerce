package br.com.zup.mercadolivre.pedido;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public enum MetodoPagamento implements Pagamento{

    PAYPAL("paypal"){
        @Override
        public String realizarPagamento(Pedido pedido) {
            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/retorno-paypal/{id}")
                    .buildAndExpand(pedido.getId()).toString();


            return "paypal.com?buyerId="+pedido.getId()+"&redirectUrl="+uri;
        }
    },

    PAGSEGURO("pagseguro"){
        @Override
        public String realizarPagamento(Pedido pedido) {
            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(pedido.getId()).toString();

            return "pagseguro.com?returnId="+pedido.getId()+"&redirectUrl="+uri;
        }
    };

    private String descricoa;

    MetodoPagamento(String descricoa) {
        this.descricoa = descricoa;
    }

    public static void eValido(String pagamento) {
        /**
         * @Param valor em String representando um ENUM
         * Estoura uma exceção caso não exista
         */
        MetodoPagamento.valueOf(pagamento.toUpperCase());
    }
}
