package com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question;

import jakarta.persistence.*;

@Entity
@Table(name = "AnswerFrequently")
public class DomainAnswerFrequently {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRespuestaFrecuente")
    private Long id;

    @Column(name = "respuestaFrecuente", nullable = false)
    private String respuestaFrecuente;

    public DomainAnswerFrequently() {
    }

    public DomainAnswerFrequently(Long id, String respuestaFrecuente) {
        this.id = id;
        this.respuestaFrecuente = respuestaFrecuente;
    }

    public Long getId() {
        return id;
    }

    public String getRespuestaFrecuente() {
        return respuestaFrecuente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRespuestaFrecuente(String respuestaFrecuente) {
        this.respuestaFrecuente = respuestaFrecuente;
    }
}
