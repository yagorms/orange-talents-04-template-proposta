package br.com.ot4.yago.proposta.proposta;

import br.com.ot4.yago.proposta.meter.MinhasMetricas;
import br.com.ot4.yago.proposta.proposta.Feign.RestricaoClient;
import br.com.ot4.yago.proposta.proposta.Feign.VerificaRestricaoRequest;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
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

    private final Tracer tracer;

    public PropostaSalvaController(Tracer tracer){
        this.tracer = tracer;
    }

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private RestricaoClient restricaoClient;

    @Autowired
    private MinhasMetricas minhasMetricas;

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriComponentsBuilder){

        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", "yago@yago.com");
        activeSpan.setBaggageItem("user.email", "yago@yago.com");
        activeSpan.log("Meu log");

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

            minhasMetricas.meuContador();

            URI uri = uriComponentsBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).body(new PropostaDTO(proposta));

        }
    }
}
