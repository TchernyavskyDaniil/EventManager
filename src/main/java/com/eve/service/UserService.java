package com.eve.service;

import com.eve.entity.Role;
import com.eve.entity.User;
import com.eve.entity.VerificationToken;
import com.eve.repository.RoleRepository;
import com.eve.repository.UserRepository;
import com.eve.repository.VerificationTokenRepository;
import com.eve.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service("UserService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public VerificationToken createNewAccount(UserDto accountDto) {
        User founded = userRepository.findByUsername(accountDto.getUsername());
        if (founded!=null){
            return null;
        }

        final User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName("USER");
        roles.add(role);
        user.setRoles(roles);
        user.disable();
        User r = userRepository.save(user);

        final String token = UUID.randomUUID().toString();
        final VerificationToken myToken = new VerificationToken(token, r);
        return tokenRepository.save(myToken);

    }

    @Transactional
    @Override
    public User confirmUserAccount(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (!verificationToken.equals(null)){
            User user = verificationToken.getUser();
            user.enable();
            tokenRepository.delete(verificationToken);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUser(String usernameOrEmail) {
        if (userRepository.findByUsername(usernameOrEmail) != null)
            return userRepository.findByUsername(usernameOrEmail);
        else
            return userRepository.findByEmail(usernameOrEmail);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    public String getMsg() {
        return "Hello ";
    }
}
