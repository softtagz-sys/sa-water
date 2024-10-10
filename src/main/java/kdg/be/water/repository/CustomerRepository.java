// src/main/java/kdg/be/water/repository/CustomerRepository.java
package kdg.be.water.repository;

import kdg.be.water.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Override
    Optional<Customer> findById(UUID uuid);
}