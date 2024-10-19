package kdg.be.water.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DockOperationDTO {
    private LocalDateTime arrivalTime;
    private String location;
    private String vesselNumber;
    private List<String> purchaseOrderNumbers;
    private UUID customerId;

    public DockOperationDTO(LocalDateTime arrivalTime, String location, String vesselNumber, List<String> purchaseOrderNumbers, UUID customerId) {
        this.arrivalTime = arrivalTime;
        this.location = location;
        this.vesselNumber = vesselNumber;
        this.purchaseOrderNumbers = purchaseOrderNumbers;
        this.customerId = customerId;
    }
}