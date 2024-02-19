package com.xindus.user.app.service;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xindus.user.app.entity.Product;
import com.xindus.user.app.exception.ResourceNotFoundException;
import com.xindus.user.app.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;	
	
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}


	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}


	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}


	@Override
	public Product getProductById(long id) {
		Optional<Product> product = productRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		return productRepository.findById(id).orElseThrow(()-> 
						new ResourceNotFoundException("Product", "productId", id));
		
	}


	@Override
	public Product updateProduct(Product product, long id) {
		//we need to check whether employee with given id is exist in DB or not
		
		Product existingProduct= productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product", "Id", id));
		
		existingProduct.setProductName(product.getProductName());
		existingProduct.setDescription(product.getDescription());
		productRepository.save(existingProduct);
		return existingProduct;
	}


	@Override
	public void deleteProduct(long id) {
		// check whether a employee exist in a DB or not
		productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product", "productId", id));				
		
		productRepository.deleteById(id);
	}
	
}
