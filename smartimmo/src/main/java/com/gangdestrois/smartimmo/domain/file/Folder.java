package com.gangdestrois.smartimmo.domain.file;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;

public class Folder extends ComponentImpl implements Composite<Component>, Component {
    List<Component> components;

    Folder(String name, Prospect owner) {
        super(name, owner);
    }

    @Override
    public List<Component> getChildren() {
        return null;
    }

    @Override
    public void addChild(Component c) {

    }

    @Override
    public Boolean removeChild(List<Component> t) {
        return null;
    }

    @Override
    public Boolean removeChildren(List<Component> t) {
        return null;
    }

    public void setOwner(Prospect owner, Boolean recursive) {

    }

    public Integer getSize() {
        return null;
    }

    public String getContent() {
        return null;
    }

    public void appendContent(String name) {
    }

    public Boolean isComposite() {
        return null;
    }

    public String toString() {
        return null;
    }
}
