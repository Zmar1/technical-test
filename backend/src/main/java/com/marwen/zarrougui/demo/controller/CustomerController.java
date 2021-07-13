package com.marwen.zarrougui.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marwen.zarrougui.demo.dto.CustomerDTO;
import com.marwen.zarrougui.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/")
	public CustomerDTO create(@RequestBody CustomerDTO customer) {

		return customerService.create(customer);
	}

	@PutMapping("/{id}")
	public CustomerDTO create(@PathVariable("id") Long id, @RequestBody CustomerDTO customer) {

		return customerService.update(id, customer);
	}

	@GetMapping("/{id}")
	public CustomerDTO get(@PathVariable("id") Long id) {

		return customerService.get(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {

		customerService.delete(id);
	}

	@PostMapping("/list")
	public List<CustomerDTO> list(@RequestBody CustomerDTO customer) {

		return customerService.list(customer);
	}
}
