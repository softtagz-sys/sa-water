package kdg.be.water.controller.api;

import kdg.be.water.domain.DockOperation;
import kdg.be.water.controller.dto.DockOperationDTO;
import kdg.be.water.domain.domainclass.ShipOverview;
import kdg.be.water.service.DockOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/overview/{vesselNumber}")
    @PreAuthorize("hasAuthority('captain')")
    public ShipOverview getOverview(@PathVariable String vesselNumber) {
        return dockOperationService.getOverview(vesselNumber);
    }

    @PostMapping("/leave/{vesselNumber}")
    @PreAuthorize("hasAuthority('captain')")
    public void leave(@PathVariable String vesselNumber) {
        dockOperationService.leave(vesselNumber);
    }
}