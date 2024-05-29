package com.testing.course.repository;

import com.testing.course.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserList {

    public List<User> userList(){
        List<User> users = new ArrayList<>();

        users.add(new User(true, "user1"));
        users.add(new User(false, "user2"));
        users.add(new User(true, "user3"));
        return users;
    }
}
