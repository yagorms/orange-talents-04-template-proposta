package br.com.ot4.yago.proposta.avisoViagem.feign;

import br.com.ot4.yago.proposta.avisoViagem.AvisoViagemForm;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import java.time.LocalDate;

public class AvisoViagemRequest {

    private String destino;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    @Deprecated
    public AvisoViagemRequest() {
    }

    public AvisoViagemRequest(@Valid AvisoViagemForm form) {
        this.destino = form.getDestino();
        this.validoAte = form.getDataTermino();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
