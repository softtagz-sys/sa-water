package kdg.be.water.controller.api;

import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.controller.dto.BunkerOperationDTO;
import kdg.be.water.service.BunkerOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bunker-operations")
public class BunkerOperationController {

    @Autowired
    private BunkerOperationService bunkerOperationService;

    @PostMapping("/plan")
    public BunkerOperation planBunkerOperation(@RequestBody BunkerOperationDTO bunkerOperationDTO) {
        return bunkerOperationService.createBunkerOperation(
                bunkerOperationDTO.getBunkerOperationDate(),
                bunkerOperationDTO.getVesselNumber()
        );
    }
}