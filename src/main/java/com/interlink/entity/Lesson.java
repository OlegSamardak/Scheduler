package com.interlink.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Setter
@Getter
public class Lesson extends BaseEntity{
    @ManyToOne
    private Schedule schedule;
    private Date date;
}
