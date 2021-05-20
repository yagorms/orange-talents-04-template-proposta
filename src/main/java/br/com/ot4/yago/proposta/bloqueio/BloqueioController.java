package br.com.ot4.yago.proposta.bloqueio;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.cartao.CartaoRepository;
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

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloqueio(@PathVariable("idCartao") Long idCartao,
                                      @RequestHeader(value = "User-Agent") String userAgent,
                                      @RequestHeader HttpHeaders headers){

        InetSocketAddress host = headers.getHost();
        String ipClient = host.getHostName() + ":" + host.getPort();
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);

        if (possivelCartao.isPresent()){

            if (possivelCartao.get().getBloqueio() != null){

                return ResponseEntity.unprocessableEntity().build();
            }

            else{
                Bloqueio bloqueioEfetuado = new Bloqueio(userAgent, ipClient);
                bloqueioRepository.save(bloqueioEfetuado);
                possivelCartao.get().setBloqueio(bloqueioEfetuado);
                cartaoRepository.save(possivelCartao.get());
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }


}
