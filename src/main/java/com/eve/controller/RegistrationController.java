package com.eve.controller;
import com.eve.entity.User;
import com.eve.entity.VerificationToken;
import com.eve.repository.UserRepository;
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

@Controller
@ComponentScan(basePackages = "com.eve.service")
public class RegistrationController {

    @Autowired
    @Qualifier("UserService")
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/perform_login")
//    public String login(@ModelAttribute("username") String username,
//                        @ModelAttribute("password") String password, Model model){
//        User user = userRepository.findByUsername(username);
//        if (user==null){
//            model.addAttribute("message","User not found");
//            return "loginPage";
//        }
//        boolean authenticated = userService.checkPassword(user.getId(),password);
//        if (authenticated){
//            return "eventsPage";
//        }
//        model.addAttribute("message","Password not correct");
//        return "errorPage";
//    }

    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

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

    @RequestMapping("/info")
    public @ResponseBody String userInfo(Authentication authentication) {
        String msg = "";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            msg+=userService.getMsg()+ authentication.getName()+", You have "+ role;
        }
        return msg;
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
