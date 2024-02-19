package com.xindus.user.app.service;

import java.util.List;

import com.xindus.user.app.entity.Product;

public interface ProductService {
	Product saveProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(long id);
	Product updateProduct(Product product,long id);
	void deleteProduct(long id);
}
