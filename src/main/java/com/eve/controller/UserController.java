package com.eve.controller;

import com.eve.entity.Role;
import com.eve.entity.User;
import com.eve.repository.RoleRepository;
import com.eve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller

public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    @RequestMapping(path = {"/","/home"}, method = RequestMethod.GET)
    public String index(Model model){
        User user = new User("kek","kek","kek@kek.kek");
		Role role = new Role("Admin");

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
        userRepository.save(user);

        Set<User> users = new HashSet<>();
        users.add(user);
		role.setUsers(users);
		roleRepository.save(role);

        List<User> userList = userRepository.findAll();
		List<Role> roleList = roleRepository.findAll();

        System.out.println(userList.size());
		System.out.println(roleList.size());

        return "index";
    }
}
