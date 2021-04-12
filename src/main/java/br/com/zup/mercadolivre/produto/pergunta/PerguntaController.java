package br.com.zup.mercadolivre.produto.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioLogado;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final Email email;

    public PerguntaController(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository,
                              Email email) {

        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.email = email;
    }

    @PostMapping("/produtos/{id}/pergunta")
    @Transactional
    public ResponseEntity<?> criarPergunta(@PathVariable(name = "id") Long idProduto,
                                                @RequestBody @Valid NovaPerguntaRequest request,
                                                @AuthenticationPrincipal UsuarioLogado user){

        Optional<Produto> produto = produtoRepository.findById(idProduto);
        Optional<Usuario> usuario = usuarioRepository.findByLogin(user.getUsername());

        if(produto.isEmpty() || usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        email.enviarPergunta(request);
        produto.get().addPergunta(request, usuario.get());

        return ResponseEntity.ok().build();
    }
}
