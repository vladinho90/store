package spring.security.fullstack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.fullstack.model.User;
import spring.security.fullstack.repository.UserRepository;
import spring.security.fullstack.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user){
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
