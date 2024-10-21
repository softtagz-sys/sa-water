package kdg.be.water.service;

import jakarta.transaction.Transactional;
import kdg.be.water.controller.dto.ShipOverviewDTO;
import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.repository.DockOperationRepository;
import kdg.be.water.repository.InspectionOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DockOperationService {

    private static final Logger logger = LoggerFactory.getLogger(DockOperationService.class);

    private final DockOperationRepository dockOperationRepository;
    private final InspectionOperationRepository inspectionOperationRepository;

    public DockOperationService(DockOperationRepository dockOperationRepository, InspectionOperationRepository inspectionOperationRepository) {
        this.dockOperationRepository = dockOperationRepository;
        this.inspectionOperationRepository = inspectionOperationRepository;
    }

    @Transactional
    public DockOperation createDockOperation(DockOperation dockOperation) {
        try {
            logger.info("Creating InspectionOperation for DockOperation with arrival time: {}", dockOperation.getArrivalTime());
            InspectionOperation inspectionOperation = new InspectionOperation(dockOperation.getArrivalTime().toLocalDate());
            inspectionOperation = inspectionOperationRepository.save(inspectionOperation);
            dockOperation.setInspectionOperation(inspectionOperation);

            logger.info("Saving DockOperation with vessel number: {}", dockOperation.getVesselNumber());
            return dockOperationRepository.save(dockOperation);
        } catch (Exception e) {
            logger.error("Error creating DockOperation: ", e);
            throw e;
        }
    }

    public ShipOverviewDTO getOverview(UUID id) {
        DockOperation dockOperation = dockOperationRepository.findById(id).orElseThrow(() -> new RuntimeException("DockOperation not found"));

        InspectionOperation inspectionOperation = dockOperation.getInspectionOperation();
        boolean inspectionSuccess = inspectionOperation.isSuccessful();

        BunkerOperation bunkerOperation = dockOperation.getBunkerOperation();
        boolean bunkerOperationPlanned = (bunkerOperation != null);
        boolean bunkerOperationSuccess = bunkerOperationPlanned && bunkerOperation.isSuccessful();

        return new ShipOverviewDTO(inspectionSuccess, bunkerOperationPlanned, bunkerOperationSuccess);
    }

    public void leave(UUID id) {
        DockOperation dockOperation = dockOperationRepository.findById(id).orElseThrow(() -> new RuntimeException("DockOperation not found"));
        dockOperation.setLeft(true);
        dockOperationRepository.save(dockOperation);
        logger.info("Ship with ID {} has left the dock", id);
    }
}