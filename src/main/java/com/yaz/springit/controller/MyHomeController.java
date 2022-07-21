package com.yaz.springit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyHomeController {

    @GetMapping("/")
    public String home(Model model,HttpServletRequest request) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }
}
