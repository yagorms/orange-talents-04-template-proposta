package br.com.ot4.yago.proposta.bloqueio.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "bloqueios", url = "${cartoes.host}")
public interface BloqueioClient {

    @RequestMapping(method = RequestMethod.POST, value = "{numeroCartao}/bloqueios", produces = "application/json")
    BloqueioResponse informaBloqueio(@PathVariable("numeroCartao") String numeroCartao, @RequestBody BloqueioRequest bloqueioRequest);

}
