package com.ust.Purchase_Service.service;

import com.ust.Purchase_Service.model.Purchases;
import com.ust.Purchase_Service.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Purchase_Service {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchases addPurchaseData(Purchases purchases){
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
