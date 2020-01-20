package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.exception.CustomerNotFoundException;
import com.example.repository.CustomerRepository;

@Service
public class CustomerService {
	
	Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	CustomerRepository customerRepository;

	public Customer save(Customer customer) {
		logger.info("saving customer {}", customer);
		return customerRepository.save(customer);
	}

	public Customer findById(String id) {
		return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
	}

	public void deleteById(String id) {
		logger.info("deleting customer id {}", id);
		customerRepository.deleteById(id);
	}

}
