package com.openclassrooms.shop.service;

import com.openclassrooms.shop.domain.Cart;
import com.openclassrooms.shop.domain.CartLine;
import com.openclassrooms.shop.repository.OrderRepository;
import com.openclassrooms.shop.domain.Product;
import com.openclassrooms.shop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	private ProductRepository productRepository;
	private OrderRepository orderRepository;

	@Autowired
	public ProductService(ProductRepository repository, OrderRepository orderRepository) {
		this.productRepository = repository;
		this.orderRepository = orderRepository;
	}

	/**
	 * @return all products from the inventory
	 * BA: change return type from array to List
	 */

	public List<Product> getAllProducts() {

		// TODO change the return type from array to List<T> and propagate the change
		// throughout the application
		return productRepository.findAll();
	}

	/**
	 *
	 * @param productId Id of the product
	 * @return a product form the inventory
	 */
	public Product getProductById(Long productId) {
		// TODO implement the method
		
		/**
		 * find the first product by productId from a stream 
		 * store it in optional object with the name of product
		 **/		
		Optional<Product> product = getAllProducts().stream().filter(x -> x.getId().equals(productId)).findFirst();
		if(product.isPresent()) {
			return product.get(); 
		} else {
			return null;
		}
	}

	/**
	 * Update the quantities left for each product in the inventory depending of
	 * ordered the quantities
	 * 
	 * @param productId ID of the product to be updated
	 */
	public void updateProductQuantities(Long productId, int quantity) {		
		// TODO implement the method
		
		/**
		 * find the first product by productId from a stream
		 * store it in optional object with the name of product
		 * substract the ordered quantity from current quantity of the product 
		 * set the value of stock variable accordingly
		 **/
		
		Optional<Product> product = getAllProducts().stream().filter(x -> x.getId().equals(productId)).findFirst();
		if (product.isPresent()) {
			product.get().setStock(product.get().getStock() - quantity);
		} 			
	}
}
