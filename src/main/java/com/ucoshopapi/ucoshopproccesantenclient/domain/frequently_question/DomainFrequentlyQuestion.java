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

    @OneToMany(mappedBy = "frequentlyQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DomainAnswerFrequently> respuestaFrecuente = new ArrayList<>();

    public DomainFrequentlyQuestion() {
    }

    public DomainFrequentlyQuestion(Long id, String titulo, String proceso, List<DomainAnswerFrequently> respuestaFrecuente) {
        this.id = id;
        this.titulo = titulo;
        this.proceso = proceso;
        if (respuestaFrecuente != null) {
            this.respuestaFrecuente = respuestaFrecuente;
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public List<DomainAnswerFrequently> getRespuestaFrecuente() {
        return respuestaFrecuente;
    }

    public void setRespuestaFrecuente(List<DomainAnswerFrequently> respuestaFrecuente) {
        this.respuestaFrecuente = (respuestaFrecuente != null) ? respuestaFrecuente : new ArrayList<>();
    }
}