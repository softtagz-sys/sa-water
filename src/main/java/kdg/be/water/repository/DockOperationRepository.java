package kdg.be.water.repository;

import kdg.be.water.domain.DockOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DockOperationRepository extends JpaRepository<DockOperation, UUID> {
    Optional<DockOperation> findByVesselNumber(String vesselNumber);
}