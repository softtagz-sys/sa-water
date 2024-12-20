package kdg.be.water.controller.api;

import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.controller.dto.BunkerOperationDTO;
import kdg.be.water.service.BunkerOperationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bunker-operations")
public class BunkerOperationController {

    private final BunkerOperationService bunkerOperationService;

    public BunkerOperationController(BunkerOperationService bunkerOperationService) {
        this.bunkerOperationService = bunkerOperationService;
    }

    @PostMapping("/plan")
    @PreAuthorize("hasAuthority('tanker')")
    public BunkerOperation planBunkerOperation(@RequestBody BunkerOperationDTO bunkerOperationDTO) {
        return bunkerOperationService.createBunkerOperation(
                bunkerOperationDTO.getBunkerOperationDate(),
                bunkerOperationDTO.getVesselNumber()
        );
    }

    @PostMapping("/success/{vesselNumber}")
    @PreAuthorize("hasAuthority('tanker')")
    public BunkerOperation setBunkerOperationSuccess(@PathVariable String vesselNumber) {
        return bunkerOperationService.setBunkerOperationSuccess(vesselNumber);
    }
}