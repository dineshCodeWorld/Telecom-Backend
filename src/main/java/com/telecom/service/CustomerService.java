package com.telecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.telecom.dto.CallDetailsDTO;
import com.telecom.dto.CustomerDTO;
import com.telecom.dto.FriendFamilyDTO;
import com.telecom.dto.LoginDTO;
import com.telecom.dto.UpdateDto;
import com.telecom.entity.CallDetails;
import com.telecom.entity.Customer;
import com.telecom.entity.FriendFamily;
import com.telecom.entity.Plan;
import com.telecom.repository.CallDetailsRepository;
import com.telecom.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository custRepo;

	@Autowired
	CallDetailsRepository callDetailsRepo;

	public void createCustomer(CustomerDTO custDTO) {
		Customer cust = custDTO.createEntity();
		Plan p = new Plan();
		p.setPlanId(custDTO.getCurrentPlan().getPlanId());
		cust.setPlan(p);
		custRepo.save(cust);
	}

	public boolean login(LoginDTO loginDTO) {
		Boolean flag = false;
		Optional<Customer> cust;
		cust = custRepo.findById(loginDTO.getPhoneNo());
		if (cust.isPresent() && cust.get() != null && cust.get().getPassword().equals(loginDTO.getPassword())) {
			flag = true;
		}
		return flag;
	}

	public CustomerDTO getCustomerProfile(Long phoneNo) {
		CustomerDTO custDTO = null;

		Optional<Customer> cust = custRepo.findById(phoneNo);
		if (cust.isPresent())
			custDTO = CustomerDTO.valueOf(cust.get());

		return custDTO;
	}

	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {

		List<CallDetails> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
		List<CallDetailsDTO> callsDTO = new ArrayList<>();

		for (CallDetails call : callDetails) {
			callsDTO.add(CallDetailsDTO.valueOf(call));
		}

		return callsDTO;
	}

	public void saveFriend(Long phoneNo, FriendFamilyDTO friendDTO) {
		friendDTO.setPhoneNo(phoneNo);
		FriendFamily friend = friendDTO.createFriend();
		List<FriendFamily> friendList = new ArrayList<>();
		friendList.add(friend);
		Customer c = custRepo.getOne(phoneNo);
		c.getFriends().add(friend);
		c.setFriends(c.getFriends());
		custRepo.save(c);
	}

	public List<CustomerDTO> getAllUsers() {
		List<Customer> customers = custRepo.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerDTO customerDTO = CustomerDTO.valueOf(customer);
			customerDTOs.add(customerDTO);
		}
		return customerDTOs;

	}

	public String update(Long phoneNo, UpdateDto updateDto) {
		Optional<Customer> cust = custRepo.findById(phoneNo);
		if (cust.isPresent()) {
			if (updateDto.getName() != null) {
				cust.get().setName(updateDto.getName());
			}
			if (updateDto.getAddress() != null) {
				cust.get().setAddress(updateDto.getAddress());
			}
			if (updateDto.getAge() != null) {
				cust.get().setAge(updateDto.getAge());
			}
			if (updateDto.getPassword() != null) {
				cust.get().setPassword(updateDto.getPassword());
			}
			custRepo.save(cust.get());
		}
		return "Profile successfully updated!.";
	}

}
