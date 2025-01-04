package com.johndlr.products.service;

import com.johndlr.products.rest.CreateProductRestModel;

public interface ProductService {
	
	String createProduct(CreateProductRestModel productRestModel) throws Exception ;

}
