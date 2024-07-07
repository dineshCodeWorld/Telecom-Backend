package com.Telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.telecom.dto.CallDetailsDTO;
import com.telecom.dto.CustomerDTO;
import com.telecom.dto.FriendFamilyDTO;
import com.telecom.dto.LoginDTO;
import com.telecom.dto.PlanDTO;
import com.telecom.entity.CallDetails;
import com.telecom.entity.Customer;
import com.telecom.entity.Plan;
import com.telecom.repository.CallDetailsRepository;
import com.telecom.repository.CustomerRepository;
import com.telecom.repository.PlanRepository;
import com.telecom.service.CustomerService;
import com.telecom.service.PlanService;

public class TelecomApplicationTest {

	@InjectMocks
	CustomerService customerService;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	CallDetailsRepository callDetailsRepository;
	@Mock
	PlanRepository planRepository;
	@Mock
	PlanService planService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setPhoneNo(1234567890L);
		customerDTO.setPassword("password");
		Plan plan = new Plan();
		plan.setPlanId(1);
		customerDTO.setCurrentPlan(PlanDTO.valueOf(plan));

		Customer customer = new Customer();
		customer.setPhoneNo(1234567890L);
		customer.setPassword("password");
		customer.setPlan(plan);

		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		customerService.createCustomer(customerDTO);

		verify(customerRepository, times(1)).save(any(Customer.class));
	}

	@Test
	public void testLoginSuccess() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setPhoneNo(1234567890L);
		loginDTO.setPassword("password");

		Customer customer = new Customer();
		customer.setPhoneNo(1234567890L);
		customer.setPassword("password");

		when(customerRepository.findById(loginDTO.getPhoneNo())).thenReturn(Optional.of(customer));

		boolean result = customerService.login(loginDTO);

		assertTrue(result);
	}

	@Test
	public void testLoginFailure() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setPhoneNo(1234567890L);
		loginDTO.setPassword("wrongpassword");

		Customer customer = new Customer();
		customer.setPhoneNo(1234567890L);
		customer.setPassword("password");

		when(customerRepository.findById(loginDTO.getPhoneNo())).thenReturn(Optional.of(customer));

		boolean result = customerService.login(loginDTO);

		assertFalse(result);
	}

	@Test
	public void testGetCustomerCallDetails() {
		Long phoneNo = 1234567890L;
		List<CallDetails> callDetailsList = new ArrayList<>();
		CallDetails callDetails = new CallDetails();
		callDetails.setCalledBy(phoneNo);
		callDetailsList.add(callDetails);

		when(callDetailsRepository.findByCalledBy(phoneNo)).thenReturn(callDetailsList);

		List<CallDetailsDTO> callDetailsDTOList = customerService.getCustomerCallDetails(phoneNo);

		assertNotNull(callDetailsDTOList);
		assertEquals(1, callDetailsDTOList.size());
	}

	@Test
	public void testSaveFriend() {
		Long phoneNo = 1234567890L;
		FriendFamilyDTO friendFamilyDTO = new FriendFamilyDTO();
		friendFamilyDTO.setFriendAndFamily(1234567891L);

		Customer customer = new Customer();
		customer.setPhoneNo(phoneNo);
		customer.setFriends(new ArrayList<>());

		when(customerRepository.getOne(phoneNo)).thenReturn(customer);

		customerService.saveFriend(phoneNo, friendFamilyDTO);

		verify(customerRepository, times(1)).save(any(Customer.class));
	}

}
