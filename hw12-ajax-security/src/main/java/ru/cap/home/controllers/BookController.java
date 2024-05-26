package ru.cap.home.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @PostMapping("/")
    public String postIndex() {
        return "index";
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

}