package com.ust.sales_service.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ust.sales_service.dto.CustomerSummaryDto;
import com.ust.sales_service.dto.InventoryUpdateDto;
import com.ust.sales_service.model.Customer;
import com.ust.sales_service.model.Sales;
import com.ust.sales_service.repository.CustomerRepository;
import com.ust.sales_service.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Sales addSalesData(Sales sales) {
        Customer customer = sales.getCustomer();
        Long productId = sales.getProductId();
        Optional<Long> inventoryId = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .get()
                .uri("/api/stores/getInventoryId/"+sales.getStoreId())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Long>>() {})
                .block();
        InventoryUpdateDto inventoryUpdateDto = new InventoryUpdateDto();
        inventoryUpdateDto.setInventoryId(inventoryId.get());
        inventoryUpdateDto.setProductId(sales.getProductId());
        inventoryUpdateDto.setStock(sales.getQuantity());

        Optional<Object> object = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .put() // Use POST to send the DTO in the body
                .uri("/api/stores/inventory/updateSale") // Direct URI without query parameters
                .bodyValue(inventoryUpdateDto) // Attach the DTO as the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Object>>() {})
                .block();
        if (customer != null) {
            if (customer.getCustomerId() == null) {
                customer = customerRepository.save(customer);
            }
            sales.setCustomer(customer);
        } else {
            throw new IllegalArgumentException("Customer details are missing in the sales data");
        }
        return salesRepository.save(sales);
    }

    public  List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public void saveSalesDataFromCSV(String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] headers = csvReader.readNext();
            String[] line;

            while ((line = csvReader.readNext()) != null) {
                String customerName = line[3];
                String contactNumber = line[4];

//                Customer customer = customerRepository.findById(customerId).orElse(null);

                  Customer  customer = new Customer();

                    customer.setName(customerName);
                    customer.setContactNumber(contactNumber);
                    customerRepository.save(customer);

                Sales sales = new Sales();
                sales.setStoreId(Long.valueOf(line[1]));
                sales.setCustomer(customer);
                sales.setProductId(Long.valueOf(line[5]));
                sales.setQuantity(Integer.parseInt(line[6]));
                sales.setTotalPrice(Double.valueOf(line[7]));
                sales.setSaleDate(parseDate(line[8]));
                sales.setPaymentMethod(line[9]);

                salesRepository.save(sales);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<CustomerSummaryDto> getCustomerData(Long customerId) {

        List<Object[]> objs = customerRepository.getCustomerDataById(customerId);

        List<CustomerSummaryDto> customerSummaryDtos = new ArrayList<>();


        for (Object[] obj : objs) {
            if (obj.length > 1 && obj[1] != null) { // Ensure obj[1] exists and is not null
                Long productId = ((Number) obj[1]).longValue(); // Convert to Long
                CustomerSummaryDto customerSummaryDto = new CustomerSummaryDto();


                Map<String, Object> productDetails = webClientBuilder
                        .baseUrl("http://localhost:9093")
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/api/products/getProductById/{id}")
                                .build(productId)) // Replace {id} with productId
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .block();

                String productName = productDetails != null ? (String) productDetails.get("name") : "Unknown";


                customerSummaryDto.setCount(((Number) obj[0]).longValue());
                customerSummaryDto.setProduct_id(productId);
                customerSummaryDto.setCustomerName((String) obj[2]);
                customerSummaryDto.setProductName(productName);

                customerSummaryDtos.add(customerSummaryDto);
            }
        }

        return customerSummaryDtos;
    }

}