package kdg.be.water.controller;

import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.service.BunkerOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bunker-operations")
public class BunkerOperationController {

    @Autowired
    private BunkerOperationService bunkerOperationService;

    @PostMapping("/plan")
    public BunkerOperation planBunkerOperation(@RequestParam LocalDateTime bunkerOperationDate, @RequestParam String vesselNumber) {
        return bunkerOperationService.createBunkerOperation(bunkerOperationDate, vesselNumber);
    }
}