package com.interlink.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Secretary extends BaseEntity{
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean active;
    @ManyToOne
    private Faculty faculty;
}
