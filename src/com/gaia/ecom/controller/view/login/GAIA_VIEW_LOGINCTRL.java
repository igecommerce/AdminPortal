package com.gaia.ecom.controller.view.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping(value="/welcomeboard")
public class GAIA_VIEW_LOGINCTRL  {
	@RequestMapping(value="/login",method = RequestMethod.GET)
    public String getLoginPage() {
        return "template/login";
    }

}