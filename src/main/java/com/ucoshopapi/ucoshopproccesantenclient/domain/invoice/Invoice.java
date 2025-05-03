package com.ucoshopapi.ucoshopproccesantenclient.domain.invoice;

import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.PaymentDomain;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "invoice")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice", nullable = false)
    private UUID idInvoice;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date date;

    @OneToOne
    @JoinColumn(name = "id_payment", referencedColumnName = "id_payment", nullable = false)
    private PaymentDomain payment;

    @PrePersist
    protected void onCreate() {
        this.date = new Date();
    }
}