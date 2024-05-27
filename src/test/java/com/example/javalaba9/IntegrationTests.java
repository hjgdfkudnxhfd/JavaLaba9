package com.example.javalaba9;

import com.example.controller.ProductListController;
import com.example.model.Product;
import com.example.repository.ProductsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //автоматически настраивает MockMvc для тестирования контроллеров без запуска сервера
public class IntegrationTests {

    @Autowired
    private ProductListController productListController; //Внедряет контроллер ProductListController, который будет тестироваться

    @MockBean
    private ProductsRepository productsRepository; //заменяет реальный репозиторий моком для тестирования

    @Autowired
    private MockMvc mockMvc;
    private Product product1 = new Product("рыба");
    private Product product2 = new Product("хлеб");
    private Product product3 = new Product("сметана");
    private Product product4 = new Product("молоко");
    private Product product5 = new Product("котлеты");


    @Test
    public void productListTest() throws Exception {
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3, product4, product5));

        Sort sort = Sort.by(Sort.Direction.ASC, "id"); //сортировка по id
        Mockito.when(productsRepository.findAll(sort)).thenReturn(products);

        mockMvc.perform(get("/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[1].name", is("хлеб")));
    }
    @Test
    public void deleteProductTest() throws Exception {
        product1.setId(6l);
        productsRepository.save(product1);

        mockMvc.perform(delete("/items/"+product1.getId().toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Product> deletedProduct = productsRepository.findById(product1.getId()); //Optional используется, потому что продукт может быть найден или нет
        Assert.assertFalse(deletedProduct.isPresent()); //условие должно быть ложным
    }
}