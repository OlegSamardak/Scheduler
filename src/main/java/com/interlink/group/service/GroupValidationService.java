package com.interlink.group.service;


import com.interlink.entity.Group;
import com.interlink.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupValidationService {
    @Autowired
    private GroupRepository groupRepository;

    public boolean checkGroupExistence(Group group){
        return groupRepository.existsByName(group);
    }
}
