package kdg.be.water.service;

import jakarta.transaction.Transactional;
import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.domain.domainclass.ShipOverview;
import kdg.be.water.repository.DockOperationRepository;
import kdg.be.water.repository.InspectionOperationRepository;
import kdg.be.water.sender.PurchaseOrderSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DockOperationService {

    private static final Logger logger = LoggerFactory.getLogger(DockOperationService.class);

    private final DockOperationRepository dockOperationRepository;
    private final InspectionOperationRepository inspectionOperationRepository;
    private final PurchaseOrderSender purchaseOrderSender;

    public DockOperationService(DockOperationRepository dockOperationRepository, InspectionOperationRepository inspectionOperationRepository, PurchaseOrderSender purchaseOrderSender) {
        this.dockOperationRepository = dockOperationRepository;
        this.inspectionOperationRepository = inspectionOperationRepository;
        this.purchaseOrderSender = purchaseOrderSender;
    }

    @Transactional
    public DockOperation createDockOperation(DockOperation dockOperation) {
        try {
            InspectionOperation inspectionOperation = createInspectionOperation(dockOperation);
            dockOperation.setInspectionOperation(inspectionOperation);

            logger.info("Saving DockOperation with vessel number: {}", dockOperation.getVesselNumber());
            DockOperation savedDockOperation = dockOperationRepository.save(dockOperation);

            completePurchaseOrderAndSetLoaded(savedDockOperation);

            return savedDockOperation;
        } catch (IllegalArgumentException iae) {
            logger.error("Invalid argument error creating DockOperation: ", iae);
            throw iae;
        }
    }

    private InspectionOperation createInspectionOperation(DockOperation dockOperation) {
        logger.info("Creating InspectionOperation for DockOperation with arrival time: {}", dockOperation.getArrivalTime());
        InspectionOperation inspectionOperation = new InspectionOperation(dockOperation.getArrivalTime().toLocalDate());
        return inspectionOperationRepository.save(inspectionOperation);
    }

    private void completePurchaseOrderAndSetLoaded(DockOperation dockOperation) {
        boolean isPurchaseOrderCompleted = purchaseOrderSender.completePurchaseOrder(dockOperation.getSellerId(), dockOperation.getPurchaseOrderNumbers());

        if (isPurchaseOrderCompleted) {
            dockOperation.setLoaded(true);
            dockOperationRepository.save(dockOperation);
        }
    }

    public ShipOverview getOverview(String vesselNumber) {
        DockOperation dockOperation = dockOperationRepository.findByVesselNumber(vesselNumber)
                .orElseThrow(() -> new RuntimeException("DockOperation not found"));

        InspectionOperation inspectionOperation = dockOperation.getInspectionOperation();
        boolean inspectionSuccess = inspectionOperation.isSuccessful();

        BunkerOperation bunkerOperation = dockOperation.getBunkerOperation();
        boolean bunkerOperationPlanned = (bunkerOperation != null);
        boolean bunkerOperationSuccess = bunkerOperationPlanned && bunkerOperation.isSuccessful();

        return new ShipOverview(inspectionSuccess, bunkerOperationPlanned, bunkerOperationSuccess);
    }

    @Transactional
    public void leave(String vesselNumber) {
        ShipOverview overview = getOverview(vesselNumber);

        if (!overview.isInspectionSuccess()) {
            throw new RuntimeException("Inspection operation is not successful. Ship cannot leave.");
        }

        if (overview.isBunkerOperationPlanned() && !overview.isBunkerOperationSuccess()) {
            throw new RuntimeException("Bunker operation is not successful. Ship cannot leave.");
        }

        DockOperation dockOperation = dockOperationRepository.findByVesselNumber(vesselNumber)
                .orElseThrow(() -> new RuntimeException("DockOperation not found"));
        dockOperation.setHasLeft(true);
        dockOperationRepository.save(dockOperation);
        logger.info("Ship with vessel number {} has left the dock", vesselNumber);
    }
}