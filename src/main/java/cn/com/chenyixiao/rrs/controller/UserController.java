package cn.com.chenyixiao.rrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.chenyixiao.rrs.entity.User;
import cn.com.chenyixiao.rrs.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addUser(User user) {
		userService.addUser(user);
		return "success";
	}
}
