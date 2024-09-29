package kdg.be.water.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "inspection_operations")
@Getter
public class InspectionOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID inspectionOperationId;

    @NotBlank(message = "An inspection operation must have an inspection date")
    private Date inspectionDate;

    @Setter
    private boolean inspectionSuccess;

    public InspectionOperation() {
    }

    public InspectionOperation(Date inspectionDate, boolean inspectionSuccess) {
        this.inspectionDate = inspectionDate;
        this.inspectionSuccess = inspectionSuccess;
    }
}