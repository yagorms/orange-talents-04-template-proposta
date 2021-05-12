package br.com.ot4.yago.proposta.proposta.Feign;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VerificaRestricaoResponse {

    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String resultadoSolicitacao;
    @NotNull
    private Long idProposta;

    @Deprecated
    public VerificaRestricaoResponse() {
    }

    public VerificaRestricaoResponse(@NotBlank String documento, @NotBlank String nome, @NotBlank String resultadoSolicitacao, @NotNull Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }

}
