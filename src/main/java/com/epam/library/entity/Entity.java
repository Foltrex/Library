package com.epam.library.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Identifable {

    private Long id;

    // TODO: make all classes serialiazable
    public Entity() {
    }

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
