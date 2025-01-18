package com.ust.sales_service.controller;


import com.ust.sales_service.dto.SalesSummaryDto;
import com.ust.sales_service.model.Customer;
import com.ust.sales_service.model.Sales;
import com.ust.sales_service.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping()
    public Sales addSalesData(@RequestBody Sales sales){
        return salesService.addSalesData(sales);
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return salesService.getAllCustomer();
    }

    @GetMapping("/sales")
    public List<Sales> getAllSales(){
        return salesService.getAllSales();
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("sales_data") MultipartFile file) {
        try {
            // Save the uploaded file to a temporary location
            Path tempFile = Files.createTempFile("sales_data", ".csv");
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Process the file and save data
            salesService.saveSalesDataFromCSV(tempFile.toString());

            // Delete the temporary file
            Files.delete(tempFile);

            return ResponseEntity.ok("Sales data successfully saved!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred while processing the file");
        }
    }

    @GetMapping("/getByDate")
    public List<SalesSummaryDto> getSaleByDate(){
        return salesService.getSalesByDate();
    }
}
