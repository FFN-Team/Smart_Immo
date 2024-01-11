package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;

public class Folder extends ComponentImpl implements Composite<Component>, Component {
    List<Component> components;

    Folder(String name, Prospect owner) {
        super(name, owner);
    }

    @Override
    public List<Component> getChildren() {
        return this.components;
    }

    @Override
    public void addChild(Component c) {
        this.components.add(c);
    }

    @Override
    public Boolean removeChild(Component t) {
        return this.components.remove(t);
    }

    @Override
    public Boolean removeChildren(List<Component> t) {
        return this.components.removeAll(t);
    }

    public void setOwner(Prospect owner, Boolean recursive) {
        super.setOwner(owner, recursive);
        if (recursive)
            for (Component c : components) {
                c.setOwner(owner, true);
            }
    }

    public Integer getSize() {
        return null;
    }

    public String getUrl() {
        return null;
    }

    public Boolean isComposite() {
        return true;
    }

    public String toString() {
        return null;
    }
}
