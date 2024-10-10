package kdg.be.water.service;

import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.domain.customer.Customer;
import kdg.be.water.domain.order.PurchaseOrder;
import kdg.be.water.repository.DockOperationRepository;
import kdg.be.water.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DockOperationService {

    @Autowired
    private DockOperationRepository dockOperationRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public DockOperation createDockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, List<String> purchaseOrderNumbers, Customer seller) {
        DockOperation dockOperation = new DockOperation(arrivalTime, location, vesselNumber, null);
        InspectionOperation inspectionOperation = new InspectionOperation(LocalDate.now());
        dockOperation.setInspectionOperation(inspectionOperation);

        List<PurchaseOrder> purchaseOrders = purchaseOrderNumbers.stream()
                .map(number -> {
                    PurchaseOrder purchaseOrder = new PurchaseOrder(seller, number);
                    purchaseOrder.setDockOperation(dockOperation);
                    return purchaseOrder;
                })
                .collect(Collectors.toList());

        purchaseOrders.forEach(purchaseOrderRepository::save);
        dockOperation.setPurchaseOrders(purchaseOrders);

        return dockOperationRepository.save(dockOperation);
    }
}