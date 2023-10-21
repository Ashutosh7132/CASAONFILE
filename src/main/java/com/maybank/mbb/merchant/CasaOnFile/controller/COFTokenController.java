package com.maybank.mbb.merchant.CasaOnFile.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class COFTokenController {

    @GetMapping("/hi")
    public String getHello(){

        return "Hi Ashutosh Shriansh";
    }
}
