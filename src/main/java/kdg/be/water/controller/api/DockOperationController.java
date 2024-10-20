package kdg.be.water.controller.api;

import kdg.be.water.domain.DockOperation;
import kdg.be.water.controller.dto.DockOperationDTO;
import kdg.be.water.domain.Customer;
import kdg.be.water.service.CustomerService;
import kdg.be.water.service.DockOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/dock-operations")
public class DockOperationController {
    private final DockOperationService dockOperationService;

    private final CustomerService customerService;

    public DockOperationController(DockOperationService dockOperationService, CustomerService customerService) {
        this.dockOperationService = dockOperationService;
        this.customerService = customerService;
    }

    @PostMapping("/arrive")
    @PreAuthorize("hasAuthority('captain')")
    public ResponseEntity<DockOperation> createAndArriveAtDock(@RequestBody DockOperationDTO dockOperationDTO) {
        UUID customerId = dockOperationDTO.getCustomerId();
        Customer seller = customerService.getCustomerById(customerId);
        DockOperation dockOperation = dockOperationService.createDockOperation(
                dockOperationDTO.getArrivalTime(),
                dockOperationDTO.getLocation(),
                dockOperationDTO.getVesselNumber(),
                dockOperationDTO.getPurchaseOrderNumbers(),
                seller
        );
        return ResponseEntity.ok(dockOperation);
    }
}