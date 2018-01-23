package com.eve.service;

import com.eve.entity.Event;
import com.eve.entity.User;
import com.eve.entity.VerificationToken;
import com.eve.web.dto.UserDto;
import org.springframework.security.access.annotation.Secured;


public interface IEventService {
    Event createEvent(UserDto userDTO);
    User confirmUserAccount(String token);

    User getUser(long id);
    User getUser(String usernameOrEmail);
    Iterable<User> getUsers();
    void deleteUser(long id);

    @Secured("authenticated")
    public String getMsg();

}
