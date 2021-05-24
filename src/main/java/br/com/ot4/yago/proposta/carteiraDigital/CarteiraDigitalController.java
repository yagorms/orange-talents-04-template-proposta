package br.com.ot4.yago.proposta.carteiraDigital;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.cartao.CartaoRepository;
import br.com.ot4.yago.proposta.carteiraDigital.feign.CarteiraDigitalClient;
import br.com.ot4.yago.proposta.carteiraDigital.feign.CarteiraDigitalResponse;
import br.com.ot4.yago.proposta.carteiraDigital.feign.ResultadoFeign;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteira")
public class CarteiraDigitalController {

    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraDigitalClient carteiraDigitalClient;

    @PostMapping("/{numeroCartao}")
    public ResponseEntity<?> cadastrarCarteira(@PathVariable("numeroCartao") String numeroCartao,
                                               @Valid @RequestBody CarteiraDigitalForm form,
                                               UriComponentsBuilder uriComponentsBuilder){

        Optional<Cartao> possivelCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Optional<CarteiraDigital> possivelCarteira = carteiraDigitalRepository.findByCartaoAndCarteiraAndResultado(possivelCartao.get(),form.getCarteira(), ResultadoFeign.ASSOCIADA);

        if (possivelCartao.isPresent() && possivelCarteira.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        try {

            CarteiraDigitalResponse carteiraDigitalResponse = carteiraDigitalClient.associarCarteira(numeroCartao, form);
            CarteiraDigital carteiraDigital = form.converter(carteiraDigitalResponse, possivelCartao.get());
            carteiraDigitalRepository.save(carteiraDigital);
            cartaoRepository.save(possivelCartao.get());

            URI carteiraCadastrada = uriComponentsBuilder.path("/carteira/{id}").buildAndExpand(carteiraDigital.getId()).toUri();
            return ResponseEntity.created(carteiraCadastrada).build();

        }catch (FeignException e){
            return ResponseEntity.badRequest().build();

        }
    }


}
