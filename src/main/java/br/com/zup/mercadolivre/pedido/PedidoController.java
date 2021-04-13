package br.com.zup.mercadolivre.pedido;

import br.com.zup.mercadolivre.handler.Error;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.produto.pergunta.Email;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioLogado;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PedidoController {

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final Email email;

    public PedidoController(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository,
                            PedidoRepository pedidoRepository, Email email) {

        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.email = email;
    }

    @PostMapping("/produtos/pedido")
    @Transactional
    public ResponseEntity<?> compra(@RequestBody @Valid PedidoRequest request,
                                         @AuthenticationPrincipal UsuarioLogado user) {

        Optional<Usuario> usuario = usuarioRepository.findByLogin(user.getUsername());

        Pedido pedido = request.toModel(produtoRepository, usuario.get());

        try {
            MetodoPagamento.eValido(request.getMetodoPagamento());
        }catch (IllegalArgumentException e){

            return ResponseEntity.badRequest().body(
                    new Error("metodoPagamento", "Forma de pagamento inexistente " +
                            request.getMetodoPagamento()));
        }

        pedidoRepository.save(pedido);
        String pagamento = request.getMetodoPagamento();
        String url = MetodoPagamento.valueOf(pagamento).realizarPagamento(pedido);
        email.avisoDeCompra(pedido);
        return ResponseEntity.status(303).body(url);
    }

}
