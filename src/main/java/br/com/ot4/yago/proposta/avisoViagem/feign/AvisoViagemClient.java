package br.com.ot4.yago.proposta.avisoViagem.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "avisos", url = "${cartoes.host}")
public interface AvisoViagemClient {

    @RequestMapping(method = RequestMethod.POST, value = "{numeroCartao}/avisos", produces = "application/json")
    String notificaAvisoViagem(@PathVariable("numeroCartao") String numeroCartao,@RequestBody AvisoViagemRequest avisoViagemRequest);


}
