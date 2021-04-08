package br.com.zup.mercadolivre.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/usuario")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest ) {

        usuarioRepository.save(novoUsuarioRequest.toModel());
        return ResponseEntity.ok().build();
    }

}

