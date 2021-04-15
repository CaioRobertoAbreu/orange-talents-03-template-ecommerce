package br.com.zup.mercadolivre.pedido;

import br.com.zup.mercadolivre.finalizacompra.EmailParaCliente;
import br.com.zup.mercadolivre.handler.Error;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.produto.pergunta.EmailParaDonoProduto;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioLogado;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;
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
    private final EmailParaDonoProduto emailDonoProduto;

    public PedidoController(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository,
                            PedidoRepository pedidoRepository, EmailParaDonoProduto emailDonoProduto) {

        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.emailDonoProduto = emailDonoProduto;
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
        emailDonoProduto.avisoDeCompra(pedido);

        return ResponseEntity.status(301).body(url);
    }

}
