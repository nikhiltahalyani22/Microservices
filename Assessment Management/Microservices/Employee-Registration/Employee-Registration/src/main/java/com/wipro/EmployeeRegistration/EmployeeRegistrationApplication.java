package com.wipro.EmployeeRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.wipro")
public class EmployeeRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeRegistrationApplication.class, args);
    }

}
