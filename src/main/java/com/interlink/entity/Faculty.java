package com.interlink.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Faculty extends BaseEntity{
    private String name;
    private boolean active;
}
