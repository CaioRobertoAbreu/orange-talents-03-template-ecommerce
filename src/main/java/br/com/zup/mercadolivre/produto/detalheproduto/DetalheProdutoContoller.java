package br.com.zup.mercadolivre.produto.detalheproduto;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DetalheProdutoContoller {

    private ProdutoRepository produtoRepository;

    public DetalheProdutoContoller(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<DetalheProdutoResponse> detalharProduto(@PathVariable(name = "id") Long idProduto){

        Optional<Produto> produtoEncontrado = produtoRepository.findById(idProduto);

        if(produtoEncontrado.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        DetalheProdutoResponse response = new DetalheProdutoResponse(produtoEncontrado.get());

        return ResponseEntity.ok(response);
    }
}
