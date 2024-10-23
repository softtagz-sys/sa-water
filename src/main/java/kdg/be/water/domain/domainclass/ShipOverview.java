package kdg.be.water.domain.domainclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipOverview {
    private boolean inspectionSuccess;
    private boolean bunkerOperationPlanned;
    private boolean bunkerOperationSuccess;

    public ShipOverview(boolean inspectionSuccess, boolean bunkerOperationPlanned, boolean bunkerOperationSuccess) {
        this.inspectionSuccess = inspectionSuccess;
        this.bunkerOperationPlanned = bunkerOperationPlanned;
        this.bunkerOperationSuccess = bunkerOperationSuccess;
    }
}