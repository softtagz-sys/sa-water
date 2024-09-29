package kdg.be.water.controller;

import kdg.be.water.domain.InspectionOperation;
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

    @GetMapping("/by-date")
    public List<InspectionOperation> getInspectionOperationsByDate(@RequestParam("date") LocalDate date) {
        return inspectionOperationService.getInspectionOperationsByDate(date);
    }

    @PutMapping("/set-success/{id}")
    public Optional<InspectionOperation> setInspectionSuccess(@PathVariable UUID id) {
        return inspectionOperationService.setInspectionSuccess(id);
    }
}