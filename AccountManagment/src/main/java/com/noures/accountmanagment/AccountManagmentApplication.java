package com.noures.accountmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagmentApplication.class, args);
    }

}
