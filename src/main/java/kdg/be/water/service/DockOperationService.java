package kdg.be.water.service;

import kdg.be.water.domain.DockOperation;
import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.domain.Customer;
import kdg.be.water.repository.DockOperationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DockOperationService {

    private final DockOperationRepository dockOperationRepository;

    public DockOperationService(DockOperationRepository dockOperationRepository) {
        this.dockOperationRepository = dockOperationRepository;
    }

    public DockOperation createDockOperation(LocalDateTime arrivalTime, String location, String vesselNumber, List<String> purchaseOrderNumbers, Customer seller) {
        DockOperation dockOperation = new DockOperation(arrivalTime, location, vesselNumber, purchaseOrderNumbers);
        InspectionOperation inspectionOperation = new InspectionOperation(LocalDate.now());
        dockOperation.setInspectionOperation(inspectionOperation);

        return dockOperationRepository.save(dockOperation);
    }
}
