package DiamonShop.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{
	
	@RequestMapping(value = {"/", "/trang-chu"})
	public ModelAndView Index() {	
		_mvShare.addObject("slides", _homeService.getDataSlides());
		_mvShare.addObject("categorys", _homeService.getDataCategorys());
		_mvShare.addObject("products", _homeService.getDataProductsDto()); 
		_mvShare.setViewName("user/index");
		return _mvShare;
	}
}
