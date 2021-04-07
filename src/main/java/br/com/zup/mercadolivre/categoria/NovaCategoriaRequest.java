package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.customvalidation.ExistsId;
import br.com.zup.mercadolivre.customvalidation.NotDuplicatedField;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @NotDuplicatedField(domain = Categoria.class, field = "nome")
    private String nome;
    @ExistsId(domain = Categoria.class, field = "id")
    private Long idCategoriaMae;

    public NovaCategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(this.nome);

        if(this.idCategoriaMae != null) {
            Categoria categoria1 = categoriaRepository.findById(this.idCategoriaMae).get();
            categoria.setCategoriaMae(categoria1);
        }

        return categoria;
    }

}
