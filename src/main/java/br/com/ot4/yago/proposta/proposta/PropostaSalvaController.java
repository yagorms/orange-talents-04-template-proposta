package br.com.ot4.yago.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaSalvaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaDTO> cadastrar (@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriComponentsBuilder){
        Proposta proposta = form.converter(propostaRepository);
        if (proposta != null){
            propostaRepository.save(proposta);

            URI uri = uriComponentsBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).body(new PropostaDTO(proposta));
        }
        else return ResponseEntity.badRequest().build();
    }
}
