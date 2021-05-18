package br.com.ot4.yago.proposta.biometria;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.cartao.CartaoRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
public class BiometriaSalvaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;



    @PostMapping("/{idCartao}")
    public ResponseEntity<?> salvaBiometria (@RequestBody @Valid BiometriaForm form, @PathVariable("idCartao") Long idCartao, UriComponentsBuilder uriComponentsBuilder){

        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);

        if (possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = form.converter(biometriaRepository);
        biometria.setCartao(possivelCartao.get());
        biometriaRepository.save(biometria);

        URI uri = uriComponentsBuilder.path("/biometria/{idCartao}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
