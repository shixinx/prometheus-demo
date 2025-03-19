package com.prometheus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("")
    public String hello() {
        return "hello";
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) throws InterruptedException {
        System.out.println(user.toString());
        for (int i = 0; i < 500; i++) {
            User user1 = new User(UUID.randomUUID().toString(), 3);
            System.out.println(user1.toString());
        }
        Thread.sleep(200);
        return user;
    }
}
