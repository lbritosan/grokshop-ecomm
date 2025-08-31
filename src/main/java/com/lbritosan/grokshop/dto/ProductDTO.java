package com.lbritosan.grokshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private int stock;
    private List<Long> categoryIds = new ArrayList<>();
}