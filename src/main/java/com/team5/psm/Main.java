package com.team5.psm;

import com.team5.psm.models.request_models.PaymentRequest;
import com.team5.psm.service_implementors.PaymentServiceImpl;
import com.team5.psm.services.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

import java.time.LocalDate;


@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
