package com.noures.usersclient;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UsersClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersClientApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /* RestTemplate is a Http client used for Http internal communications between Microservices that support load balancing.
     @LoadBalanced will enable the client side load balancing */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

    /*Enables Feign Logging to see the date exchanged in Debug mode*/
    @Bean
    Logger.Level feignLoggerLover()
    {
        return Logger.Level.FULL;
    }


}
