package com.perennial.pht.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(){
        return "Hello TD..." +
                " Good to fo for API Testing... :)";
    }
}
