package kdg.be.water.controller.api;

import kdg.be.water.controller.dto.ShipOverviewDTO;
import kdg.be.water.domain.DockOperation;
import kdg.be.water.controller.dto.DockOperationDTO;
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

    public DockOperationController(DockOperationService dockOperationService) {
        this.dockOperationService = dockOperationService;
    }

    @PostMapping("/arrive")
    @PreAuthorize("hasAuthority('captain')")
    public ResponseEntity<DockOperation> createAndArriveAtDock(@RequestBody DockOperationDTO dockOperationDTO) {
        DockOperation dockOperation = new DockOperation(
                dockOperationDTO.getArrivalTime(),
                dockOperationDTO.getLocation(),
                dockOperationDTO.getVesselNumber(),
                dockOperationDTO.getPurchaseOrderNumbers(),
                dockOperationDTO.getSellerId()
        );
        DockOperation savedDockOperation = dockOperationService.createDockOperation(dockOperation);
        return ResponseEntity.ok(savedDockOperation);
    }

    @GetMapping("/overview/{id}")
    @PreAuthorize("hasAuthority('captain')")
    public ShipOverviewDTO getOverview(@PathVariable UUID id) {
        return dockOperationService.getOverview(id);
    }

    //TODO: how do we need to handle this?
    @PostMapping("/leave/{id}")
    @PreAuthorize("hasAuthority('captain')")
    public void leave(@PathVariable UUID id) {
        dockOperationService.leave(id);
    }
}