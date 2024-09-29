package kdg.be.water.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kdg.be.water.domain.order.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dock_operations")
@Getter
public class DockOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID dockOperationId;

    @NotBlank(message = "A dock operation must have an arrival time")
    private LocalDateTime arrivalTime;

    @Setter
    private LocalDateTime departureTime;

    @NotBlank(message = "A dock operation must have a location")
    private String location;

    @NotBlank(message = "A dock operation must have a vessel number")
    private String vesselNumber;

    @OneToMany(mappedBy = "dockOperation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();

    @NotNull(message = "A dock operation must have an inspection operation")
    @Setter
    @OneToOne
    @JoinColumn(name = "inspection_operation_id")
    private InspectionOperation inspectionOperation;

    @NotNull(message = "A dock operation must have a bunker operation")
    @OneToOne
    @Setter
    @JoinColumn(name = "bunker_operation_id")
    private BunkerOperation bunkerOperation;

    public DockOperation() {
    }

    public DockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, List<PurchaseOrder> purchaseOrders) {
        this.arrivalTime = arrivalTime;
        this.location = location;
        this.vesselNumber = vesselNumber;
        this.purchaseOrders.addAll(purchaseOrders);
    }

    public DockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, InspectionOperation inspectionOperation, BunkerOperation bunkerOperation) {
        this.arrivalTime = arrivalTime;
        this.location = location;
        this.vesselNumber = vesselNumber;
        this.inspectionOperation = inspectionOperation;
        this.bunkerOperation = bunkerOperation;
    }
}