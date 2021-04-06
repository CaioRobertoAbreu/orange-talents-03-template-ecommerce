package br.com.zup.mercadolivre.usuario;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @PastOrPresent
    private LocalDateTime instante;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String senha;

    public Usuario(String login, SenhaLimpa senha) {
        Assert.isTrue(StringUtils.hasLength(login), "email não pode estar em branco");
        Assert.notNull(senha, "campo SenhaLimpa não pode estar nulo");
        this.instante = LocalDateTime.now();
        this.login = login.toLowerCase(Locale.ROOT);
        this.senha = senha.hash();
    }

    @Deprecated
    public Usuario() {
    }
}