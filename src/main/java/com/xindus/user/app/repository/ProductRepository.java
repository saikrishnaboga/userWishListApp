package com.xindus.user.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xindus.user.app.entity.Product;



public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
