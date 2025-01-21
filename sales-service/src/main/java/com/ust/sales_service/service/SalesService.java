package com.ust.sales_service.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ust.sales_service.dto.SalesSummaryDto;
import com.ust.sales_service.model.Customer;
import com.ust.sales_service.model.Sales;
import com.ust.sales_service.repository.CustomerRepository;
import com.ust.sales_service.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Sales addSalesData(Sales sales) {
        Customer customer = sales.getCustomer();
        if (customer != null) {
            if (customer.getCustomerId() == null) {
                // Save the customer to ensure it exists in the DB
                customer = customerRepository.save(customer);
            }
            sales.setCustomer(customer); // Associate the saved customer with the sales
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
            String[] headers = csvReader.readNext(); // Read and ignore the header row
            String[] line;

            while ((line = csvReader.readNext()) != null) {
                // Parse and create Customer object
//                Long customerId = Long.valueOf(line[2]);
                String customerName = line[3];
                String contactNumber = line[4];

//                Customer customer = customerRepository.findById(customerId).orElse(null);

                  Customer  customer = new Customer();
//                    customer.setCustomerId(customerId);
                    customer.setName(customerName);
                    customer.setContactNumber(contactNumber);
                    customerRepository.save(customer);


                // Parse and create Sales object
                Sales sales = new Sales();
                sales.setStoreId(Integer.parseInt(line[1]));
                sales.setCustomer(customer);
                sales.setProductId(Long.valueOf(line[5]));
                sales.setQuantity(Integer.parseInt(line[6]));
                sales.setTotalPrice(Double.valueOf(line[7]));
                sales.setSaleDate(parseDate(line[8]));
                sales.setPaymentMethod(line[9]);

                // Save Sales record to the database
                salesRepository.save(sales);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    // Utility method to parse date
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
    public List<Object []> getCustomerDat(){
        return customerRepository.getCustomerDataById();
    }

}
