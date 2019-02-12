package com.openclassrooms.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
	
	/**Create an empty list of type Cartline with the name of actualCartList**/	
	List <CartLine> actualCartList = new ArrayList<CartLine>(); 
	
    /**
     *
     * @return the actual cartline list
     */
    public List<CartLine> getCartLineList() {
        return actualCartList;   
    }

    /**
     * Adds a getProductById in the cart or increment its quantity in the cart if already added
     * @param product getProductById to be added
     * @param quantity the quantity
     */
    public void addItem(Product product, int quantity) {
        // TODO implement the method
    	
    	/**create an object of class CartLine**/
    	CartLine cartline = new CartLine(); 
    	
    	/**
    	 * find the first element which matches with  @param product from a stream
    	 * store it in optional object with the name of optionalProduct 
    	 **/
    	Optional<CartLine> optionalProduct = actualCartList.stream().filter(x -> x.getProduct().getId().equals(product.getId())).findFirst();
    	
    	/**if value not present**/
    	if(!(optionalProduct.isPresent())) {
    		/**set the value of the Product product to @param product **/
    		cartline.setProduct(product);  
    		/**set the value of the quantity variable to @param quantity**/
    		cartline.setQuantity(quantity); 
    		/**add the cartline to the actual cart list**/
    		actualCartList.add(cartline);   
    		/**if value is present**/
    		} else { 
    	    /**get the already added product and increment its quantity with @param quantiy**/
    		optionalProduct.get().setQuantity(optionalProduct.get().getQuantity() + quantity); 	
    	}
    }

    /**
     * Removes a getProductById form the cart
     * @param product the getProductById to be removed
     */
    public void removeLine(Product product) {
        getCartLineList().removeIf(l -> l.getProduct().getId().equals(product.getId()));
    }


    /**
     * @return total value of a cart
     */
    public double getTotalValue()  {
         //TODO implement the method
    	
    	/**create variable of type double**/
    	double total =0; 
    	
    	/**loop through the actual cart*/
    	for (CartLine list: actualCartList) { 
    		/**get the subtotal of each cartline,  add them together and store the value in variable total**/
    		total += list.getSubtotal();  
    	}
        return total; 
    }

    /**
     * @return Get average value of a cart
     */
    public double getAverageValue() {
        // TODO implement the method
    	
    	/**create variable of type int**/
    	int quantity=0; 
    	
    	/**loop through the actual cart **/
    	for (CartLine list: actualCartList) { 
    		/**get the quantity of each cartline, add them together and store the value in quantity variable**/
    		quantity = quantity + list.getQuantity(); 
    	}
    	/**divide total value of the cart with the total quantity and return its value**/
    	return getTotalValue()/quantity; 
    }

    /**
     * @param productId the getProductById id to search for
     * @return getProductById in the cart if it finds it
     */
    public Product findProductInCartLines(Long productId) {
        // TODO implement the method
    	
    	/**
    	 * find the first element by productID from a stream 
    	 * store it in optional object with the name of product 
    	 */
    	Optional <CartLine> product = actualCartList.stream().filter(x ->x.getProduct().getId().equals(productId)).findFirst();
    	
    	/**if product present**/
    	if(product.isPresent()) {
    		return product.get().getProduct();
    	} else {
    		return null;
    	}
         
    }

    /**
     *
     * @param index index of the cartLine
     * @return CartLine in that index
     */
    public CartLine getCartLineByIndex(int index) {
        return getCartLineList().get(index);
    }

    /**
     * Clears a the cart of all added products
     */
    public void clear() {
        List<CartLine> cartLines = getCartLineList();
        cartLines.clear();
    }
}
