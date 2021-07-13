package com.marwen.zarrougui.demo.service;

import java.util.List;

import com.marwen.zarrougui.demo.dto.CustomerDTO;

public interface CustomerService {

	CustomerDTO create(CustomerDTO customer);

	CustomerDTO update(Long id, CustomerDTO customer);

	CustomerDTO get(Long id);

	void delete(Long id);

	List<CustomerDTO> list(CustomerDTO customer);

}
