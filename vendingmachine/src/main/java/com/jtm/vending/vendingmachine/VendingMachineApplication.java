package com.jtm.vending.vendingmachine;

import org.apache.log4j.BasicConfigurator;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class VendingMachineApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(VendingMachineApplication.class, args);
	}

	@Bean
	@Scope("prototype")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
