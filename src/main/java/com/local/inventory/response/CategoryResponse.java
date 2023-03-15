package com.local.inventory.response;

import java.util.List;

import com.local.inventory.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
     private List<Category> category;
}
