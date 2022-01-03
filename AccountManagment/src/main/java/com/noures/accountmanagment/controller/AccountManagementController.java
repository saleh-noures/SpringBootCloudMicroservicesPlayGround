package com.noures.accountmanagment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountManagementController {
    @GetMapping("/status/check")
    public String status() {
        return " Account Management is Working..";
    }
}
