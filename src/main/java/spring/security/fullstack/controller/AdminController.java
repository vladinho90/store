package spring.security.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.fullstack.service.IUserService;

@RestController
public class AdminController {

    @Autowired
    private IUserService userService;

    @GetMapping("/api/admin/all")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
