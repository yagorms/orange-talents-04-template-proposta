package br.com.ot4.yago.proposta.avisoViagem;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.InetSocketAddress;
import java.util.Optional;

@RestController
@RequestMapping("/avisoviagem")
public class AvisoViagemController {

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{numeroCartao}")
    public ResponseEntity<?> avisoViagem(@PathVariable("numeroCartao") String numeroCartao,
                                         @RequestHeader(value = "User-Agent") String userAgent,
                                         @RequestHeader HttpHeaders headers,
                                         @RequestBody @Valid AvisoViagemForm form){

        InetSocketAddress host = headers.getHost();
        String ipClient = host.getHostName() + ":" + host.getPort();

        Optional<Cartao> possivelCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (possivelCartao.isPresent()){

            AvisoViagem avisoViagem = form.converter(ipClient, userAgent, possivelCartao.get());
            avisoViagemRepository.save(avisoViagem);
            possivelCartao.get().setAvisoViagem(avisoViagem);
            cartaoRepository.save(possivelCartao.get());

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
