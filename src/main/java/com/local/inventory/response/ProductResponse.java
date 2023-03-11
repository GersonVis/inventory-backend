package com.local.inventory.response;
import java.util.List;

import com.local.inventory.model.Product;

import lombok.Data;


@Data
public class ProductResponse {
		List<Product> products;
}
