package kdg.be.water.controller.api;

import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.controller.dto.InspectionOperationDTO;
import kdg.be.water.service.InspectionOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/inspection-operations")
public class InspectionOperationController {

    @Autowired
    private InspectionOperationService inspectionOperationService;

    @GetMapping("/open")
    public List<InspectionOperation> getOpenInspectionOperations() {
        return inspectionOperationService.getOpenInspectionOperations();
    }

    @GetMapping("/date")
    public List<InspectionOperation> getInspectionOperationsByDate(@RequestParam("date") LocalDate date) {
        return inspectionOperationService.getInspectionOperationsByDate(date);
    }

    @PutMapping("/success")
    public Optional<InspectionOperation> setInspectionSuccess(@RequestBody InspectionOperationDTO inspectionOperationDTO) {
        return inspectionOperationService.setInspectionSuccess(
                inspectionOperationDTO.getId()
        );
    }
}