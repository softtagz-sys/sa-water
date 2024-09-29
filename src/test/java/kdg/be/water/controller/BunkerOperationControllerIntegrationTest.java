package kdg.be.water.controller;

import kdg.be.water.domain.BunkerOperation;
import kdg.be.water.repository.BunkerOperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BunkerOperationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BunkerOperationRepository bunkerOperationRepository;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/bunker-operations";
        bunkerOperationRepository.deleteAll();
    }

    @Test
    public void testPlanBunkerOperation() {
        LocalDateTime bunkerOperationDate = LocalDateTime.now();
        String vesselNumber = "Vessel 428";

        ResponseEntity<BunkerOperation> response = restTemplate.postForEntity(baseUrl + "/plan?bunkerOperationDate=" + bunkerOperationDate + "&vesselNumber=" + vesselNumber, null, BunkerOperation.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getBunkerOperationDate()).isEqualTo(bunkerOperationDate);
        assertThat(response.getBody().getVesselNumber()).isEqualTo(vesselNumber);
    }
}