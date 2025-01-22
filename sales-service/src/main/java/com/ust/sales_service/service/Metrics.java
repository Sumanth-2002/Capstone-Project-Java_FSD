package com.ust.sales_service.service;

import com.ust.sales_service.dto.CustomerMonthlyDto;
import com.ust.sales_service.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Metrics {

    @Autowired
    private SalesRepository salesRepository;

    public List<Object[]> getProductMetrics(Integer year){
        return salesRepository.getProductMetric(year);
    }
    public List<Object[]> getProductMetricsByMonth(Integer Month,Integer year){
        return salesRepository.getProductMetricByMonthAndYear(Month,year);
    }

    public List<CustomerMonthlyDto> getCustomerPerMonth(Integer year){
        List<Long []> customerData = salesRepository.getCustomersPerMonth(year);
        List<CustomerMonthlyDto> customerMonthlyDtos = new ArrayList<>();
        for(Long[] obj:customerData) {
            CustomerMonthlyDto customerMonthlyDto = new CustomerMonthlyDto();
            customerMonthlyDto.setMonth(obj[1].intValue());
            customerMonthlyDto.setNoOfCustomers(obj[1]);
            customerMonthlyDtos.add(customerMonthlyDto);
        }
        return  customerMonthlyDtos ;
    }
    public List<Double []> getSalePerMonth(Integer year){
        return  salesRepository.getSaleDataMonthly(year);
    }
}
