package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javastart.model.entity.User;
import pl.javastart.validator.RegisterValidator;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private RegisterValidator registerValidator;

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        User user = new User();
        model.addAttribute("register",user);
        return "register";
    }

    @PostMapping("/registerForm")
    public String register(@Valid @ModelAttribute("register") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        } else {
            if (registerValidator.createAccount(user)) {
                return "redirect:/login";
            } else {
                return "register";
            }
        }
    }
}
