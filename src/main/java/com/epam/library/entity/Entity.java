package com.epam.library.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Identifable {
    private long id;

    public Entity() {
    }

    public Entity(long id) {
        this.id = id;
    }


    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
