package com.gangdestrois.smartimmo.domain.file;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class File extends ComponentImpl implements Component {
    private StringBuilder content;

    File(String name, Prospect owner) {
        super(name, owner);
    }

    public Integer getSize() {
        return null;
    }

    public String getContent() {
        return null;
    }

    public void appendContent(String content) {

    }

    public Boolean isComposite() {
        return null;
    }

    public String toString() {
        return null;
    }

}
