package com.springBootMvcHibernate.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.springBootMvcHibernate.models.Users;
import com.springBootMvcHibernate.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/login");
		
		return model;
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		Users user = new Users();
		model.addObject("user", user);
		model.setViewName("/signup");

		return model;
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView createUser(@Valid Users user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		Users userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("/signup");
		} else {
			userService.saveUser(user);
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("user", new Users());
			model.setViewName("/login");
		}

		return model;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findUserByEmail(auth.getName());

		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("/home");
		return model;
	}

	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/access_denied");
		return model;
	}
	
	@RequestMapping(value = {"/logout"},  method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/");
		return model;
	} 
	
}