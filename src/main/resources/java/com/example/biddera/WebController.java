package com.example.biddera;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @GetMapping("/registerproduct")
    public String productRegistrationForm(Model model) {
        model.addAttribute("registerproduct", new RegisterProduct());
        return "registerproduct";
    }

    @PostMapping("/registerproduct")
    public String greetingSubmit(@ModelAttribute RegisterProduct registerProduct, Model model) {
        model.addAttribute("registerproduct", registerProduct);
        return "sussessful";
    }

    @GetMapping("/allproducts")
    public String ShowAllPOD(Model model) {
        return "continents";
    }
   
    
    @GetMapping("/register")
    public String AccountRegistrationForm(Model model) {
        model.addAttribute("register", new Register());
        return "Register";
    }

    @PostMapping("/register")
    public String SccountRegisterSubmit(@ModelAttribute Register register, Model model) {
        model.addAttribute("register", register);
        DBHandler dbHandler = new DBHandler();
        dbHandler.AddNewUser(register);
        return "sussessful";
    }





}
