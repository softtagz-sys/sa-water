package kdg.be.water.domain.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "orderlines")
@Getter
public class Orderline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderlineId;

    @NotBlank(message = "A orderline must have a line number")
    private int lineNumber;

    @NotBlank(message = "A orderline must have a material type")
    private String materialType;

    @NotBlank(message = "A orderline must have a quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    public Orderline() {
    }

    public Orderline(int lineNumber, String materialType, int quantity, PurchaseOrder purchaseOrder) {
        this.lineNumber = lineNumber;
        this.materialType = materialType;
        this.quantity = quantity;
        this.purchaseOrder = purchaseOrder;
    }
}