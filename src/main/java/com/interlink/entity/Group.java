package com.interlink.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Group extends BaseEntity{
    private String name;
    private int course;
    @ManyToOne
    private Faculty faculty;
    private boolean active;
}
