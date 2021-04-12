package br.com.zup.mercadolivre.produto.pergunta;

import org.springframework.stereotype.Component;

@Component
public class EmailParaDonoProduto implements Email {

    @Override
    public void enviarPergunta(NovaPerguntaRequest texto) {
        System.out.println("Uma nova pergunta foi feita para o seu produto: \n" +
                 texto.toString() + "\nClique aqui para responder");
    }
}
