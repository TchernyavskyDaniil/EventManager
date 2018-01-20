package com.eve.controller;
import com.eve.entity.User;
import com.eve.entity.VerificationToken;
import com.eve.service.IUserService;
import com.eve.service.UserService;
import com.eve.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ComponentScan(basePackages = "com.eve.service")
public class RegistrationController {

    @Autowired
    @Qualifier("UserService")
    private IUserService userService;

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @PostMapping("/registration")
    @ResponseBody
    public ModelAndView registerUserAccount(
            @ModelAttribute("user")
            @Valid final UserDto accountDto,
            BindingResult result, WebRequest request, Errors errors) {

        final VerificationToken token = userService.createNewAccount(accountDto);

        Map<String, String> model = new HashMap<>();
        model.put("username", token.getUser().getUsername());
        model.put("email", token.getUser().getEmail());
        model.put("token", token.getToken());
        model.put("link", "http://127.0.0.1:8080/registration/confirm_account?token=" + token.getToken());
        return new ModelAndView("successRegister", "user", accountDto);
    }

    @RequestMapping("/info")
    public @ResponseBody String userInfo(Authentication authentication) {
        String msg = "";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            msg+=userService.getMsg()+ authentication.getName()+", You have "+ role;
        }
        return msg;
    }

    @GetMapping("/confirm_account")
    public User confirmAccount(@ModelAttribute(value = "token") String token){
        return userService.confirmUserAccount(token);
    }

}
