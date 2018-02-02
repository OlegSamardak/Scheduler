package com.interlink.repository;

import com.interlink.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    boolean existsByName(Group group);
}
