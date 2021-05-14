package br.com.ot4.yago.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "cartoes", url = "${cartoes.host}")
public interface CartaoClient {

    @GetMapping
    public CartaoResponse consultaCartao(@RequestParam("idProposta") Long idProposta);
}
