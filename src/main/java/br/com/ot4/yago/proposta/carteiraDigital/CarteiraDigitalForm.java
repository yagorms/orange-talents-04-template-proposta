package br.com.ot4.yago.proposta.carteiraDigital;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.carteiraDigital.feign.CarteiraDigitalResponse;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalForm {

    @Email @NotBlank
    private String email;
    @Enumerated(EnumType.STRING) @NotNull
    private TipoCarteira carteira;

    @Deprecated
    public CarteiraDigitalForm() {
    }

    public CarteiraDigitalForm(@Email @NotBlank String email, @NotNull TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public CarteiraDigital converter(CarteiraDigitalResponse carteiraDigitalResponse, Cartao cartao) {
        return new CarteiraDigital(email, carteira, carteiraDigitalResponse.getResultado(), carteiraDigitalResponse.getId(), cartao);
    }
}
