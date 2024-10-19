package kdg.be.water.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InspectionOperationDTO {
    private UUID id;
    private LocalDate inspectionDate;
    private boolean success;

    public InspectionOperationDTO(UUID id, LocalDate inspectionDate) {
        this.id = id;
        this.inspectionDate = inspectionDate;
    }
}