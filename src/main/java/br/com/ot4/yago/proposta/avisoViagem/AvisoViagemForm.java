package br.com.ot4.yago.proposta.avisoViagem;

import br.com.ot4.yago.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemForm {

    @NotBlank
    private String destino;
    @FutureOrPresent @NotNull @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;

    @Deprecated
    public AvisoViagemForm() {
    }

    public AvisoViagemForm(@NotBlank String destino, @FutureOrPresent @NotNull LocalDate dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public AvisoViagem converter(String ipClient, String userAgent, Cartao cartao) {
        return new AvisoViagem(destino, dataTermino, ipClient, userAgent, cartao);
    }
}
