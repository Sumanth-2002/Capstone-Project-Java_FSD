package com.ust.Purchase_Service.service;

import com.ust.Purchase_Service.dto.InventoryUpdateDto;
import com.ust.Purchase_Service.model.Purchases;
import com.ust.Purchase_Service.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Purchase_Service {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Purchases addPurchaseData(Purchases purchases){
        Optional<Long> inventoryId = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .get()
                .uri("/api/stores/getInventoryId/"+purchases.getStoreId())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Long>>() {})
                .block();
        InventoryUpdateDto inventoryUpdateDto = new InventoryUpdateDto();
        inventoryUpdateDto.setInventoryId(inventoryId.get());
        inventoryUpdateDto.setProductId(purchases.getProductId());
        inventoryUpdateDto.setStock(purchases.getQuantity());

        Optional<Object> object = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .put() // Use POST to send the DTO in the body
                .uri("/api/stores/inventory/updatePurchase") // Direct URI without query parameters
                .bodyValue(inventoryUpdateDto) // Attach the DTO as the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Object>>() {})
                .block();

        return  purchaseRepository.save(purchases);
    }

    public List<Purchases> getPurchaseData(){
        return  purchaseRepository.findAll();
    }

    public Double getWholePurchase(){
        return purchaseRepository.findAll().stream()
                .mapToDouble(Purchases::getTotalCost)
                .sum();
    }
    public Double getPurchaseByDate(Date startDate,Date endDate){
        return purchaseRepository.getPurchaseDataByDate(startDate,endDate);
    }

    public List<Object[]> getPurchaseByYear(){
        return purchaseRepository.getTotalCostByYear();
    }
    public List<Object[]> getPurchaseByMonth(){
        return purchaseRepository.getTotalCostByMonth();
    }

}
