package br.com.zup.mercadolivre.produto.opiniao;

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
public class OpiniaoController {

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public OpiniaoController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/produtos/{id}/opiniao")
    @Transactional
    public ResponseEntity<String> adicionaOpiniao(@PathVariable(name = "id")Long idProduto,
                                                  @RequestBody @Valid NovaOpiniaoRequest request,
                                                  @AuthenticationPrincipal UsuarioLogado user){

        Optional<Usuario> usuario = usuarioRepository.findByLogin(user.getUsername());
        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if(produto.isEmpty() || usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        produto.get().addOpiniao(request, usuario.get());

        return ResponseEntity.ok().build();
    }
}
