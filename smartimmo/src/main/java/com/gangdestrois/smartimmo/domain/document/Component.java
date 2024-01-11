package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public interface Component {
    String getName();

    Prospect getOwner();

    void setOwner(Prospect owner, Boolean recursive);

    Integer getSize();

    String getUrl();

    Boolean isComposite();
}
