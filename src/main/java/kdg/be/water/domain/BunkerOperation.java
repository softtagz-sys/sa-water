package kdg.be.water.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bunker_operations")
@Getter
public class BunkerOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bunkerOperationId;

    @NotBlank(message = "A bunker operation must have a vessel number")
    private String vesselNumber;

    @NotBlank(message = "A bunker operation must have a bunker operation date")
    private LocalDateTime bunkerOperationDate;

    @Setter
    private boolean isSuccessful;

    public BunkerOperation() {
    }

    public BunkerOperation(String vesselNumber, LocalDateTime bunkerOperationDate) {
        this.vesselNumber = vesselNumber;
        this.bunkerOperationDate = bunkerOperationDate;
    }
}