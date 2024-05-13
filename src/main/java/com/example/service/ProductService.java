package com.example.service;

import com.example.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

//используется для выполнения операций с продуктами
@Service
public class ProductService {
    private final ArrayList<Product> products = new ArrayList<Product>();
    private int idCounter = 0;

    public Product addProduct(String name) {
        Product newProduct = new Product(idCounter, name);
        products.add(newProduct);
        idCounter++;
        return newProduct;
    }
    public ArrayList<Product> getProductList() {
        return products;
    }
    public void markProduct(int id) {
        getProductById(id).setIsMarked();
    }

    public void deleteProduct(int id) {
        products.remove(getProductById(id));
    }

    public Product getProductById(int id){
        return products.stream() //создаем полок, фильтруем данные по id и берем первый нужный
                .filter(product -> product.getId() == id)
                .findFirst()
                .get();
    }
}