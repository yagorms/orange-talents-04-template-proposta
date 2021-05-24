package br.com.ot4.yago.proposta.carteiraDigital.feign;

import br.com.ot4.yago.proposta.carteiraDigital.CarteiraDigitalForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "carteiras", url = "${cartoes.host}")
public interface CarteiraDigitalClient {

    @RequestMapping(method = RequestMethod.POST, value = "{numeroCartao}/carteiras", produces = "application/json")
    CarteiraDigitalResponse associarCarteira(@PathVariable("numeroCartao") String numeroCartao, @RequestBody CarteiraDigitalForm form);
}
