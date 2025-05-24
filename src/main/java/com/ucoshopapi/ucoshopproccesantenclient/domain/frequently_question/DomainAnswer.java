package com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question;

import jakarta.persistence.*;

@Entity
@Table(name = "answer")
public class DomainAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pregunta_id", nullable = false)
    private Long preguntaId;

    @Column(nullable = false)
    private String respuesta;

    public DomainAnswer() {}

    public DomainAnswer(Long preguntaId, String respuesta) {
        this.preguntaId = preguntaId;
        this.respuesta = respuesta;
    }

    public Long getId() { return id; }
    public Long getPreguntaId() { return preguntaId; }
    public String getRespuesta() { return respuesta; }

    public void setPreguntaId(Long preguntaId) { this.preguntaId = preguntaId; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }
}
