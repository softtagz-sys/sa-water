package kdg.be.water.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipOverviewDTO {
    private boolean inspectionSuccess;
    private boolean bunkerOperationPlanned;
    private boolean bunkerOperationSuccess;

    public ShipOverviewDTO(boolean inspectionSuccess, boolean bunkerOperationPlanned, boolean bunkerOperationSuccess) {
        this.inspectionSuccess = inspectionSuccess;
        this.bunkerOperationPlanned = bunkerOperationPlanned;
        this.bunkerOperationSuccess = bunkerOperationSuccess;
    }
}