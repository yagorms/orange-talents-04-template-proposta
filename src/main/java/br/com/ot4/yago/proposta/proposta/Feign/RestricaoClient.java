package br.com.ot4.yago.proposta.proposta.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "analises", url = "${analises.host}")
public interface RestricaoClient {

//    @RequestMapping(method = RequestMethod.POST, value = "", produces = "application/json")
    @PostMapping
    VerificaRestricaoResponse verificaRestricao(VerificaRestricaoRequest request);
}
