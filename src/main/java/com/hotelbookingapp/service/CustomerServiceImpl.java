package com.hotelbookingapp.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotelbookingapp.dto.CustomerDTO;
import com.hotelbookingapp.entity.Customer;
import com.hotelbookingapp.exception.CustomerNotFoundException;
import com.hotelbookingapp.mapper.CustomerMapper;
import com.hotelbookingapp.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;
	
	@Override
	public CustomerDTO addCustomer(CustomerDTO customerDTO) {
		
		Customer customer = customerMapper.toCustomer(customerDTO);		
		return customerMapper.toCustomerDTO(customerRepository.save(customer));
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		
		Customer customer = customerRepository.findById(customerDTO.getCustomerId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		customer.setCustomerName(customerDTO.getCustomerName());
		customer.setCustomerAddress(customerDTO.getCustomerAddress());
		customer.setCustomerEmail(customerDTO.getCustomerEmail());
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setCustomerMobile(customerDTO.getCustomerMobile());
		
		return customerMapper.toCustomerDTO(customerRepository.save(customer));
				
	}

	@Override
	public List<CustomerDTO> viewAllCustomer() {
		List<Customer> customers = customerRepository.findAll();
		
		if(customers.isEmpty()) {
			throw new CustomerNotFoundException("No customer avaialable");
		}
			
		return customers.stream()
				.map(customerMapper::toCustomerDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO findByContactNo(Long contactNo) {
		Customer customer = customerRepository.findByCustomerMobile(contactNo)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found for the given contact"));
		return customerMapper.toCustomerDTO(customer);
	}

	@Override
	public CustomerDTO findByEmail(String emailId) {
		
		Customer customer = customerRepository.findByCustomerEmail(emailId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found for the given email address"));
		return customerMapper.toCustomerDTO(customer);
	}
	
	@Override
	public CustomerDTO findById(Long id){
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(id)
				.orElseThrow(()->new CustomerNotFoundException("Customer not found for the given ID"));
		return customerMapper.toCustomerDTO(customer);
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		CustomerDTO customer = findById(id);
		customerRepository.delete(customerMapper.toCustomer(customer));
	}

	@Override
	public void deleteAll() {
		
		List<Customer> customers = customerRepository.findAll();
		
		if(customers.isEmpty())throw new CustomerNotFoundException("No Customer Avaialable");
		
	    customerRepository.deleteAll();
	}
	
}
