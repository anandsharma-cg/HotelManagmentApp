package com.hotelbookingapp.mapper;

import org.springframework.stereotype.Component;
import com.hotelbookingapp.dto.CustomerDTO;
import com.hotelbookingapp.entity.Customer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomerMapper {

    public CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerMobile(customer.getCustomerMobile());
        customerDTO.setCustomerEmail(customer.getCustomerEmail());
        customerDTO.setCustomerAddress(customer.getCustomerAddress());
        return customerDTO;
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerMobile(customerDTO.getCustomerMobile());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());
        return customer;
    }
}
