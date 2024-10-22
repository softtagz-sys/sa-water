package kdg.be.water.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class PurchaseOrderCompleteDTO {
    private UUID sellerId;
    private List<String> poNumbers;

    public PurchaseOrderCompleteDTO() {
    }

    public PurchaseOrderCompleteDTO(UUID sellerId, List<String> poNumbers) {
        this.sellerId = sellerId;
        this.poNumbers = poNumbers;
    }
}
