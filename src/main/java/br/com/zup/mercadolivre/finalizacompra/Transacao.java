package br.com.zup.mercadolivre.finalizacompra;

import br.com.zup.mercadolivre.pedido.Pedido;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Transacao {

    @Id
    private String id;
    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusTransacao statusTransacao;
    @ManyToOne
    @Valid
    @NotNull
    private Pedido pedido;

    public Transacao(String id, StatusTransacao status, Pedido pedido) {
        this.id = id;
        this.statusTransacao = status;
        this.pedido = pedido;
    }

    @Deprecated
    public Transacao() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return id.equals(transacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public boolean estaConcluida() {

        return this.statusTransacao.equals(StatusTransacao.SUCESSO);
    }
}