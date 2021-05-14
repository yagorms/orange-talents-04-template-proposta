package br.com.ot4.yago.proposta.cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {

    @NotBlank
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    @NotNull
    private BigDecimal limite;

    @Deprecated
    public CartaoResponse() {
    }

    public CartaoResponse(@NotBlank String id, @NotNull LocalDateTime emitidoEm,
                          @NotBlank String titular, @NotNull BigDecimal limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Cartao converter(CartaoRepository cartaoRepository) {
        return new Cartao(id, emitidoEm, titular, limite);
    }
}
