package DiamonShop.Service.User;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import DiamonShop.Dto.CartDto;
import DiamonShop.Entity.Bills;

@Service
public interface IBillsService {
	public int addBills(Bills bill);
	
	public void addBillDetail(HashMap<Integer, CartDto> carts);
}
