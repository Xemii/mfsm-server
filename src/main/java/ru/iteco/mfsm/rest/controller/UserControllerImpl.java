package ru.iteco.mfsm.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.iteco.mfsm.dao.mfsm.MFSMDAO;
import ru.iteco.mfsm.rest.dto.UserDTO;

import javax.annotation.PostConstruct;

@RestController
public class UserControllerImpl {
    @Autowired
    @Qualifier("BEAN_MFSMDAO")
    private MFSMDAO mfsmdao;

    @PostConstruct
    public void afterInit() {
        System.out.println("UserControllerImpl inited");
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUserById(@PathVariable("userId") String id) {
        UserDTO user = new UserDTO();
        user.setId("ID");
        return user;
    }

    @PostMapping("/user")
    public UserDTO saveUser(@RequestBody UserDTO user) {
        return mfsmdao.createEntity("User", user, UserDTO.class);
    }
}
