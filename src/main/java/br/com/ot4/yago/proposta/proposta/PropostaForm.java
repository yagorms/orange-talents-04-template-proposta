package br.com.ot4.yago.proposta.proposta;

import br.com.ot4.yago.proposta.validator.CPForCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


public class PropostaForm {

    @NotBlank @CPForCNPJ
    private String documento;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;

    @Deprecated
    public PropostaForm() {
    }

    public PropostaForm(@NotBlank String documento, @NotBlank @Email String email,
                        @NotBlank String nome, @NotBlank String endereco,
                        @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
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

    public Proposta converter(PropostaRepository propostaRepository) {
        return new Proposta(documento, email, nome, endereco, salario);
    }
}
