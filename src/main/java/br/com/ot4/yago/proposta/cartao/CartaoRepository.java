package br.com.ot4.yago.proposta.cartao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByNumeroCartao(String id);
}
