package com.ust.seller_service.service;

import com.ust.seller_service.SellerServiceApplication;
import com.ust.seller_service.dto.SellerDto;
import com.ust.seller_service.model.Seller;
import com.ust.seller_service.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;



    public Seller addSeller(Seller seller){
        return  sellerRepository.save(seller);
    }
    public List<SellerDto> getAllSellers(){
        List<SellerDto> sellerDtoList = new ArrayList<>();

        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller: sellers){
            SellerDto sellerDto = new SellerDto();
            sellerDto.setSellerId(seller.getSellerId());
            sellerDto.setName(seller.getName());
            sellerDtoList.add(sellerDto);
        }
        return sellerDtoList;
    }
}
