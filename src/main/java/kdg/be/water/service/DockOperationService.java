package kdg.be.water.service;

import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.domain.order.PurchaseOrder;
import kdg.be.water.repository.DockOperationRepository;
import kdg.be.water.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DockOperationService {

    @Autowired
    private DockOperationRepository dockOperationRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public DockOperation createDockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, List<UUID> purchaseOrderIds) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderIds.stream()
                .map(id -> purchaseOrderRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("PurchaseOrder not found for ID: " + id)))
                .collect(Collectors.toList());

        DockOperation dockOperation = new DockOperation(arrivalTime, location, vesselNumber, purchaseOrders);
        InspectionOperation inspectionOperation = new InspectionOperation(LocalDate.now(), true);
        dockOperation.setInspectionOperation(inspectionOperation);
        return dockOperationRepository.save(dockOperation);
    }
}