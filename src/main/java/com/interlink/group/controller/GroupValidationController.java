package com.interlink.group.controller;

import com.interlink.entity.Group;
import com.interlink.group.service.GroupValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupValidationController {
    @Autowired
    private GroupValidationService groupValidationService;

    @CrossOrigin
    @GetMapping(path = "/groups/{name}")
    public boolean isGroupExists(@PathVariable String name){
        Group group = new Group();
        group.setName(name);
        return groupValidationService.checkGroupExistence(name);
    }
}
