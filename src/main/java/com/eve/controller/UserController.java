package com.eve.controller;

import com.eve.entity.User;
import com.eve.repository.UserRepository;
import com.eve.service.UserService;
import com.eve.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserService userService;

    @GetMapping("/user/profile")
    public String getUserProfile(Principal principal,Model model){

        User user = userRepository.findByUsername(principal.getName());
        if (user==null){
            model.addAttribute("message","User not found");
            return "errorPage";
        }
        UserDto userDto = new UserDto(user);
        model.addAttribute("user",userDto);
        return "userProfile";
    }

    @PostMapping("/user/change_info")
    public String changeInfo(@ModelAttribute("user") UserDto userDto, Model model){
        User user = userRepository.findById(userDto.getId());
        if(user==null){
            model.addAttribute("message","User not found");
            return "errorPage";
        }
        if (userDto.getPassword()!=null &&
                userDto.getPassword().equals(userDto.getMatchingPassword())){
            if (userService.changePassword(userDto.getId(),userDto.getPassword())){
                return "userProfile";
            }
        }
        model.addAttribute("message","Password uncorrect");
        return "errorPage";
    }

}