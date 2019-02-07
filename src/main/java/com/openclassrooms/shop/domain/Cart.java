package com.openclassrooms.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
	
	List <CartLine> actualCartList = new ArrayList<CartLine>(); /**Create an empty list of type Cartline with the name of actualCartList**/

    /**
     *
     * @return the actual cartline list
     */
    public List<CartLine> getCartLineList() {
        return actualCartList;    /** return actualCartList**/
    }

    /**
     * Adds a getProductById in the cart or increment its quantity in the cart if already added
     * @param product getProductById to be added
     * @param quantity the quantity
     */
    public void addItem(Product product, int quantity) {
        // TODO implement the method
    	CartLine cartline = new CartLine(); /**create an object of class CartLine**/
    	
    	Optional<CartLine> optionalProduct = actualCartList.stream().filter(x -> x.getProduct().getId().equals(product.getId())).findFirst();
    	/**find the first element which matches with  @param product from a stream and store it in optional object with the name of optionalProduct **/
    	
    	if(!(optionalProduct.isPresent())) { /**if value not present**/
    		cartline.setProduct(product);   /**set the value of the Product product to @param product **/
    		cartline.setQuantity(quantity); /**set the value of the quantity variable to @param quantity**/
    		actualCartList.add(cartline); /**add the cartline to the actual cart list**/
    	} else { /**if value is present**/
    		optionalProduct.get().setQuantity(optionalProduct.get().getQuantity() + quantity); /**get the already added product and increment its quantity with @param quantiy**/
    		
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
    	double total =0; /**create variable of type double**/
    	for (CartLine list: actualCartList) { /**loop through the actual cart*/
    		total += list.getSubtotal();  /**get the subtotal of each cartline,  add them together and store the value in variable total**/
    	}
        return total; /**return the value of total**/

    }

    /**
     * @return Get average value of a cart
     */
    public double getAverageValue()
    {
        // TODO implement the method

    	int quantity=0; /**create variable of type int**/
    	for (CartLine list: actualCartList) { /**loop through the actual cart **/
    		quantity = quantity + list.getQuantity(); /**get the quantity of each cartline, add them together and store the value in quantity variable**/
    	}
    	return getTotalValue()/quantity; /**divide total value of the cart with the total quantity and return its value**/
    }

    /**
     * @param productId the getProductById id to search for
     * @return getProductById in the cart if it finds it
     */
    public Product findProductInCartLines(Long productId)
    {
        // TODO implement the method
    	Optional <CartLine> product = actualCartList.stream().filter(x ->x.getProduct().getId().equals(productId)).findFirst();
    	/**find the first element by productID from a stream and store it in optional object with the name of product **/
        return product.get().getProduct(); /**return product**/
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
