package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(path="/", method={RequestMethod.PUT, RequestMethod.POST})
	public Customer save(@RequestBody final Customer customer) {
		return customerService.save(customer);
	}
	
	@GetMapping("/{id}")
	public Customer findById(@PathVariable final String id) {
		return customerService.findById(id);
	}
	
	@PreAuthorize("hasRole('USER') and #oauth2.isUser()")
	@PostAuthorize("returnObject.name == authentication.name")
	@GetMapping("/security/{id}")
	public Customer findBySecId(@PathVariable final String id, Authentication authentication) {
		return customerService.findById(id);
	}
	
	@PreAuthorize("(hasRole('ADMIN') or hasRole('CUSTOMER_HAPPINESS')) and #oauth2.isUser() and #oauth2.hasScope('READ')")
	@GetMapping("/security/admin/{id}")
	public Customer findBySecAdminId(@PathVariable final String id, Authentication authentication) {
		
		var userName = authentication.getName();
		var clientId = ((OAuth2Authentication)authentication).getOAuth2Request().getClientId();
		
		return customerService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable final String id) {
		customerService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
