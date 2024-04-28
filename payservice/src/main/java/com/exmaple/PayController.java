package com.exmaple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pay")
public class PayController {

    @GetMapping("")
    public String test() {
        return "test";
    }
}
