package com.bootstrapping.controller;

import com.bootstrapping.model.User;
import com.bootstrapping.service.AuthService;
import com.bootstrapping.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    NodeService nodeService;

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) throws Exception {
        user = authService.login(user);
        if (user==null){
            return "wrong username or password";
        }
        return nodeService.assignUserToNode(user);
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user){
        return authService.signup(user);
    }

    @PostMapping("/signout")
    public void signOut(@RequestBody User user){
        //remove user from his assigned node
        nodeService.clearNode(user);
    }
}
