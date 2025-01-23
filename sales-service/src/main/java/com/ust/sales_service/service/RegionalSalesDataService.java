package com.ust.sales_service.service;

import com.ust.sales_service.dto.RegionalSalesDto;
import com.ust.sales_service.dto.StoreSalesDto;
import com.ust.sales_service.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegionalSalesDataService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<RegionalSalesDto> getRegionWiseSalesData() {
        List<Map<String, Object>> obe = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .get()
                .uri("/api/stores/getALlStores")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .block();

        List<RegionalSalesDto> regSales = new ArrayList<>();
        if (obe != null) {
            for (Map<String, Object> obj : obe) {
                RegionalSalesDto regionalSalesDto = new RegionalSalesDto();
                regionalSalesDto.setRegion((String) obj.get("region"));
                List<Long> storeIds = (List<Long>) obj.get("storeId");
                Double totalSale = salesRepository.getTotalSaleDataRegion(storeIds);
                if(totalSale!=null)
                regionalSalesDto.setTotalSale(totalSale);
                else
                    regionalSalesDto.setTotalSale(0.0);
                regSales.add(regionalSalesDto);
            }
        }
        return regSales;

    }

    public List<StoreSalesDto> getStoreByRegion(String region){
        List<StoreSalesDto> storeSalesDtoList = new ArrayList<>();
        List<Map<String,Object>> list = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .get()
                .uri("/api/stores/getStoreByRegion?regionName="+region)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String,Object>>>() {})
                .block();
      for(Map<String,Object> obj:list){
          StoreSalesDto storeSalesDto = new StoreSalesDto();
          Long storeId = Long.valueOf(obj.get("storeId").toString());

          storeSalesDto.setStoreId(storeId);
          storeSalesDto.setStoreName((String)obj.get("storeName"));
          Double totalSale = salesRepository.getStoreSaleData(storeId);
          if(totalSale!=null) storeSalesDto.setTotalSale(totalSale);
          else storeSalesDto.setTotalSale(0.0);
          storeSalesDtoList.add(storeSalesDto);
      }
        return  storeSalesDtoList;
    }
}
