package com.ust.Purchase_Service.controller;

import com.ust.Purchase_Service.dto.RestockPurchaseData;
import com.ust.Purchase_Service.model.Purchases;
import com.ust.Purchase_Service.service.Purchase_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private Purchase_Service purchase_service;

    @PostMapping
    public Purchases addPurchaseData(@RequestBody Purchases purchases){
        return  purchase_service.addPurchaseData(purchases);
    }

    @GetMapping
    public List<Purchases> getPurchaseData(){
        return purchase_service.getPurchaseData();
    }

    @GetMapping("/total")
    public Double getTotalPurchaseCost(){
        return  purchase_service.getWholePurchase();
    }

    @PostMapping("/add-restock-purchase")
    public Purchases addRestockPurchase(@RequestBody RestockPurchaseData restockPurchaseData){
        return purchase_service.addRestockPurchase(restockPurchaseData);
    }
}
