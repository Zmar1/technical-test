package com.marwen.zarrougui.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.marwen.zarrougui.demo.entity.Customer;

@Repository
public interface CustomerRepository
		extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

}
