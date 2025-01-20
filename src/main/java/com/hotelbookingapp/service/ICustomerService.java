package com.hotelbookingapp.service;

import java.util.List;
import com.hotelbookingapp.dto.CustomerDTO;

public interface ICustomerService {

	CustomerDTO addCustomer(CustomerDTO customer);

	CustomerDTO updateCustomer(CustomerDTO customer);

	List<CustomerDTO> viewAllCustomer();

	CustomerDTO findByContactNo(Long contactNo);

	CustomerDTO findByEmail(String emailId);
	
	void deleteById(Long id);
	
	CustomerDTO findById(Long id);

	void deleteAll();
}
