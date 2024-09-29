package kdg.be.water.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bunker_operations")
@Getter
public class BunkerOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bunkerOperationId;

    @Column(nullable = false)
    private String vesselNumber;

    @Column(nullable = false)
    private Date timestamp;

    @Setter
    @Column(nullable = false)
    private boolean isSuccessful;

    public BunkerOperation() {
    }

    public BunkerOperation(String vesselNumber, Date timestamp, boolean isSuccessful) {
        this.vesselNumber = vesselNumber;
        this.timestamp = timestamp;
        this.isSuccessful = isSuccessful;
    }
}