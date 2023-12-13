package DiamonShop.Controller.User;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Dto.CartDto;
import DiamonShop.Entity.Bills;
import DiamonShop.Entity.Users;
import DiamonShop.Service.User.BillsServiceImpl;
import DiamonShop.Service.User.CartServiceImpl;

@Controller
public class CartController  extends BaseController{
	@Autowired
	private CartServiceImpl cartServiceImpl = new CartServiceImpl();
	
	@Autowired
	private BillsServiceImpl billsService = new BillsServiceImpl();
	
	@RequestMapping(value = "list-cart")
	public ModelAndView Index() {	
		_mvShare.addObject("slides", _homeService.getDataSlides());
		_mvShare.addObject("categorys", _homeService.getDataCategorys());
		_mvShare.addObject("products", _homeService.getDataProductsDto()); 
		_mvShare.setViewName("user/cart/list-cart");
		return _mvShare;
	}
	
	@RequestMapping(value = "addCart/{id}")
	public String addCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {
		HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>)session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDto>();
		}
		cart = cartServiceImpl.addCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantityCart", cartServiceImpl.totalQuantity(cart));
		session.setAttribute("TotalPriceCart", cartServiceImpl.totalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "editCart/{id}/{quantity}")
	public String editCart(HttpServletRequest request, HttpSession session, @PathVariable int id, @PathVariable int quantity) {
		HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>)session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDto>();
		}
		cart = cartServiceImpl.editCart(id, quantity, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantityCart", cartServiceImpl.totalQuantity(cart));
		session.setAttribute("TotalPriceCart", cartServiceImpl.totalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "deleteCart/{id}")
	public String deleteCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {
		HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>)session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDto>();
		}
		cart = cartServiceImpl.deleteCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantityCart", cartServiceImpl.totalQuantity(cart));
		session.setAttribute("TotalPriceCart", cartServiceImpl.totalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView checkOut(HttpServletRequest request, HttpSession session) {
		_mvShare.setViewName("user/bills/checkout");
		Bills bills = new Bills();
		Users loginInfo = (Users)session.getAttribute("loginInfo");
		if (loginInfo != null) {
			bills.setAddress(loginInfo.getAddress());
			bills.setDisplay_name(loginInfo.getDisplay_name());
			bills.setUser(loginInfo.getUser());
		}
		_mvShare.addObject("bills", bills);
		return _mvShare;
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String checkOutBill(HttpServletRequest request, HttpSession session, @ModelAttribute("bills") Bills bill) {
//		bill.setQuantity(Integer.parseInt((String) session.getAttribute("TotalQuantityCart")));
//		bill.setTotal(Double.parseDouble((String) session.getAttribute("TotalPriceCart")));
		
		if (billsService.addBills(bill) > 0) {
			HashMap<Integer, CartDto> carts = (HashMap<Integer, CartDto>)session.getAttribute("Cart");
			billsService.addBillDetail(carts);
		}
		session.removeAttribute("Cart");
		return "redirect:list-cart";
	}
	
	
}
