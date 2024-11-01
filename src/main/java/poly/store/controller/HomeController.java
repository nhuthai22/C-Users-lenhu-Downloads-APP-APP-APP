package poly.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.store.service.AccountService;



@Controller
public class HomeController {
	@Autowired
	AccountService accountService;


	@RequestMapping(value = "/home/index", method = RequestMethod.GET)
    public String home(Model model) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
        return "ad/index"; // Ensure this matches the view name in your templates
    }
}

