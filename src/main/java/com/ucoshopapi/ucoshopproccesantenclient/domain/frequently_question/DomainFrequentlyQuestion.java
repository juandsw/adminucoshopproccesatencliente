package com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "frequentlyQuestion")
public class DomainFrequentlyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String proceso;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idPreguntaFrecuente")
    private List<DomainAnswerFrequently> respuestaFrecuente = new ArrayList<>();

    public DomainFrequentlyQuestion() {
    }

    public DomainFrequentlyQuestion(Long id, String titulo, String proceso, List<DomainAnswerFrequently> respuestaFrecuente) {
        this.id = id;
        this.titulo = titulo;
        this.proceso = proceso;
        this.respuestaFrecuente = respuestaFrecuente != null ? respuestaFrecuente : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getProceso() {
        return proceso;
    }

    public List<DomainAnswerFrequently> getRespuestaFrecuente() {
        return respuestaFrecuente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public void setRespuestaFrecuente(List<DomainAnswerFrequently> respuestaFrecuente) {
        this.respuestaFrecuente = respuestaFrecuente;
    }
}
