package com.interlink.repository;

import com.interlink.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query(nativeQuery = false, value = "SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM Group as g WHERE g.name = :groupName")
    boolean checkExistence(@Param("groupName") String groupName);
}
