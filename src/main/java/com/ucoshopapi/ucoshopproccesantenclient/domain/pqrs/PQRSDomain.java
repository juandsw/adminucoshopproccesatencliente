package com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs;


import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.UtilObject;
import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.UtilText;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@Data
@Entity
@Table(name = "pqrs")
public class PQRSDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "date_of_creation", nullable = false)
    private Date dateOfCreation;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "process", nullable = false)
    private String process;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    private PQRSDomain(Long id, String title, String description, String type, Date dateOfCreation, String code, String process, String userEmail) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setType(type);
        setDateOfCreation(dateOfCreation);
        setStatus("Abierto");
        setCode(code);
        setProcess(process);
        setUserEmail(userEmail);
    }

    private PQRSDomain(int zero) {
        setId(0L);
        setTitle(UtilText.EMPTY);
        setDescription(UtilText.EMPTY);
        setType(UtilText.EMPTY);
        setDateOfCreation(new Date());
        setStatus(UtilText.EMPTY);
        setCode(UtilText.EMPTY);
        setProcess(UtilText.EMPTY);
        setUserEmail(UtilText.EMPTY);
    }

    public PQRSDomain(){}

    public static PQRSDomain build(final Long id, final String title, final String description, final String type,
                                   final Date dateOfCreation, final String code,
                                   final String process, String userEmail) {
        return new PQRSDomain(id, title, description, type, dateOfCreation, code, process, userEmail);
    }

    public static PQRSDomain buildDummy() {
        return new PQRSDomain(0L, UtilText.EMPTY, UtilText.EMPTY, UtilText.EMPTY,
                new Date(), UtilText.EMPTY, UtilText.EMPTY, UtilText.EMPTY);
    }

    public PQRSDomain setId(Long id) {
        this.id = id;
        return this;
    }

    public PQRSDomain setTitle(String title) {
        this.title = UtilText.applyTrim(title);
        return this;
    }

    public PQRSDomain setDescription(String description) {
        this.description = UtilText.applyTrim(description);
        return this;
    }

    public PQRSDomain setType(String type) {
        this.type = UtilText.applyTrim(type);
        return this;
    }

    public PQRSDomain setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = UtilObject.getUtilObject().getDefault(dateOfCreation, new Date());
        return this;
    }

    public PQRSDomain setStatus(String status) {
        this.status = UtilText.applyTrim(status);
        return this;
    }

    public PQRSDomain setCode(String code) {
        this.code = UtilText.applyTrim(code);
        return this;
    }

    public PQRSDomain setProcess(String process) {
        this.process = UtilText.applyTrim(process);
        return this;
    }

    public PQRSDomain setUserEmail(String userEmail) {
        this.userEmail = UtilText.applyTrim(userEmail);
        return this;
    }

    
}