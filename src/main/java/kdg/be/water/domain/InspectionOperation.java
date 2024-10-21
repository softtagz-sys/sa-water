package kdg.be.water.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "inspection_operations")
@Getter
public class InspectionOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID inspectionOperationId;

    @NotNull
    private LocalDate inspectionDate;

    @Setter
    private boolean inspectionSuccess;

    public InspectionOperation() {
    }

    public InspectionOperation(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }
}