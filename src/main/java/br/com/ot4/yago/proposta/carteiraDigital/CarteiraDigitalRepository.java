package br.com.ot4.yago.proposta.carteiraDigital;

import br.com.ot4.yago.proposta.cartao.Cartao;
import br.com.ot4.yago.proposta.carteiraDigital.feign.ResultadoFeign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
    Optional<CarteiraDigital> findByCartaoAndCarteiraAndResultado(Cartao cartao, TipoCarteira carteira, ResultadoFeign associada);
}
