package kdg.be.water.controller;

import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.domain.order.PurchaseOrder;
import kdg.be.water.service.DockOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/dock-operations")
public class DockOperationController {

    @Autowired
    private DockOperationService dockOperationService;

    @PostMapping("/arrive")
    public ResponseEntity<DockOperation> createAndArriveAtDock(@RequestParam LocalDateTime arrivalTime,
                                                               @RequestParam String location,
                                                               @RequestParam String vesselNumber,
                                                               @RequestBody List<PurchaseOrder> purchaseOrders) {
        DockOperation dockOperation = dockOperationService.createDockOperation(arrivalTime, location, vesselNumber, purchaseOrders);
        return ResponseEntity.ok(dockOperation);
    }
}