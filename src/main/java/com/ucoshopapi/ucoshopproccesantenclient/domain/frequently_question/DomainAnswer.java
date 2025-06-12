package com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question;

import jakarta.persistence.*;

@Entity
@Table(name = "answer")
public class DomainAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pregunta_id", nullable = false)
    private DomainQuestion question;

    @Column(nullable = false)
    private String respuesta;

    public DomainAnswer() {}

    public DomainAnswer(DomainQuestion question, String respuesta) {
        this.question = question;
        this.respuesta = respuesta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DomainQuestion getQuestion() {
        return question;
    }

    public void setQuestion(DomainQuestion question) {
        this.question = question;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}