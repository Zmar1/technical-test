package com.marwen.zarrougui.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.marwen.zarrougui.demo.dto.CustomerDTO;
import com.marwen.zarrougui.demo.entity.Customer;
import com.marwen.zarrougui.demo.entity.QCustomer;
import com.marwen.zarrougui.demo.repositories.CustomerRepository;
import com.marwen.zarrougui.demo.service.CustomerService;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class CustomerServiceImpl implements CustomerService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private Mapper mapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CustomerDTO create(CustomerDTO customer) {

		Preconditions.checkArgument(customer != null, "The customer object can not be null !");

		Customer toBeCreated = mapper.map(customer, Customer.class);

		return mapper.map(repository.saveAndFlush(toBeCreated), CustomerDTO.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CustomerDTO update(Long id, CustomerDTO customer) {

		Preconditions.checkArgument(id != null, "The id can not be null !");
		Preconditions.checkArgument(customer != null, "The customer object can not be null !");
		Customer toBeUpdated = repository.getById(id);
		mapper.map(customer, toBeUpdated);

		return mapper.map(repository.saveAndFlush(toBeUpdated), CustomerDTO.class);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CustomerDTO get(Long id) {

		Preconditions.checkArgument(id != null, "The id can not be null !");
		return mapper.map(repository.getById(id), CustomerDTO.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {

		repository.deleteById(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CustomerDTO> list(CustomerDTO customer) {

		BooleanExpression spec = getPredicate(customer);

		List<Customer> result =
				spec == null ? repository.findAll() : (List<Customer>) repository.findAll(spec);
		return result.stream().map(e -> mapper.map(e, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	private BooleanExpression getPredicate(CustomerDTO customer) {

		BooleanExpression spec = null;
		QCustomer qCustomer = QCustomer.customer;
		if (customer == null) {
			return null;
		}
		if (customer.getName() != null) {
			spec = qCustomer.name.contains(customer.getName());
		}
		if (customer.getEmail() != null) {
			spec = spec == null ? qCustomer.email.contains(customer.getEmail())
					: spec.and(qCustomer.email.contains(customer.getEmail()));
		}

		return spec;
	}

}
