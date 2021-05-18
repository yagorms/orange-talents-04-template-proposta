package br.com.ot4.yago.proposta.biometria;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaForm {

    @NotBlank
    private String impressaoDigital;

    @Deprecated
    public BiometriaForm() {
    }

        public String getImpressaoDigital() {
        return impressaoDigital;
    }

    public Biometria converter(BiometriaRepository biometriaRepository) {
        byte[] impressaoDigitalCodificada = Base64.getEncoder().encode(impressaoDigital.getBytes());
        return new Biometria(impressaoDigitalCodificada);
    }
}
