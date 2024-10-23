package kdg.be.water.service;

import jakarta.transaction.Transactional;
import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.repository.BunkerOperationRepository;
import kdg.be.water.repository.DockOperationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BunkerOperationService {

    private final BunkerOperationRepository bunkerOperationRepository;

    private final DockOperationRepository dockOperationRepository;

    @Value("${bunker.operation.max-per-day}")
    private int maxBunkerOperationsPerDay;

    @Value("${bunker.operation.duration-hours}")
    private int bunkerOperationDurationHours;

    public BunkerOperationService(BunkerOperationRepository bunkerOperationRepository, DockOperationRepository dockOperationRepository) {
        this.bunkerOperationRepository = bunkerOperationRepository;
        this.dockOperationRepository = dockOperationRepository;
    }

    @Transactional
    public BunkerOperation createBunkerOperation(LocalDateTime bunkerOperationDate, String vesselNumber) {
        LocalDateTime startOfDay = bunkerOperationDate.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<BunkerOperation> operationsToday = bunkerOperationRepository.findAllByBunkerOperationDateBetween(startOfDay, endOfDay);

        if (operationsToday.size() >= maxBunkerOperationsPerDay) {
            throw new IllegalStateException("Maximum number of bunker operations for the day reached");
        }

        Optional<DockOperation> dockOperationOptional = dockOperationRepository.findByVesselNumber(vesselNumber);
        if (!dockOperationOptional.isPresent()) {
            throw new IllegalArgumentException("DockOperation not found for vessel number: " + vesselNumber);
        }

        BunkerOperation bunkerOperation = new BunkerOperation(bunkerOperationDate, vesselNumber);
        BunkerOperation savedBunkerOperation = bunkerOperationRepository.save(bunkerOperation);

        DockOperation dockOperation = dockOperationOptional.get();
        dockOperation.setBunkerOperation(savedBunkerOperation);
        dockOperationRepository.save(dockOperation);

        return savedBunkerOperation;
    }

    public BunkerOperation setBunkerOperationSuccess(String vesselNumber) {
        Optional<BunkerOperation> bunkerOperationOptional = bunkerOperationRepository.findByVesselNumber(vesselNumber);
        if (!bunkerOperationOptional.isPresent()) {
            throw new IllegalArgumentException("BunkerOperation not found for vessel number: " + vesselNumber);
        }

        BunkerOperation bunkerOperation = bunkerOperationOptional.get();
        bunkerOperation.setSuccessful(true);
        return bunkerOperationRepository.save(bunkerOperation);
    }
}