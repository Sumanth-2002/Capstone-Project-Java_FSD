package com.ust.sales_service.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ust.sales_service.dto.CustomerSummaryDto;
import com.ust.sales_service.dto.SalesSummaryDto;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        
        if (customer != null) {
            if (customer.getCustomerId() == null) {
                customer = customerRepository.save(customer);
            }
            sales.setCustomer(customer);
        } else {
            throw new IllegalArgumentException("Customer details are missing in the sales data");
        }
        return salesRepository.save(sales); // Save the sales entity
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
                sales.setStoreId(Integer.parseInt(line[1]));
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

    public List<Sales> getAllSales(){
        return salesRepository.findAll();
    }

    public List<SalesSummaryDto> getSalesByDate(){

        List<Object[]> results = salesRepository.getSalesSummaryByDate();
        List<SalesSummaryDto> salesSummary = new ArrayList<>();
        System.out.println(results);

        for (Object[] record : results) {
            Date saleDate = (Date) record[0];
            Double totalPrice = ((Double) record[1]).doubleValue(); // Second column: SUM(total_price)
            Long totalSales = ((Long) record[2]).longValue();     // Third column: COUNT(sale_id)

            salesSummary.add(new SalesSummaryDto(saleDate, totalPrice, totalSales));
        }

        return salesSummary;
    }
    public List<CustomerSummaryDto> getCustomerData(Long customerId) {

        // Fetch customer data from the repository
        List<Object[]> objs = customerRepository.getCustomerDataById(customerId);

        List<CustomerSummaryDto> customerSummaryDtos = new ArrayList<>();

        // Loop through the fetched data and make API calls for each customer
        for (Object[] obj : objs) {
            if (obj.length > 1 && obj[1] != null) { // Ensure obj[1] exists and is not null
                Long productId = ((Number) obj[1]).longValue(); // Convert to Long
                CustomerSummaryDto customerSummaryDto = new CustomerSummaryDto();

                // Make API call to fetch product details
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

                // Extract product name from API response
                String productName = productDetails != null ? (String) productDetails.get("name") : "Unknown";

                // Populate CustomerSummaryDto
                customerSummaryDto.setCount(((Number) obj[0]).longValue());
                customerSummaryDto.setProduct_id(productId);
                customerSummaryDto.setCustomerName((String) obj[2]);
                customerSummaryDto.setProductName(productName);

                // Add to the list
                customerSummaryDtos.add(customerSummaryDto);
            }
        }

        return customerSummaryDtos;
    }

}