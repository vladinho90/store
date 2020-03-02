package spring.security.fullstack.service;

import spring.security.fullstack.model.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllUsers();
}
