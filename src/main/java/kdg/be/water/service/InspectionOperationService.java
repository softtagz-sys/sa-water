package kdg.be.water.service;

import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.repository.InspectionOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InspectionOperationService {

    @Autowired
    private InspectionOperationRepository inspectionOperationRepository;

    public List<InspectionOperation> getOpenInspectionOperations() {
        return inspectionOperationRepository.findByInspectionSuccessFalse();
    }

    public List<InspectionOperation> getInspectionOperationsByDate(LocalDate inspectionDate) {
        return inspectionOperationRepository.findByInspectionDate(inspectionDate);
    }

    //TODO: do we need to automaticly set it?
    public Optional<InspectionOperation> setInspectionSuccess(UUID id) {
        Optional<InspectionOperation> inspectionOperation = inspectionOperationRepository.findById(id);
        inspectionOperation.ifPresent(inspection -> {
            inspection.setInspectionSuccess(true);
            inspectionOperationRepository.save(inspection);
        });
        return inspectionOperation;
    }
}