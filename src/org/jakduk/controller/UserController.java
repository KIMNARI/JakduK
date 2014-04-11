package org.jakduk.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.jakduk.model.User;
import org.jakduk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/users")
@SessionAttributes("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/list")
	public void list(Model model) {
//		logger.debug("/list : " + model);
		
		List<User> users = userService.findAll();
		logger.debug("/list : " + users);
		
		model.addAttribute("list", users);
	}
	
	@RequestMapping(value = "/add")
	public void add(Model model) {
		logger.debug("/add : ");
		model.addAttribute("user", new User());
		
//		User user = new User();
//		user.setId("112");
//		user.setUserName("Gwangsu");
//		userService.create(user);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addSubmit(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "users/add";
		}
		
		userService.create(user);
		return "redirect:/users/list";
	}
	
}