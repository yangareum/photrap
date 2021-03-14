package com.abocom.photrap.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String getUser(Model model) {
        User user = new User("kkaok", "테스트", "web") ;
        model.addAttribute("user", user);
        return "main";
    }






}

