package com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs;

import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.UtilObject;
import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.UtilText;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "messages")
public class MessageDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pqrs_id", nullable = false)
    private PQRSDomain pqrsId;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp = new Date();

    private MessageDomain(Long id, PQRSDomain pqrsId, String content) {
        setId(id);
        setPqrsId(pqrsId);
        setSender("Admin");
        setContent(content);
        setTimestamp(new Date());
    }

    public MessageDomain(){}

    public static MessageDomain build(final Long id, final PQRSDomain pqrsDomain, final String content) {
        return new MessageDomain(id, pqrsDomain, content);
    }

    public static MessageDomain buildDummy() {
        return new MessageDomain(0L, PQRSDomain.buildDummy(), UtilText.EMPTY);
    }

    public Long getId() {
        return id;
    }

    public PQRSDomain getPqrsId() {
        return pqrsId;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public MessageDomain setId(Long id) {
        this.id = id;
        return this;
    }

    public MessageDomain setPqrsId(PQRSDomain pqrsDomain) {
        this.pqrsId = UtilObject.getUtilObject().getDefault(pqrsDomain, PQRSDomain.buildDummy());
        return this;
    }

    public MessageDomain setSender(String sender) {
        this.sender = UtilText.applyTrim(sender);
        return this;
    }

    public MessageDomain setContent(String content) {
        this.content = UtilText.applyTrim(content);
        return this;
    }

    public MessageDomain setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}

