package com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment_method")
public class PaymentMethodDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_PaymentMethod", nullable = false)
    private UUID idPaymentMethod;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "id_bank", referencedColumnName = "id_bank", nullable = false)
    private BankDomain bank;

    public PaymentMethodDomain() {}

    public PaymentMethodDomain(UUID idPaymentMethod, String cardNumber, BankDomain bank) {
        this.idPaymentMethod = idPaymentMethod;
        this.cardNumber = cardNumber;
        this.bank = bank;
    }
}
