package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    TodoRepository repository;

    @RequestMapping("/")
    public String showTodos(Model model){
        model.addAttribute("todos", repository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String newForm(Model model){
        model.addAttribute("todo", new Todo());
        return "todoform";
    }

    @PostMapping("/process")
    public String processFrom(@Valid Todo todo, BindingResult result, @RequestParam("duedate") String duedate){

        if(result.hasErrors()){
            return "todoform";
        }
        Date date = new Date();

        try{
            date = new SimpleDateFormat("yyyy-mm-dd").parse(duedate);}

        catch (Exception e){
            e.printStackTrace();
        }
        todo.setDuedate(date);
        repository.save(todo);
        return "redirect:/";
    }
}
