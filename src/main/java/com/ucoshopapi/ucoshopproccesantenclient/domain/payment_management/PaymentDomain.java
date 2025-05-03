package com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_payment", nullable = false)
    private UUID idPayment;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "id_payment_method", referencedColumnName = "id_PaymentMethod", nullable = false)
    private PaymentMethodDomain paymentMethod;

    @ManyToOne
    @JoinColumn(name = "id_buy", referencedColumnName = "id_buy", nullable = false)
    private BuyDomain buy;

    public PaymentDomain() {
        super();
    }

    public PaymentDomain(UUID idPayment, Boolean status, String description, double total, String shippingAddress, PaymentMethodDomain paymentMethod, BuyDomain buy) {
        this.idPayment = idPayment;
        this.status = status;
        this.description = description;
        this.total = total;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.buy = buy;
    }
}
