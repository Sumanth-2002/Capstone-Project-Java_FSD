package com.ust.sales_service.service;

import com.ust.sales_service.dto.CustomerMonthlyDto;
import com.ust.sales_service.dto.ProductQuantityDto;
import com.ust.sales_service.dto.SalesYearDto;
import com.ust.sales_service.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Metrics {

    @Autowired
    private SalesRepository salesRepository;

    private static final List<String> MONTH_NAMES = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );



    public List<CustomerMonthlyDto> getCustomerPerMonth(Integer year){
        List<Object []> customerData = salesRepository.getCustomersPerMonth(year);
        List<CustomerMonthlyDto> customerMonthlyDtos =MONTH_NAMES.stream()
                .map(month -> {
                    CustomerMonthlyDto dto = new CustomerMonthlyDto();
                    dto.setMonth(month);
                    dto.setNoOfCustomers(0L);
                    return dto;
                }).collect(Collectors.toList());
        for(Object[] obj:customerData){
            Long monthNumber = Long.valueOf(obj[0].toString());
            Long totalCustomers = (Long.valueOf(obj[1].toString()));
            CustomerMonthlyDto dto = customerMonthlyDtos.get((int) (monthNumber - 1));
            dto.setNoOfCustomers(totalCustomers);
        }
        return  customerMonthlyDtos ;
    }

public List<SalesYearDto> getSalePerMonth(int year) {
    List<Object[]> results = salesRepository.getSaleDataMonthly(year);

    List<SalesYearDto> allMonths = MONTH_NAMES.stream()
            .map(month -> {
                SalesYearDto dto = new SalesYearDto();
                dto.setMonth(month);
                dto.setTotalSale(0);
                return dto;
            }).collect(Collectors.toList());


    for (Object[] row : results) {
        int monthNumber = (int) row[0];
        double totalSale = ((Number) row[1]).doubleValue();

        SalesYearDto dto = allMonths.get(monthNumber - 1);
        dto.setTotalSale(totalSale);
    }

    return allMonths;
}

    public List<ProductQuantityDto> getMonthlyProductQuantity(String productName, int year) {
        List<Object[]> results = salesRepository.getMonthlyProductQuantity(productName, year);

        List<ProductQuantityDto> allMonths = MONTH_NAMES.stream()
                .map(month -> {
                    ProductQuantityDto dto = new ProductQuantityDto();
                    dto.setMonth(month);
                    dto.setProductQuantity(0);
                    return dto;
                }).collect(Collectors.toList());

        for (Object[] row : results) {
            int monthNumber = (int) row[0];
            int productQuantity = ((Number) row[1]).intValue();
            ProductQuantityDto dto = allMonths.get(monthNumber - 1);
            dto.setProductQuantity(productQuantity);
        }

        return allMonths;
    }
}
