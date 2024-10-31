package kdg.be.water.sender;

import kdg.be.water.controller.dto.PurchaseOrderCompleteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Component
public class PurchaseOrderSender {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderSender.class);

    private final RestTemplate restTemplate;
    private final String purchaseOrderCompleteUrl;

    public PurchaseOrderSender(RestTemplate restTemplate, @Value("${purchase.order.complete.url}") String purchaseOrderCompleteUrl) {
        this.restTemplate = restTemplate;
        this.purchaseOrderCompleteUrl = purchaseOrderCompleteUrl;
    }

    public boolean completePurchaseOrder(UUID sellerId, List<String> poNumbers) {
        String url = UriComponentsBuilder.fromHttpUrl(purchaseOrderCompleteUrl)
                .queryParam("sellerId", sellerId.toString())
                .toUriString();

        logger.info("Sending request to complete purchase order with sellerId: {} and poNumbers: {}", sellerId, poNumbers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, poNumbers, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Purchase order completed successfully.");
            return true;
        } else {
            logger.error("Failed to complete purchase order. Status code: {}", response.getStatusCode());
            return false;
        }
    }
}