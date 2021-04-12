package br.com.zup.mercadolivre.produto.uploadimage;

public class ImagemResponse {

    private String link;

    public ImagemResponse(Imagem imagem) {
        this.link = imagem.getLink();
    }

    public String getLink() {
        return link;
    }
}
