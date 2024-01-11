package com.gangdestrois.smartimmo.domain.file;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class ComponentImpl {
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
}
