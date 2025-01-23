package com.ust.Purchase_Service.service;

import com.ust.Purchase_Service.dto.InventoryUpdateDto;
import com.ust.Purchase_Service.dto.RestockPurchaseData;
import com.ust.Purchase_Service.model.Purchases;
import com.ust.Purchase_Service.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public Purchases addPurchaseData(Purchases purchases) {
        Long inventoryId = webClientBuilder.build()
                .get()
                .uri("http://localhost:9094/api/stores/getInventoryId/{storeId}", purchases.getStoreId())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Long>>() {})
                .blockOptional()
                .orElseThrow(() -> new RuntimeException("Inventory ID not found for store ID: " + purchases.getStoreId()))
                .orElseThrow(() -> new RuntimeException("Inventory ID is missing for store ID: " + purchases.getStoreId()));

        InventoryUpdateDto inventoryUpdateDto = new InventoryUpdateDto();
        inventoryUpdateDto.setInventoryId(inventoryId);
        inventoryUpdateDto.setProductId(purchases.getProductId());
        inventoryUpdateDto.setStock(purchases.getQuantity());

        webClientBuilder.build()
                .put()
                .uri("http://localhost:9094/api/stores/inventory/updatePurchase")
                .bodyValue(inventoryUpdateDto)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return purchaseRepository.save(purchases);
    }

    public List<Purchases> getPurchaseData() {
        return purchaseRepository.findAll();
    }

    public Double getWholePurchase() {
        return purchaseRepository.findAll().stream()
                .mapToDouble(Purchases::getTotalCost)
                .sum();
    }

    public Double getPurchaseByDate(Date startDate, Date endDate) {
        return purchaseRepository.getPurchaseDataByDate(startDate, endDate);
    }

    public Purchases addRestockPurchase(RestockPurchaseData restock) {
        Map<String, Object> product = webClientBuilder.build()
                .get()
                .uri("http://localhost:9093/api/products/getProductById/{productId}", restock.getProductId())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .blockOptional()
                .orElseThrow(() -> new RuntimeException("Product not found for product ID: " + restock.getProductId()));

        Double pricePerUnit = (Double) product.get("price");
        if (pricePerUnit == null) {
            throw new RuntimeException("Price not found for product ID: " + restock.getProductId());
        }

        Double totalCost = restock.getQuantity() * pricePerUnit;

        Purchases purchases = new Purchases(
                restock.getStoreId(),
                restock.getProductId(),
                restock.getQuantity(),
                pricePerUnit,
                totalCost,
                restock.getPurchaseDate()
        );
        Long inventoryId = webClientBuilder.build()
                .get()
                .uri("http://localhost:9094/api/stores/getInventoryId/{storeId}", purchases.getStoreId())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Long>>() {})
                .blockOptional()
                .orElseThrow(() -> new RuntimeException("Inventory ID not found for store ID: " + purchases.getStoreId()))
                .orElseThrow(() -> new RuntimeException("Inventory ID is missing for store ID: " + purchases.getStoreId()));

        InventoryUpdateDto inventoryUpdateDto = new InventoryUpdateDto();
        inventoryUpdateDto.setInventoryId(inventoryId);
        inventoryUpdateDto.setProductId(restock.getProductId());
        inventoryUpdateDto.setStock(restock.getQuantity());
        webClientBuilder.build()
                .put()
                .uri("http://localhost:9094/api/stores/inventory/updatePurchase")
                .bodyValue(inventoryUpdateDto)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return purchaseRepository.save(purchases);
    }
}