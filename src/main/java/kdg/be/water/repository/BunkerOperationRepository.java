package kdg.be.water.repository;

import kdg.be.water.domain.BunkerOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BunkerOperationRepository extends JpaRepository<BunkerOperation, UUID> {
    List<BunkerOperation> findAllByBunkerOperationDateBetween(LocalDateTime start, LocalDateTime end);
}