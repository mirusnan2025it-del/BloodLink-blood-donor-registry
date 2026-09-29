package com.example.bloodlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/donor")
    public String donorPage() {
        return "donor";
    }

    @GetMapping("/search-donor")
    public String searchDonorPage() {
        return "search-donor";
    }

    @GetMapping("/blood-request")
    public String bloodRequestPage() {
        return "blood-request";
    }

    @GetMapping("/blood-requests")
    public String bloodRequestsPage() {
        return "blood-requests";
    }
}