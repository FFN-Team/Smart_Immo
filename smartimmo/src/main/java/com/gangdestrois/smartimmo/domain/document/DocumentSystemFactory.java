package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public interface DocumentSystemFactory {
    Component createComponent(String name, Prospect owner);
}
