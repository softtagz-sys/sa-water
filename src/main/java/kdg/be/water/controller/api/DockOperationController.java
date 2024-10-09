package kdg.be.water.controller.api;

import kdg.be.water.domain.DockOperation;
import kdg.be.water.controller.dto.DockOperationDTO;
import kdg.be.water.service.DockOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dock-operations")
public class DockOperationController {

    @Autowired
    private DockOperationService dockOperationService;

    @PostMapping("/arrive")
    public ResponseEntity<DockOperation> createAndArriveAtDock(@RequestBody DockOperationDTO dockOperationDTO) {
        DockOperation dockOperation = dockOperationService.createDockOperation(
                dockOperationDTO.getArrivalTime(),
                dockOperationDTO.getLocation(),
                dockOperationDTO.getVesselNumber(),
                dockOperationDTO.getPurchaseOrders()
        );
        return ResponseEntity.ok(dockOperation);
    }
}