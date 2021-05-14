package br.com.ot4.yago.proposta.cartao;


import br.com.ot4.yago.proposta.proposta.Proposta;

public class CartaoRequest {

    private Long idProposta;


    public CartaoRequest(Proposta proposta) {
        this.idProposta = proposta.getId();
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
