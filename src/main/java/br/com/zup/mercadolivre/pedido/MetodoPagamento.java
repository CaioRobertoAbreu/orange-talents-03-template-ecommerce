package br.com.zup.mercadolivre.pedido;

public enum MetodoPagamento implements Pagamento{

    PAYPAL("paypal"){
        @Override
        public String realizarPagamento(Pedido pedido) {
            return "paypal.com?buyerId={"+ pedido.getId()+"}&redirectUrl={urlRetornoAppPosPagamento}";
        }
    },

    PAGSEGURO("pagseguro"){
        @Override
        public String realizarPagamento(Pedido pedido) {
            return "pagseguro.com?returnId={"+pedido.getId()+"}&redirectUrl={urlRetornoAppPosPagamento}";
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
