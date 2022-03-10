package com.epam.library.entity;


import java.io.Serializable;
import java.util.Objects;

public abstract class Entity implements Serializable, Identifable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
