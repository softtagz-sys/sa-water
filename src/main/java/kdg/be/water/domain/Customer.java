package kdg.be.water.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customerId;

    @Column(unique = true)
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    public Customer() {
    }

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
