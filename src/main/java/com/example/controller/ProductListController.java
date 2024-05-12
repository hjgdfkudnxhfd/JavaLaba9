package com.example.controller;

import net.minidev.json.JSONObject;
import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductListController {
    @Autowired
    private ProductService productService;

    @PostMapping("/items")
    public HttpStatus createItem(@RequestBody JSONObject jsonItem) {
        productService.addProduct(jsonItem.getAsString("name"));
        return HttpStatus.OK;
    }

    // Спринг сам приобразовывает возвращаемую коллекцию в JSON-массив и добавляет его в тело http-ответа
    @GetMapping("/items")
    public Iterable<Product> getItemList() { return productService.getProductList(); }
    @PutMapping("/items/{id}")
    public HttpStatus markItem(@PathVariable int id) {
        productService.markProduct(id);
        return HttpStatus.OK;
    }
    @DeleteMapping("/items/{id}")
    public HttpStatus deleteItem(@PathVariable int id) {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}