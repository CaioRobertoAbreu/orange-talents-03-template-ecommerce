package br.com.zup.mercadolivre.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {

        return new Usuario(login, new SenhaLimpa(this.senha));
    }

}

