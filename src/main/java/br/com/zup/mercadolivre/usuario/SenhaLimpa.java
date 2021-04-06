package br.com.zup.mercadolivre.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * Representa uma senha limpa
 */
public class SenhaLimpa {

    @Length(min = 6)
    @NotBlank
    private String senhaLimpa;

    public SenhaLimpa(String senha) {
        Assert.isTrue(StringUtils.hasLength(senha), "Campo senha precisa ser preenchido");
        Assert.isTrue(senha.length() >= 6, "senha muito curto (min. 6)");
        this.senhaLimpa = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(this.senhaLimpa);
    }
}
