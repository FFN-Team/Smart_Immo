package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Objects;

public abstract class ComponentImpl implements Component, Model {
    private Long id;
    private final String name;
    private Prospect owner;

    public ComponentImpl(String name, Prospect owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public Prospect getOwner() {
        return this.owner;
    }

    public void setOwner(Prospect owner, Boolean recursive) {
        this.owner = owner;
    }

    public Long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentImpl component = (ComponentImpl) o;
        return Objects.equals(name, component.name) && Objects.equals(owner, component.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner);
    }
}
