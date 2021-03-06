package com.BoostingWebsite.home;

import com.BoostingWebsite.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
class HomeController extends BaseController {

    @GetMapping
    String showHomePage() {
        return "home";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "home";
    }
}
