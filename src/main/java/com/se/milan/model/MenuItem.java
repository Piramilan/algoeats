package com.se.milan.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private double price;
    @Column(length = 255)
    private String description;

}
