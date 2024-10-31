package kdg.be.water.repository;

import kdg.be.water.domain.InspectionOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface InspectionOperationRepository extends JpaRepository<InspectionOperation, UUID> {
    List<InspectionOperation> findByIsSuccessfulFalse();
    List<InspectionOperation> findByInspectionDate(LocalDate inspectionDate);
}