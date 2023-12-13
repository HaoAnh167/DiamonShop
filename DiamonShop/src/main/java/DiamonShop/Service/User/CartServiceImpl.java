package DiamonShop.Service.User;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.CartDao;
import DiamonShop.Dto.CartDto;
@Service
public class CartServiceImpl implements ICartService{

	@Autowired
	private CartDao cartDao = new CartDao();
	
	public HashMap<Integer, CartDto> addCart(int id, HashMap<Integer, CartDto> cart) {
		return cartDao.addCart(id, cart);
	}

	public HashMap<Integer, CartDto> editCart(int id, int quantity, HashMap<Integer, CartDto> cart) {
		return cartDao.editCart(id, quantity, cart);
	}

	public HashMap<Integer, CartDto> deleteCart(int id, HashMap<Integer, CartDto> cart) {
		return cartDao.deleteCart(id, cart);
	}

	public int totalQuantity(HashMap<Integer, CartDto> cart) {
		return cartDao.totalQuantity(cart);
	}

	public double totalPrice(HashMap<Integer, CartDto> cart) {
		return cartDao.totalPrice(cart);
	}

}
