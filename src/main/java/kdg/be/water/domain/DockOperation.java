package kdg.be.water.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dock_operations")
@Getter
@Setter
public class DockOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID dockOperationId;

    @NotNull
    private LocalDateTime arrivalTime;

    @NotBlank
    private String location;

    @NotBlank
    private String vesselNumber;

    @ElementCollection
    @CollectionTable(name = "purchase_order_numbers", joinColumns = @JoinColumn(name = "dock_operation_id"))
    @Column(name = "purchase_order_number")
    private List<String> purchaseOrderNumbers = new ArrayList<>();

    @NotNull
    private UUID sellerId;

    private boolean hasLeft;

    private boolean isLoaded;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inspection_operation_id")
    private InspectionOperation inspectionOperation;

    @OneToOne
    @JoinColumn(name = "bunker_operation_id")
    private BunkerOperation bunkerOperation;

    public DockOperation() {
    }

    public DockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, List<String> purchaseOrderNumbers, UUID sellerId) {
        this.arrivalTime = arrivalTime;
        this.location = location;
        this.vesselNumber = vesselNumber;
        this.purchaseOrderNumbers.addAll(purchaseOrderNumbers);
        this.sellerId = sellerId;
        this.hasLeft = false;
        this.isLoaded = false;
    }

    public DockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, InspectionOperation inspectionOperation, BunkerOperation bunkerOperation, UUID sellerId) {
        this.arrivalTime = arrivalTime;
        this.location = location;
        this.vesselNumber = vesselNumber;
        this.inspectionOperation = inspectionOperation;
        this.bunkerOperation = bunkerOperation;
        this.sellerId = sellerId;
        this.hasLeft = false;
        this.isLoaded = false;
    }
}