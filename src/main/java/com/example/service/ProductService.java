package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

//используется для выполнения операций с продуктами
@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    public Product addProduct(String name) {
        Product newProduct = new Product(name);

        try {
            productsRepository.save(newProduct);
        } catch (Exception e) {
            return null;
        }

        return newProduct;
    }
    public Iterable<Product> getProductList() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return productsRepository.findAll(sort);
    }
    public void markProduct(Long id) {
        Product currentProduct = getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + id));
        currentProduct.setIsMarked();
        productsRepository.save(currentProduct);
    }

    public void deleteProduct(Long id) {
        try {
            productsRepository.deleteById(id);
        } catch (Exception e) {
        }
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }
}