package com.interlink.group.controller;

import com.interlink.entity.Group;
import com.interlink.group.service.GroupValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupValidationController {
    @Autowired
    private GroupValidationService groupValidationService;

    @GetMapping(path = "/groups")
    public boolean isGroupExists(@RequestParam Group group){
        return groupValidationService.checkGroupExistence(group);
    }
}
