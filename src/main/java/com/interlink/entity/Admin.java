package com.interlink.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Admin extends BaseEntity{
    private String login;
    private String password;
    private boolean active;
}
