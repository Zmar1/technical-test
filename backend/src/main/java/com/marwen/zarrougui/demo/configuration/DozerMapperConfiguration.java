package com.marwen.zarrougui.demo.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerMapperConfiguration {

	@Bean
	public Mapper getDozerMapper() {

		return new DozerBeanMapper();
	}

}
