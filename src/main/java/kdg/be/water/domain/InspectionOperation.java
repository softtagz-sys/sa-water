package kdg.be.water.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private boolean isSuccessful;

    public InspectionOperation() {
    }

    public InspectionOperation(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }
}