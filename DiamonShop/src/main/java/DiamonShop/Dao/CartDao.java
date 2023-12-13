package DiamonShop.Dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DiamonShop.Dto.CartDto;
import DiamonShop.Dto.ProductsDto;
@Repository
public class CartDao extends BaseDao{
	@Autowired
	ProductsDao productsDao = new ProductsDao();

	public HashMap<Integer, CartDto> addCart(int id, HashMap<Integer, CartDto> cart) {
		CartDto itemCart = new CartDto();
		ProductsDto product = productsDao.findProductByID(id);
		if (product != null && cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuantity(itemCart.getQuantity() + 1);	
			itemCart.setTotalPrice(itemCart.getQuantity() * itemCart.getProduct().getPrice());
		} else {
			itemCart.setProduct(product);
			itemCart.setQuantity(1);
			itemCart.setTotalPrice(product.getPrice());
		}
		cart.put(id, itemCart);
		return cart;
	}
	
	public HashMap<Integer, CartDto> editCart(int id, int quantity, HashMap<Integer, CartDto> cart) {
		if (cart == null) {
			return cart;
		}
		CartDto itemCart = new CartDto();
		if (cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuantity(quantity);
			itemCart.setTotalPrice(quantity * itemCart.getProduct().getPrice());
		}
		cart.put(id, itemCart);
		return cart;
	}
	
	public HashMap<Integer, CartDto> deleteCart(int id, HashMap<Integer, CartDto> cart) {
		if (cart == null) {
			return cart;
		}	
		if (cart.containsKey(id)) {
			cart.remove(id);
		}
		return cart;
	}
	
	public int totalQuantity(HashMap<Integer, CartDto> cart) {
		int totalQuantity = 0;
		for (Map.Entry<Integer, CartDto> itemCart: cart.entrySet()) {
			totalQuantity += itemCart.getValue().getQuantity();
			
		}
		return totalQuantity;
	}
	
	public double totalPrice(HashMap<Integer, CartDto> cart) {
		double totalPrice = 0;
		for (Map.Entry<Integer, CartDto> itemCart: cart.entrySet()) {
			totalPrice += itemCart.getValue().getTotalPrice();
		}
		return totalPrice;
	}
}
