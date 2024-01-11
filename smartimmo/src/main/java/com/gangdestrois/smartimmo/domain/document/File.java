package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class File extends ComponentImpl implements Component {
    private String url;

    File(String name, Prospect owner) {
        super(name, owner);
    }

    public Integer getSize() {
        return null;
    }

    public String getUrl() {
        return this.url;
    }

    public void appendContent(String content) {
    }

    public Boolean isComposite() {
        return false;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
