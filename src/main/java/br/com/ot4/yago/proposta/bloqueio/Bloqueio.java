package br.com.ot4.yago.proposta.bloqueio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataBloqueio = LocalDateTime.now();
    private String userAgent;
    private String ipClient;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String userAgent, String ipClient) {
        this.userAgent = userAgent;
        this.ipClient = ipClient;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpClient() {
        return ipClient;
    }
}
