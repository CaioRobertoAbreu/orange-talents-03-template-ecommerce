package br.com.zup.mercadolivre.produto.uploadimage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploadFake implements Uploader{

    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {

        return imagens.stream()
                .map(i -> "https://aws.com/ml/"+ Objects.hash(i.getOriginalFilename()))
                .collect(Collectors.toSet());
    }
}
