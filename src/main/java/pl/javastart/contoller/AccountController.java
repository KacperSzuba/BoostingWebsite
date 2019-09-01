package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.manage.EmailChange;
import pl.javastart.manage.PasswordChange;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordChange passwordChange;

    @Autowired
    private EmailChange emailChange;

    @RequestMapping
    public String showAccountPage(){
        return "jsp/account";
    }

    @GetMapping("/showChangePasswordPage")
    public String showChangePasswordPage(){
        return "jsp/user_ChangePassword";
    }

    @GetMapping("/changePasswordForm")
    public String changePassword(@RequestParam ("password") String password,
        @RequestParam("repeatPassword")String repeatPassword, Model model,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName());
        passwordChange.changePassword(user.getId(),password,repeatPassword);
        model.addAttribute("newPassword",passwordChange.getMessage());
        return "jsp/user_ChangePassword";
    }

    @GetMapping("/showEmailChangePage")
    public String showEmailChangePage(){
        return "jsp/user_ChangeEmail";
    }

    @GetMapping("/changeEmailForm")
    public String changeEmail(@RequestParam ("email") String email,
        @RequestParam("repeatEmail")String repeatEmail, Model model,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName());
        emailChange.changeEmail(user.getId(),email,repeatEmail);
        model.addAttribute("newEmail",emailChange.getMessage());
        return "jsp/user_ChangeEmail";
    }
}
