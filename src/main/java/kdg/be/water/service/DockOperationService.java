package kdg.be.water.service;

import jakarta.transaction.Transactional;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.repository.DockOperationRepository;
import kdg.be.water.repository.InspectionOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}