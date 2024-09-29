package kdg.be.water.controller;

import kdg.be.water.domain.InspectionOperation;
import kdg.be.water.repository.InspectionOperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class InspectionOperationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InspectionOperationRepository inspectionOperationRepository;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/inspection-operations";
        inspectionOperationRepository.deleteAll();
    }

    @Test
    public void testGetOpenInspectionOperations() {
        InspectionOperation io = new InspectionOperation();
        io.setInspectionSuccess(false);
        inspectionOperationRepository.save(io);

        ResponseEntity<InspectionOperation[]> response = restTemplate.getForEntity(baseUrl + "/open", InspectionOperation[].class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void testGetInspectionOperationsByDate() {
        LocalDate date = LocalDate.now();
        InspectionOperation io = new InspectionOperation();
        io.setInspectionDate(date);
        inspectionOperationRepository.save(io);

        ResponseEntity<InspectionOperation[]> response = restTemplate.getForEntity(baseUrl + "/by-date?date=" + date, InspectionOperation[].class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void testSetInspectionSuccess() {
        InspectionOperation io = new InspectionOperation();
        io.setInspectionSuccess(false);
        io = inspectionOperationRepository.save(io);

        restTemplate.put(baseUrl + "/set-success/" + io.getInspectionOperationId(), null);

        InspectionOperation updatedIo = inspectionOperationRepository.findById(io.getInspectionOperationId()).orElse(null);
        assertThat(updatedIo).isNotNull();
        assertThat(updatedIo.isInspectionSuccess()).isTrue();
    }
}