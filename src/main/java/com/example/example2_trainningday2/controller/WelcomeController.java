package com.example.example2_trainningday2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {
    @GetMapping("/welcome/index")
    public ResponseEntity<String> welcome(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(" Welcome " + name);
    }

}
