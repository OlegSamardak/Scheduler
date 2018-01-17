package com.interlink.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Teacher extends BaseEntity{
    private String name;
    private String surname;
    private String lastName;
    @ManyToOne
    private Faculty faculty;
    @ManyToOne
    private Status status;
}
