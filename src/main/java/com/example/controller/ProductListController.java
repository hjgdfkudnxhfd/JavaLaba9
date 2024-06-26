package com.example.controller;

import net.minidev.json.JSONObject;
import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //контроллер Spring MVC
//обрабатывает HTTP-запросы и возвращает данные в формате JSON
public class ProductListController {
    @Autowired
    private ProductService productService; //внедряет зависимость ProductService в контроллер

    @PostMapping("/items") //метод пост
    public HttpStatus createItem(@RequestBody JSONObject jsonItem) { //говорю аннотацией, что в поступившем запросе тело http находится в json формате
        productService.addProduct(jsonItem.getAsString("name"));
        return HttpStatus.OK;
    }

    // Спринг сам преобразовывает возвращаемую коллекцию в JSON-массив и добавляет его в тело http-ответа
    @GetMapping("/items") //обрабатывает GET-запросы
    public Iterable<Product> getItemList() {
        return productService.getProductList();
    }
    @PutMapping("/items/{id}") //обрабатывает PUT-запросы
    public HttpStatus markItem(@PathVariable Long id) {
        productService.markProduct(id);
        return HttpStatus.OK;
    }
    @DeleteMapping("/items/{id}") //обрабатывает DELETE-запросы
    public HttpStatus deleteItem(@PathVariable Long id) {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}