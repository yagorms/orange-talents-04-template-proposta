package br.com.ot4.yago.proposta.meter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MinhasMetricas {

    private final MeterRegistry meterRegistry;

    private final Collection<String> strings = new ArrayList<>();

    private final Random random = new Random();

    public MinhasMetricas(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        criarGauge();
    }

    public void criarGauge() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "Mastercard"));
        tags.add(Tag.of("banco", "Itaú"));

        this.meterRegistry.gauge("meu_gauge", tags, strings, Collection::size);
    }

    public void removeString() {
        strings.removeIf(Objects::nonNull);
    }

    public void addString() {
        strings.add(UUID.randomUUID().toString());
    }

    @Scheduled(fixedDelay = 1000)
    public void simulandoGauge() {
        double randomNumber = random.nextInt();
        if (randomNumber % 2 == 0) {
            addString();
        } else {
            removeString();
        }
    }

    public void meuContador() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "Mastercard"));
        tags.add(Tag.of("banco", "Itaú"));

        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);
        contadorDePropostasCriadas.increment();
    }

//*********ESTE EXEMPLO NÃO FUNCIONOU MAS FICA AQUI DE MODELO***********
//    public void meuTimer(){
//        Collection<Tag> tags = new ArrayList<>();
//        tags.add(Tag.of("emissora", "Mastercard"));
//        tags.add(Tag.of("banco", "Itaú"));
//
//        Timer timerConsultarProposta = this.meterRegistry.timer("consultar_proposta", tags);
//        timerConsultarProposta.record(() -> {
//            // Método da sua operação
//            consultarProposta();
//        });
//    }

}
