package com.local.inventory.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseRest extends ReponseRest{

	private ProductResponse product = new ProductResponse();
}
