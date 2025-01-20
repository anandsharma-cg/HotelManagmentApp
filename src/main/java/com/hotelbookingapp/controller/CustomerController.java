package com.hotelbookingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbookingapp.dto.CustomerDTO;
import com.hotelbookingapp.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId,@Valid @RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomerId(customerId);
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> viewAllCustomers() {
        List<CustomerDTO> customers = customerService.viewAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/contact/{contactNo}")
    public ResponseEntity<CustomerDTO> findByContactNo(@PathVariable Long contactNo) {
        CustomerDTO customer = customerService.findByContactNo(contactNo);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{emailId}")
    public ResponseEntity<CustomerDTO> findByEmail(@PathVariable String emailId) {
        CustomerDTO customer = customerService.findByEmail(emailId);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        CustomerDTO customer = customerService.findById(id);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus>deleteByID(@PathVariable Long id){
		customerService.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

    @DeleteMapping
    public ResponseEntity<HttpStatus>deleteAll(){
    	customerService.deleteAll();
    	return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    
}
