package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    TodoRepository repository;

    @GetMapping("/")
    public String showTodos(Model model){
        model.addAttribute("todos", repository.findAll());
        return "list";
    }
}
