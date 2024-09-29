package kdg.be.water.domain.order;

import jakarta.persistence.*;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.customer.Customer;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchase_orders")
@Getter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID purchaseOrderId;

    @ManyToOne
    @JoinColumn(name = "dock_operation_id")
    private DockOperation dockOperation;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Customer seller;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orderline> orderlines;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Customer seller, List<Orderline> orderlines) {
        this.seller = seller;
        this.orderlines = orderlines;
    }
}