package com.telecom.dto;

import com.telecom.entity.Customer;

public class UpdateDto {

	String name;
	Integer age;
	String address;
	String password;

	@Override
	public String toString() {
		return "UpdateDto [name=" + name + ", age=" + age + ", address=" + address + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Customer valueOf(UpdateDto updateDto) {
		Customer customer = new Customer();
		customer.setName(updateDto.getName());
		customer.setAge(updateDto.getAge());
		customer.setAddress(updateDto.getAddress());
		customer.setPassword(updateDto.getPassword());
		return customer;
	}

}
