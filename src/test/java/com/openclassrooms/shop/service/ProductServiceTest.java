package com.openclassrooms.shop.service;


import com.openclassrooms.shop.domain.Product;
import com.openclassrooms.shop.repository.OrderRepository;
import com.openclassrooms.shop.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProductServiceTest {


    @Test
    public void updateProductQuantities_3ProductsUpdates_quantitiesUpdated()
    {
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductService productService = new ProductService(productRepository, orderRepository);

        productService.updateProductQuantities(1L ,1);
        productService.updateProductQuantities(3L ,2);
        productService.updateProductQuantities(5L ,3);

        List<Product> products = productService.getAllProducts();

        Assert.assertEquals(9, products.stream().filter(p -> p.getId() == 1).findFirst().get().getStock());
        Assert.assertEquals(28, products.stream().filter(p -> p.getId() == 3).findFirst().get().getStock());
        Assert.assertEquals(47, products.stream().filter(p -> p.getId() == 5).findFirst().get().getStock());
    }

    @Test
    public void getProductById_searchedForId3_productReturned()
    {
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductService productService = new ProductService(productRepository, orderRepository);
        Long id = 3L;

        Product product = productService.getProductById(id);

        Assert.assertEquals("JVC HAFX8R Headphone", product.getName());
        Assert.assertEquals(69.99, product.getPrice(), 0);
    }

}
