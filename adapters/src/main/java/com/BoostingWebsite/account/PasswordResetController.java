package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/reset/password")
class PasswordResetController extends BaseController {
    private static final Logger logger  = LoggerFactory.getLogger(PasswordResetController.class);

    private final PasswordResetFacade facade;

    PasswordResetController(PasswordResetFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String updatePassword() {
        return "account/update-password";
    }

    @PostMapping
    String resetPassword(@RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        try {
            facade.resetPassword(password, repeatPassword);
            model.addAttribute("message", "Your password has been successfully reset!");
            return "redirect:/login";
        } catch (DataMismatchException ex) {
            logger.error("Error during reseting password!", ex);
            model.addAttribute("message", ex.getMessage());
            return "account/update-password";
        }
    }
}
