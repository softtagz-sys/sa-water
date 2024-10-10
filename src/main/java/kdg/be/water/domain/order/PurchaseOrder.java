package kdg.be.water.domain.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchase_orders")
@Getter
@Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID purchaseOrderId;

    @Column(name = "purchase_order_number")
    private String purchaseOrderNumber;

    @ManyToOne
    @JoinColumn(name = "dock_operation_id")
    private DockOperation dockOperation;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "seller_id")
    private Customer seller;


    public PurchaseOrder() {
    }

    public PurchaseOrder(Customer seller, String purchaseOrderNumber) {
        this.seller = seller;
        this.purchaseOrderNumber = purchaseOrderNumber;
    }
}