package com.interlink.group.service;


import com.interlink.entity.Group;
import com.interlink.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupValidationService {
    @Autowired
    private GroupRepository groupRepository;

    public boolean checkGroupExistence(String group){
        return groupRepository.checkExistence(group);
    }

    public void saveNewGroup(Group group){
        groupRepository.save(group);
    }
}
