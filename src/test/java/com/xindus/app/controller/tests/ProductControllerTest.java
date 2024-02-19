package com.xindus.app.controller.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xindus.user.app.controller.ProductController;
import com.xindus.user.app.entity.Product;
import com.xindus.user.app.service.ProductService;

class ProductControllerTest {

    private ProductService productService = mock(ProductService.class);
    private ProductController productController = new ProductController(productService);

    @Test
    void testSaveProduct() {
        Product product = new Product();
        when(productService.saveProduct(ArgumentMatchers.any(Product.class))).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.saveProduct(product);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());

        verify(productService, times(1)).saveProduct(product);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(productList);

        List<Product> result = productController.getAllProducts();

        assertEquals(productList, result);

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        long productId = 1L;
        Product product = new Product();
        when(productService.getProductById(productId)).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());

        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testUpdateProduct() {
        long productId = 1L;
        Product updatedProduct = new Product();
        when(productService.updateProduct(ArgumentMatchers.any(Product.class), eq(productId))).thenReturn(updatedProduct);

        ResponseEntity<Product> responseEntity = productController.updateProduct(productId, new Product());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedProduct, responseEntity.getBody());

        verify(productService, times(1)).updateProduct(ArgumentMatchers.any(Product.class), eq(productId));
    }

    @Test
    void testDeleteProductById() {
        long productId = 1L;

        ResponseEntity<String> responseEntity = productController.deleteProductByid(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product Deleted Successfully!.", responseEntity.getBody());

        verify(productService, times(1)).deleteProduct(productId);
    }
}
