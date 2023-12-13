package DiamonShop.Service.User;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.BillsDao;
import DiamonShop.Dto.CartDto;
import DiamonShop.Entity.BillDetail;
import DiamonShop.Entity.Bills;
@Service
public class BillsServiceImpl implements IBillsService{
	@Autowired
	private BillsDao billsDao;

	public int addBills(Bills bill) {
		return billsDao.addBills(bill);
	}

	public void addBillDetail(HashMap<Integer, CartDto> carts) {
		int idBills = billsDao.getIDLastBill();
		
		for(Map.Entry<Integer, CartDto> itemCart : carts.entrySet()) {
			BillDetail billDetail = new BillDetail();
			billDetail.setId_bills(idBills);
			billDetail.setId_product(itemCart.getValue().getProduct().getId_product());
			billDetail.setQuantity(itemCart.getValue().getQuantity());
			billDetail.setTotal(itemCart.getValue().getTotalPrice());
			billsDao.addBillDetail(billDetail);
		}
		
	}

}
