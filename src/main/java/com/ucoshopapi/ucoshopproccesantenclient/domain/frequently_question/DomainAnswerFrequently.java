package com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question;

import jakarta.persistence.*;

@Entity
@Table(name = "AnswerFrequently")
public class DomainAnswerFrequently {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRespuestaFrecuente")
    private Long id;

    @Column(name = "respuestaFrecuente")
    private String respuestaFrecuente;

    @ManyToOne
    @JoinColumn(name = "idPreguntaFrecuente", referencedColumnName = "id")
    private DomainFrequentlyQuestion frequentlyQuestion;

    public DomainAnswerFrequently() {}

    public DomainAnswerFrequently(Long id, String respuestaFrecuente, DomainFrequentlyQuestion frequentlyQuestion) {
        this.id = id;
        this.respuestaFrecuente = respuestaFrecuente;
        this.frequentlyQuestion = frequentlyQuestion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRespuestaFrecuente() {
        return respuestaFrecuente;
    }

    public void setRespuestaFrecuente(String respuestaFrecuente) {
        this.respuestaFrecuente = respuestaFrecuente;
    }

    public DomainFrequentlyQuestion getFrequentlyQuestion() {
        return frequentlyQuestion;
    }

    public void setFrequentlyQuestion(DomainFrequentlyQuestion frequentlyQuestion) {
        this.frequentlyQuestion = frequentlyQuestion;
    }
}