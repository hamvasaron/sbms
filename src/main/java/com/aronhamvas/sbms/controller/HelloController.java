package com.aronhamvas.sbms.controller;

import com.aronhamvas.sbms.model.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public ResponseDTO hello() {
        return new ResponseDTO("hello");
    }
}
