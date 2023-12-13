package DiamonShop.Controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Entity.Users;
import DiamonShop.Service.User.AccountServiceImpl;

@Controller
public class UserController extends BaseController {
	@Autowired
	AccountServiceImpl accountService = new AccountServiceImpl();
	
	@RequestMapping(value = "/sign-up", method=RequestMethod.GET)
	public ModelAndView SignUp() {
		_mvShare.setViewName("user/account/sign-up");
		_mvShare.addObject("user", new Users());
		return _mvShare;
	}
	
	@RequestMapping(value = "/sign-up", method=RequestMethod.POST)
	public ModelAndView CreateAcc(@ModelAttribute("user") Users user) {
		int count = accountService.addAccount(user);
		if (count > 0) {
			_mvShare.addObject("statusCreate", "Create success");
		} else {
			_mvShare.addObject("statusCreate", "Create fail :((");
		}
		_mvShare.setViewName("user/account/sign-up");
		return _mvShare;
	}
	
	@RequestMapping(value = "/log-in", method=RequestMethod.POST)
	public ModelAndView LogIn(HttpSession session, @ModelAttribute("user") Users user) {
		user = accountService.checkAccount(user);		
		if (user != null) {
			_mvShare.setViewName("redirect:trang-chu");
			session.setAttribute("loginInfo", user);
		} else {
			_mvShare.addObject("statusLogin", "Log in fail :((");
		}	
		return _mvShare;
	}
	
	
	@RequestMapping(value = "/log-out", method=RequestMethod.GET)
	public String  LogOut(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("loginInfo");
		return "redirect:" + request.getHeader("Referer");
	}
}
