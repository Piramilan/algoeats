package com.se.milan.controller;

import com.se.milan.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.se.milan.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public String index(Model model){
        List<MenuItem> menuItems = menuService.getAllMenuItems().stream()
                .limit(3) // Show only 3 items on the homepage
                .collect(Collectors.toList());
        model.addAttribute("menuItems", menuItems);
        return "index";
    }
}
