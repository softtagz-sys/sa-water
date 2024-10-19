package kdg.be.water.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BunkerOperationDTO {
    private LocalDateTime bunkerOperationDate;
    private String vesselNumber;

    public BunkerOperationDTO(LocalDateTime bunkerOperationDate, String vesselNumber) {
        this.bunkerOperationDate = bunkerOperationDate;
        this.vesselNumber = vesselNumber;
    }
}