package kdg.be.water.controller;

import kdg.be.water.domain.DockOperation;
import kdg.be.water.repository.DockOperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DockOperationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DockOperationRepository dockOperationRepository;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/api/dock-operations";
        dockOperationRepository.deleteAll();
    }

    @Test
    public void testCreateAndArriveAtDock() {
        LocalDateTime arrivalTime = LocalDateTime.now();
        String location = "Dock A";
        String vesselNumber = "Vessel 123";
        List<UUID> purchaseOrders = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());

        ResponseEntity<DockOperation> response = restTemplate.postForEntity(baseUrl + "/arrive?arrivalTime=" + arrivalTime + "&location=" + location + "&vesselNumber=" + vesselNumber, purchaseOrders, DockOperation.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getArrivalTime()).isEqualTo(arrivalTime);
        assertThat(response.getBody().getLocation()).isEqualTo(location);
        assertThat(response.getBody().getVesselNumber()).isEqualTo(vesselNumber);
        //assertThat(response.getBody().getPurchaseOrders()).containsAll(purchaseOrders);
    }
}