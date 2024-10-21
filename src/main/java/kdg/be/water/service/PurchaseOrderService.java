package kdg.be.water.service;

import kdg.be.water.controller.dto.PurchaseOrderCompleteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class PurchaseOrderService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderService.class);

    private final RestTemplate restTemplate;

    @Autowired
    public PurchaseOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void completePurchaseOrder(UUID sellerId, List<String> poNumbers) {
        String url = "http://localhost:8082/api/purchase-orders/complete";
        PurchaseOrderCompleteDTO request = new PurchaseOrderCompleteDTO(sellerId, poNumbers);

        logger.info("Sending request to complete purchase order with sellerId: {} and poNumbers: {}", sellerId, poNumbers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Purchase order completed successfully.");
        } else {
            logger.error("Failed to complete purchase order. Status code: {}", response.getStatusCode());
        }
    }
}