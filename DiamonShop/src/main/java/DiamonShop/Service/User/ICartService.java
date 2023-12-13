package DiamonShop.Service.User;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import DiamonShop.Dto.CartDto;

@Service
public interface ICartService {
	public HashMap<Integer, CartDto> addCart(int id, HashMap<Integer, CartDto> cart);
	
	public HashMap<Integer, CartDto> editCart(int id, int quantity, HashMap<Integer, CartDto> cart);
	
	public HashMap<Integer, CartDto> deleteCart(int id, HashMap<Integer, CartDto> cart);
	
	public int totalQuantity(HashMap<Integer, CartDto> cart);
	
	public double totalPrice(HashMap<Integer, CartDto> cart);
}
