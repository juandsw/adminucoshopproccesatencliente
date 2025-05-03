package com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "buy")
public class BuyDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_buy", nullable = false)
    private UUID idBuy;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    public BuyDomain() {
        super();
    }

    public BuyDomain(UUID idBuy, String customerName) {
        this.idBuy = idBuy;
        this.customerName = customerName;
    }
}
