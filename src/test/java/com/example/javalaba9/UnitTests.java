package com.example.javalaba9;

import com.example.model.Product;
import com.example.repository.ProductsRepository;
import com.example.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTests {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void addProduct() {
        String productName = "капуста";

        Product savedProduct = productService.addProduct(productName);

        Assert.assertNotNull(savedProduct);
        Assert.assertEquals(productName, savedProduct.getName());
        Assert.assertFalse(savedProduct.getIsMarked());

        Mockito.verify(productsRepository, Mockito.times(1)).save(savedProduct);
    }

    @Test
    public void markProduct() {
        Product productForMark = new Product("капуста");
        Optional<Product> optionalProduct = Optional.of(productForMark);
        Mockito.when(productsRepository.findById(productForMark.getId())).thenReturn(optionalProduct);

        productService.markProduct(productForMark.getId());

        Mockito.verify(productsRepository, Mockito.times(1)).save(productForMark);
        Assert.assertTrue(productForMark.getIsMarked());
    }
}
