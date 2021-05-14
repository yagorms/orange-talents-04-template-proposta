package br.com.ot4.yago.proposta.cartao;

import br.com.ot4.yago.proposta.proposta.EstadoProposta;
import br.com.ot4.yago.proposta.proposta.Proposta;
import br.com.ot4.yago.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@EnableScheduling
public class CartaoScheduling {

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;


    @Scheduled(fixedDelay = 5000)
    public void buscaCartao(){
        List<Proposta> propostaElegivel = propostaRepository.findByEstadoPropostaAndCartao(EstadoProposta.ELEGIVEL, null);

        for (Proposta proposta : propostaElegivel){
            try {
                CartaoResponse response = cartaoClient.consultaCartao(proposta.getId());
                Cartao cartao = response.converter(cartaoRepository);

                Optional<Cartao> possivelCartao = cartaoRepository.findByNumeroCartao(response.getId());
                if (possivelCartao.isEmpty()){

                    cartaoRepository.save(cartao);
                    proposta.setCartao(cartao);
                    propostaRepository.save(proposta);
                }

            }catch (FeignException e){

            }
        }
    }
}
