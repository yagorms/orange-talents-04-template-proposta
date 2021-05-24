package br.com.ot4.yago.proposta.carteiraDigital;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.carteiraDigital.feign.ResultadoFeign;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CarteiraDigital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private LocalDateTime associadaEm = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;
    //retornos do feign
    @Enumerated(EnumType.STRING)
    private ResultadoFeign resultado;
    private String idResposta;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(String email, TipoCarteira carteira, ResultadoFeign resultado, String idResposta, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.resultado = resultado;
        this.idResposta = idResposta;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public ResultadoFeign getResultado() { return resultado; }

    public String getIdResposta() {
        return idResposta;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
