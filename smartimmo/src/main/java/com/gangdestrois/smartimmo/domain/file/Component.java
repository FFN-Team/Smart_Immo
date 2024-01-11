package com.gangdestrois.smartimmo.domain.file;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public interface Component {
    String getName();

    Prospect getOwner();

    void setOwner(Prospect owner, Boolean recursive);

    Integer getSize();

    String getContent();

    void appendContent(String name);

    Boolean isComposite();
}
