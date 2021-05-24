package br.com.ot4.yago.proposta.carteiraDigital.feign;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class CarteiraDigitalResponse {

    @NotBlank
    private String id;
    @Enumerated(EnumType.STRING)
    private ResultadoFeign resultado;


    public CarteiraDigitalResponse(@NotBlank String id, ResultadoFeign resultado) {
        this.id = id;
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public ResultadoFeign getResultado() {
        return resultado;
    }
}
