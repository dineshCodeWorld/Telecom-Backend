package com.telecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.dto.CallDetailsDTO;
import com.telecom.dto.CustomerDTO;
import com.telecom.dto.FriendFamilyDTO;
import com.telecom.dto.LoginDTO;
import com.telecom.dto.UpdateDto;
import com.telecom.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerService custService;

	@PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody CustomerDTO custDTO) {
		custService.createCustomer(custDTO);
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean login(@RequestBody LoginDTO loginDTO) {
		return custService.login(loginDTO);
	}

	@GetMapping(value = "/customers/{phoneNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) {
		return custService.getCustomerProfile(phoneNo);
	}

	@GetMapping(value = "/customers/{phoneNo}/calldetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {
		return custService.getCustomerCallDetails(phoneNo);
	}

	@PostMapping(value = "/customers/{phoneNo}/friends", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		custService.saveFriend(phoneNo, friendDTO);
	}

	@GetMapping("/customers/all")
	public List<CustomerDTO> getAllUsers() {
		return custService.getAllUsers();
	}

	@PutMapping(value = "/customers/update/{phoneNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String update(@PathVariable Long phoneNo, @RequestBody UpdateDto updateDto) {
		try {
			return custService.update(phoneNo, updateDto);
		} catch (Exception e) {
			return "Some error occurred, please try again later.";
		}
	}

}
