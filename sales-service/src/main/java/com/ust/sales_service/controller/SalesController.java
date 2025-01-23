package com.ust.sales_service.controller;


import com.ust.sales_service.dto.*;
import com.ust.sales_service.model.Customer;
import com.ust.sales_service.model.Sales;
import com.ust.sales_service.service.Metrics;
import com.ust.sales_service.service.RegionalSalesDataService;
import com.ust.sales_service.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private Metrics metrics;
    @Autowired
    private RegionalSalesDataService regionalSalesDataService;

    @PostMapping()
    public Sales addSalesData(@RequestBody Sales sales){
        return salesService.addSalesData(sales);
    }
    @GetMapping("/customer-metrics/customers")
    public List<Customer> getAllCustomer(){
        return salesService.getAllCustomer();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("sales_data") MultipartFile file) {
        try {
            Path tempFile = Files.createTempFile("sales_data", ".csv");
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            salesService.saveSalesDataFromCSV(tempFile.toString());
            Files.delete(tempFile);
            return ResponseEntity.ok("Sales data successfully saved!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred while processing the file");
        }
    }
    @GetMapping("/getCustomerData/{customerId}")
    public List<CustomerSummaryDto> getCustomerData(@PathVariable Long customerId){
        return salesService.getCustomerData(customerId);
    }

    @GetMapping("/region-wise-sales")
    public List<RegionalSalesDto>getSalesByRegion(){
        return regionalSalesDataService.getRegionWiseSalesData();
    }
    @GetMapping("/getSalesRegion/{region}")
    public List<StoreSalesDto> getStoreSaleData(@PathVariable String region){
        return regionalSalesDataService.getStoreByRegion(region);
    }


    @GetMapping("/customer-metrics/{year}")
    public List<CustomerMonthlyDto> getCustomerByYear(@PathVariable Integer year){
        return metrics.getCustomerPerMonth(year);
    }
    @GetMapping("/sale-metrics/{year}")
    public List<SalesYearDto> getSaleByYear(@PathVariable Integer year){
        return metrics.getSalePerMonth(year);
    }
    @GetMapping("/monthly-product-quantity")
    public List<ProductQuantityDto> getMonthlyProductQuantity(
            @RequestParam String productName,
            @RequestParam int year) {
        return metrics.getMonthlyProductQuantity(productName, year);
    }


}
