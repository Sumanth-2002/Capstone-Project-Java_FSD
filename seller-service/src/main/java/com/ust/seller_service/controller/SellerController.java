package com.ust.seller_service.controller;


import com.ust.seller_service.dto.SellerDto;
import com.ust.seller_service.model.Seller;
import com.ust.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<Seller> addSeller(@RequestBody Seller seller){
        return  ResponseEntity.ok(sellerService.addSeller(seller));
    }
    @GetMapping
    public ResponseEntity<List<SellerDto>> getAllSellers(){
        return  ResponseEntity.ok(sellerService.getAllSellers());
    }
}
