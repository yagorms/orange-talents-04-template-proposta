package br.com.ot4.yago.proposta.avisoViagem;

import br.com.ot4.yago.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destino;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;
    private LocalDateTime dataAviso = LocalDateTime.now();
    private String ipClient;
    private String userAgent;
    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String destino, LocalDate dataTermino, String ipClient, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public LocalDateTime getDataAviso() {
        return dataAviso;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
