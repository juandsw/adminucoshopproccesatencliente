package com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "preguntas_frecuentes")
public class DomainFrequentlyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String proceso;

    @ElementCollection
    @CollectionTable(name = "respuestas_frecuentes", joinColumns = @JoinColumn(name = "pregunta_id"))
    @Column(name = "respuesta")
    private List<String> respuestas;

    public DomainFrequentlyQuestion() {}

    public DomainFrequentlyQuestion(String titulo, String proceso, List<String> respuestas) {
        this.titulo = titulo;
        this.proceso = proceso;
        this.respuestas = respuestas;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getProceso() { return proceso; }
    public List<String> getRespuestas() { return respuestas; }

    public void setProceso(String proceso) { this.proceso = proceso; }
    public void setRespuestas(List<String> respuestas) { this.respuestas = respuestas; }
}
