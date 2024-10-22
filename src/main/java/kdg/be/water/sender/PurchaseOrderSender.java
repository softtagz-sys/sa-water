package kdg.be.water.sender;

import kdg.be.water.controller.dto.PurchaseOrderCompleteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class PurchaseOrderSender {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderSender.class);

    private final RestTemplate restTemplate;
    private final String purchaseOrderCompleteUrl;

    @Autowired
    public PurchaseOrderSender(RestTemplate restTemplate, @Value("${purchase.order.complete.url}") String purchaseOrderCompleteUrl) {
        this.restTemplate = restTemplate;
        this.purchaseOrderCompleteUrl = purchaseOrderCompleteUrl;
    }

    public void completePurchaseOrder(UUID sellerId, List<String> poNumbers) {
        PurchaseOrderCompleteDTO request = new PurchaseOrderCompleteDTO(sellerId, poNumbers);

        logger.info("Sending request to complete purchase order with sellerId: {} and poNumbers: {}", sellerId, poNumbers);

        ResponseEntity<Void> response = restTemplate.postForEntity(purchaseOrderCompleteUrl, request, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Purchase order completed successfully.");
        } else {
            logger.error("Failed to complete purchase order. Status code: {}", response.getStatusCode());
        }
    }
}