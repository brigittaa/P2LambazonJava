package com.openclassrooms.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
	
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
    	CartLine cartline = new CartLine();
    	
    	Optional<CartLine> itemExists = actualCartList.stream().filter(x -> x.getProduct().getId().equals(product.getId())).findFirst();
    	
    	if(!(itemExists.isPresent())) {
    		cartline.setProduct(product);
    		cartline.setQuantity(quantity);
    		actualCartList.add(cartline);
    	} else {
    		itemExists.get().setQuantity(itemExists.get().getQuantity() + quantity);
    		
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
    public double getTotalValue()
    {
         //TODO implement the method
    	double total =0;
    	for (CartLine list: actualCartList) {
    		total += list.getSubtotal();
    	}
        return total;

    }

    /**
     * @return Get average value of a cart
     */
    public double getAverageValue()
    {
        // TODO implement the method

    	int quantity=0;
    	for (CartLine list: actualCartList) {
    		quantity = quantity + list.getQuantity();
    	}
    	return getTotalValue()/quantity;
    }

    /**
     * @param productId the getProductById id to search for
     * @return getProductById in the cart if it finds it
     */
    public Product findProductInCartLines(Long productId)
    {
        // TODO implement the method
    	Optional <CartLine> product = actualCartList.stream().filter(x ->x.getProduct().getId().equals(productId)).findFirst();
        return product.get().getProduct();
    }

    /**
     *
     * @param index index of the cartLine
     * @return CartLine in that index
     */
    public CartLine getCartLineByIndex(int index)
    {
        return getCartLineList().get(index);
    }

    /**
     * Clears a the cart of all added products
     */
    public void clear()
    {
        List<CartLine> cartLines = getCartLineList();
        cartLines.clear();
    }
}
