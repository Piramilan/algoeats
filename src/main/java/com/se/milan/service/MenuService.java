package com.se.milan.service;

import com.se.milan.model.MenuItem;
import com.se.milan.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Map<String, List<MenuItem>> getMenuItemsGroupedByCategory() {
        List<MenuItem> allItems = menuRepository.findAll();
        return allItems.stream()
                .collect(Collectors.groupingBy(MenuItem::getCategory));
    }

    public List<MenuItem> searchAndFilter(String keyword, String category) {
        List<MenuItem> menuItems = menuRepository.findAll();

        // Apply search filter if keyword is provided
        if (keyword != null && !keyword.isEmpty()) {
            menuItems = menuItems.stream()
                    .filter(item -> item.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Apply category filter if category is provided and not "All"
        if (category != null && !category.equals("All")) {
            menuItems = menuItems.stream()
                    .filter(item -> item.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        return menuItems;
    }

    public List<String> getAllCategories() {
        return menuRepository.findAll()
                .stream()
                .map(MenuItem::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuRepository.findByCategory(category);
    }

    public Optional<MenuItem> getMenuItemById(int id) {
        return menuRepository.findById(id);
    }

    public MenuItem saveMenuItem(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    public void deleteMenuItem(int id) {
        menuRepository.deleteById(id);
    }

    public void deleteAllMenuItems() {
        menuRepository.deleteAll();
    }
}
