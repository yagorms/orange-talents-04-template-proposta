package br.com.ot4.yago.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaConsultaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Proposta> consultar (@PathVariable Long id){
        Optional<Proposta> proposta = propostaRepository.findById(id);
        if (proposta.isPresent()){
            return ResponseEntity.ok(proposta.get());
        }
        return ResponseEntity.notFound().build();
    }
}
