package br.com.zup.mercadolivre.produto.uploadimage;

import br.com.zup.mercadolivre.handler.Error;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.UsuarioLogado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.BindException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ImageController {

    private final ProdutoRepository produtoRepository;
    private final Uploader uploadFake;


    public ImageController(ProdutoRepository produtoRepository, UploadFake uploadFake) {
        this.produtoRepository = produtoRepository;
        this.uploadFake = uploadFake;
    }

    @PostMapping("/produtos/{id}/images")
    @Transactional
    public ResponseEntity<?> upload(@PathVariable Long id, @Valid NovasImagensRequest request,
                                    @AuthenticationPrincipal UsuarioLogado user){

        Optional<Produto> producoEncontrado = produtoRepository.findById(id);
        if(producoEncontrado.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("produto", "" +
                    "produto não encontado"));
        }

        if(!producoEncontrado.get().getLoginUsuario().equals(user.getUsername())){

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Set<String> links = uploadFake.enviar(request.getImagens());
        producoEncontrado.get().addImagens(links);

        return ResponseEntity.ok().build();

    }
}
