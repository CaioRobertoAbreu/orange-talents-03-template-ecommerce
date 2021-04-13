package br.com.zup.mercadolivre.produto.pergunta;

import br.com.zup.mercadolivre.pedido.Pedido;
import org.springframework.stereotype.Component;

@Component
public class EmailParaDonoProduto implements Email {

    @Override
    public void enviarPergunta(NovaPerguntaRequest texto) {
        System.out.println("Uma nova pergunta foi feita para o seu produto: \n" +
                 texto.toString() + "\nClique aqui para responder");
    }

    @Override
    public void avisoDeCompra(Pedido pedido) {
        System.out.println("Parabéns!!! \n" +
                "Seu produto acaba de ser comprado. \n" +
                "Comprador: " + pedido.getUsuario().getLogin() + "\n");
    }
}
