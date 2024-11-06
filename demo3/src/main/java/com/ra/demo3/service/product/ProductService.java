package com.ra.demo3.service.product;

import com.ra.demo3.model.dto.ProductDto;
import com.ra.demo3.model.entity.Product;

import java.util.List;

public interface ProductService {
List<Product> findAll();
Product findById(Long id);
Product save(ProductDto productDto);
boolean delete(Long id);
    boolean update( Product product);
}
