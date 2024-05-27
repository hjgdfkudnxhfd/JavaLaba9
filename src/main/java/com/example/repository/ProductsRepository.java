package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository //отвечает за доступ к данным
public interface ProductsRepository extends JpaRepository<Product, Long> { //автоматически получили методы CRUD для работы с сущностью Product
}
