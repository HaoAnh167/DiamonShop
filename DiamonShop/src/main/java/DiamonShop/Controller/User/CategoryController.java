package DiamonShop.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Dto.PaginatesDto;
import DiamonShop.Service.User.CategoryServiceImpl;
import DiamonShop.Service.User.PaginateServiceImpl;

@Controller
public class CategoryController extends BaseController {
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	@Autowired
	private PaginateServiceImpl paginateServiceImpl;
	
	private int totalProductsPage = 9;
		
	@RequestMapping(value = "/san-pham/{id}")
	public ModelAndView Product(@PathVariable String id) {
		
		_mvShare.setViewName("user/products/category");			
		int totalData = categoryServiceImpl.getAllProductsByID(Integer.parseInt(id)).size();
		PaginatesDto paginatesInfo = paginateServiceImpl.getInfoPaginatesDto(totalData, totalProductsPage, 1);
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginatesInfo", paginatesInfo);
		_mvShare.addObject("productsPaginate", categoryServiceImpl.getDataProductsPaginate(Integer.parseInt(id), paginatesInfo.getStart(), totalProductsPage));
		return _mvShare;
	}
	
	@RequestMapping(value = "/san-pham/{id}/{currentPage}")
	public ModelAndView Product(@PathVariable String id, @PathVariable String currentPage) {
		
		_mvShare.setViewName("user/products/category");					
		int totalData = categoryServiceImpl.getAllProductsByID(Integer.parseInt(id)).size();
		PaginatesDto paginatesInfo = paginateServiceImpl.getInfoPaginatesDto(totalData, totalProductsPage, Integer.parseInt(currentPage));
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginatesInfo", paginatesInfo);
		_mvShare.addObject("productsPaginate", categoryServiceImpl.getDataProductsPaginate(Integer.parseInt(id), paginatesInfo.getStart(), totalProductsPage));
		return _mvShare;
	}
}
