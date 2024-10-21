package kdg.be.water.service;

import kdg.be.water.controller.dto.PurchaseOrderCompleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class PurchaseOrderService {

    private final RestTemplate restTemplate;

    public PurchaseOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //TODO: when to call this method?
    public void completePurchaseOrder(UUID sellerId, List<String> poNumbers) {
        String url = "http://localhost:8082/api/purchase-orders/complete";
        PurchaseOrderCompleteDTO request = new PurchaseOrderCompleteDTO();
        request.setSellerId(sellerId);
        request.setPoNumbers(poNumbers);

        restTemplate.postForObject(url, request, Void.class);
    }
}