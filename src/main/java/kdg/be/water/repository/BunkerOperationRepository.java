package kdg.be.water.repository;

import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.domain.DockOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BunkerOperationRepository extends JpaRepository<BunkerOperation, UUID> {
    List<BunkerOperation> findAllByBunkerOperationDateBetween(LocalDateTime start, LocalDateTime end);
    Optional<BunkerOperation> findByVesselNumber(String vesselNumber);
}