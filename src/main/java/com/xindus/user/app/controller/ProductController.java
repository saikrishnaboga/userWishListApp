package com.xindus.user.app.controller;


import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xindus.user.app.entity.Product;
import com.xindus.user.app.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	//build create product REST API
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
	}
	
	//build get all products REST API
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	//build get product by id REST API
	//http://localhost:8080/api/products/1
	@GetMapping("{ProductId}")
	public ResponseEntity<Product> getProductById(@PathVariable("ProductId") long productId){
		return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.OK);
	}
	
	//build update product REST API
	//http://localhost:8080/api/products/1
	
	@PutMapping("{ProductId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("ProductId") long productId, 
															@RequestBody Product product){
		return new ResponseEntity<Product>(productService.updateProduct(product, productId), HttpStatus.OK);
	}
	
	
	//build delete product REST API
	@DeleteMapping("{ProductId}")
	public ResponseEntity<String> deleteProductByid(@PathVariable("ProductId") long productId){
		productService.deleteProduct(productId);
		
		return new ResponseEntity<String>("Product Deleted Successfully!.", HttpStatus.OK);
	}
	
}
