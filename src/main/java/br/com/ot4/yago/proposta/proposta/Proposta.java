package br.com.ot4.yago.proposta.proposta;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.security.Criptografia;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Convert(converter = Criptografia.class)
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private EstadoProposta estadoProposta = EstadoProposta.NAO_ANALISADO;
    @OneToOne(cascade = CascadeType.ALL)
    public Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void setEstadoProposta(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public Cartao getCartao() {
        return cartao;
    }

}
