package com.se.milan.controller;

import com.se.milan.model.MenuItem;
import com.se.milan.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menu")
    public String homePage(Model model) {
        Map<String, List<MenuItem>> groupedMenuItems = menuService.getMenuItemsGroupedByCategory();
        model.addAttribute("groupedMenuItems", groupedMenuItems);
        return "menu";
    }


    @GetMapping("/menu/search")
    public String menuSearchPage(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "category", required = false) String category,
            Model model) {

        List<MenuItem> menuItems = menuService.searchAndFilter(keyword, category);
        List<String> categories = menuService.getAllCategories();

        model.addAttribute("menuItems", menuItems);
        model.addAttribute("categories", categories);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategory", category);

        return "searchMenu";
    }

    @GetMapping("/menu/category/{category}")
    public String getMenuByCategory(@PathVariable String category, Model model) {
        model.addAttribute("menuItems", menuService.getMenuItemsByCategory(category));
        return "menu";
    }

    @GetMapping("/menu/add")
    public String addMenuPage(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("categories", menuService.getAllCategories());
        return "addMenuItem";
    }

    @PostMapping("/menu/add")
    public String saveMenuItem(@ModelAttribute MenuItem menuItem) {
        menuService.saveMenuItem(menuItem);
        return "redirect:/menu";
    }

    @GetMapping("/menu/edit/{id}")
    public String editMenuPage(@PathVariable int id, Model model) {
        model.addAttribute("menuItem", menuService.getMenuItemById(id).orElse(new MenuItem()));
        model.addAttribute("categories", menuService.getAllCategories());
        return "editMenuItem";
    }

    @PostMapping("/menu/edit")
    public String updateMenuItem(@ModelAttribute MenuItem menuItem) {
        menuService.saveMenuItem(menuItem);
        return "redirect:/menu";
    }

    @GetMapping("/menu/delete/{id}")
    public String deleteMenuItem(@PathVariable int id) {
        menuService.deleteMenuItem(id);
        return "redirect:/menu";
    }

    @GetMapping("/menu/clear")
    public String clearMenuItems() {
        menuService.deleteAllMenuItems();
        return "redirect:/menu";
    }
}
