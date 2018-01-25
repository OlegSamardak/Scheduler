package com.interlink.repository;

import com.interlink.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by os199 on 25.01.2018.
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {

    boolean existsByName(Group group);
}
