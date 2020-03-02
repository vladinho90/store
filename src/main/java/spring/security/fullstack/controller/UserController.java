package spring.security.fullstack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import spring.security.fullstack.jwt.JwtTokenProvider;
import spring.security.fullstack.model.User;
import spring.security.fullstack.model.enums.Role;
import spring.security.fullstack.service.IUserService;

import java.security.Principal;

@RestController
@RequestMapping(UserController.API_USER)
public class UserController {

    public static final String API_USER = "api/user";
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IUserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername())!= null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(userService.findByEmail(user.getEmail())!= null){
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        //in client side you can sent authroization token value with request header
        //in spring you can get this security value with principal
        if(principal == null) {
            //This should be ok hhtp status because this will be user for logout path
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(jwtTokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
