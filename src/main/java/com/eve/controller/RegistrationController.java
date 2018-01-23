package com.eve.controller;
import com.eve.entity.User;
import com.eve.entity.VerificationToken;
import com.eve.service.IUserService;
import com.eve.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@ComponentScan(basePackages = "com.eve.service")
public class RegistrationController {

    @Autowired
    @Qualifier("UserService")
    private IUserService userService;



    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration/create_account")
    public String registerUserAccount(Model model,
            @ModelAttribute("user")
            @Valid final UserDto accountDto) {
        final VerificationToken token = userService.createNewAccount(accountDto);
        model.addAttribute("token", token.getToken());
        return "successRegister";
    }


    @GetMapping("/registration/confirm_account")
    public String confirmAccount(@ModelAttribute(value = "token") String token,Model model){
        User user = userService.confirmUserAccount(token);
        if (user==null){
            model.addAttribute("message","Account does not confirmed");
            model.addAttribute("link","/registration");
            return "errorPage";
        }
        return "index";
    }

}
