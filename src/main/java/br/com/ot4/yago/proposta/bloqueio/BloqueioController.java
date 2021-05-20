package br.com.ot4.yago.proposta.bloqueio;

import br.com.ot4.yago.proposta.bloqueio.feign.BloqueioClient;
import br.com.ot4.yago.proposta.bloqueio.feign.BloqueioRequest;
import br.com.ot4.yago.proposta.bloqueio.feign.BloqueioResponse;
import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.cartao.CartaoRepository;
import feign.FeignException;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetSocketAddress;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioController {

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioClient bloqueioClient;

    @PostMapping("/{numeroCartao}")
    public ResponseEntity<?> bloqueio(@PathVariable("numeroCartao") String numeroCartao,
                                      @RequestHeader(value = "User-Agent") String userAgent,
                                      @RequestHeader HttpHeaders headers){

        InetSocketAddress host = headers.getHost();
        String ipClient = host.getHostName() + ":" + host.getPort();

        Optional<Cartao> possivelCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (possivelCartao.isPresent()){

            if (possivelCartao.get().getBloqueio() != null){

                return ResponseEntity.unprocessableEntity().build();
            }

            else{
                Bloqueio bloqueioEfetuado = new Bloqueio(userAgent, ipClient);
                bloqueioRepository.save(bloqueioEfetuado);
                possivelCartao.get().setBloqueio(bloqueioEfetuado);
                cartaoRepository.save(possivelCartao.get());

                try {
                    BloqueioResponse informaBloqueio = bloqueioClient.informaBloqueio(numeroCartao, new BloqueioRequest());
                    possivelCartao.get().setStatus(informaBloqueio.getResultado());
                    cartaoRepository.save(possivelCartao.get());

                }catch (FeignException e){

                }

                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }


}
