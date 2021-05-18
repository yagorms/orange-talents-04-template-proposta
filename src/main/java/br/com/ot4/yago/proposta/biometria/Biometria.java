package br.com.ot4.yago.proposta.biometria;

import br.com.ot4.yago.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] impressaoDigital;
    private LocalDateTime dataBiometria = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(byte[] impressaoDigital) {
        this.impressaoDigital = impressaoDigital;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public byte[] getImpressaoDigital() {
        return impressaoDigital;
    }

    public LocalDateTime getDataBiometria() {
        return dataBiometria;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
