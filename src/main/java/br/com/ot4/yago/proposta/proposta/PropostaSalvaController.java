package br.com.ot4.yago.proposta.proposta;

import br.com.ot4.yago.proposta.proposta.Feign.RestricaoClient;
import br.com.ot4.yago.proposta.proposta.Feign.VerificaRestricaoRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaSalvaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private RestricaoClient restricaoClient;

    @PostMapping

    public ResponseEntity<?> cadastrar (@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriComponentsBuilder){
        Proposta proposta = form.converter(propostaRepository);

        Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(form.getDocumento());

        if (possivelProposta.isPresent()){

            return ResponseEntity.unprocessableEntity().body("Já existe uma proposta para esse documento");
        }
        else {
            propostaRepository.save(proposta);

            try{
                restricaoClient.verificaRestricao(new VerificaRestricaoRequest(proposta));
                proposta.setEstadoProposta(EstadoProposta.ELEGIVEL);

            } catch (FeignException e){
                if (e.status() == 422){
                    proposta.setEstadoProposta(EstadoProposta.NAO_ELEGIVEL);
                }
                else {
                    throw e;
                }
            }
            propostaRepository.save(proposta);

            URI uri = uriComponentsBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).body(new PropostaDTO(proposta));

        }
    }
}
