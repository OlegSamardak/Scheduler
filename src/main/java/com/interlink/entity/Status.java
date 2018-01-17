package com.interlink.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by os199 on 17.01.2018.
 */
@Entity
@Getter
@Setter
public class Status extends BaseEntity {
    private String name;
}
