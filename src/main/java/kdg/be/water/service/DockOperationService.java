package kdg.be.water.service;

import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.domain.order.PurchaseOrder;
import kdg.be.water.repository.BunkerOperationRepository;
import kdg.be.water.repository.DockOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DockOperationService {

    @Autowired
    private DockOperationRepository dockOperationRepository;

    public DockOperation createDockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, List<PurchaseOrder> purchaseOrders) {
        DockOperation dockOperation = new DockOperation(arrivalTime, location, vesselNumber, purchaseOrders);
        InspectionOperation inspectionOperation = new InspectionOperation(LocalDate.now(), true);
        dockOperation.setInspectionOperation(inspectionOperation);
        return dockOperationRepository.save(dockOperation);
    }
}