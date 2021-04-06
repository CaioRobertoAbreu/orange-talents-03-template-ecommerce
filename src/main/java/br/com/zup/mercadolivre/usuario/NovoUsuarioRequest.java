package br.com.zup.mercadolivre.usuario;

import br.com.zup.mercadolivre.customvalidation.NotDuplicatedField;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Locale;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @NotDuplicatedField(domain = Usuario.class, field = "login")
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String login, String senha) {
        this.login = login.toLowerCase(Locale.ROOT);
        this.senha = senha;
    }

    public Usuario toModel() {

        return new Usuario(login, new SenhaLimpa(this.senha));
    }

}

