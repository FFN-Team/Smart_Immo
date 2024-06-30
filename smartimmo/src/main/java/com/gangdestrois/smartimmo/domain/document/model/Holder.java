package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.utils.Model;

import java.util.List;

public abstract class Holder implements Model {
    public abstract List<File> getFiles(DocumentSpi documentSpi);
}
