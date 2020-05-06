package com.edealer.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaygroundController {

    @GetMapping("/")
    public String index() {
        return "redirect:playground";
    }

    @GetMapping("/playground")
    public String playground() {
        return "playground";
    }
}
