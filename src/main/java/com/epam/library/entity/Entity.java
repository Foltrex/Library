package com.epam.library.entity;


import java.io.Serializable;

public abstract class Entity implements Serializable, Identifable {

    public static final String ID = "id";

    private Long id;

    public Entity(Long id) {
        this.id = id;
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
