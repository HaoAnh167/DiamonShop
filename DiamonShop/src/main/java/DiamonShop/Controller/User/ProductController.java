package DiamonShop.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Service.User.IProductService;
import DiamonShop.Service.User.ProductServiceImpl;

@Controller
public class ProductController extends BaseController{
	@Autowired
	private IProductService _productService;
	
	@RequestMapping(value = "/product-detail/{id}")
	public ModelAndView Index(@PathVariable int id) {	
		_mvShare.setViewName("user/products/product");
		_mvShare.addObject("product", _productService.getProductByID(id));
		int idCate = _productService.getProductByID(id).getId_category();
		_mvShare.addObject("productByIDCategory", _productService.getProductByIDCategory(idCate));
		return _mvShare;
	}
}
