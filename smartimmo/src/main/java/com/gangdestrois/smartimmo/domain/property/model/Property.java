package com.gangdestrois.smartimmo.domain.property.model;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.document.util.Holder;

import java.util.List;

public class Property extends Holder {
    private final Long id;
    private final String propertyName;
    private final String description;
    private final int roomsNumber;
    private final double livableArea;
    private final Address address;

    public Property(Long id, String propertyName, String description, int roomsNumber, double livableArea, Address address) {
        this.id = id;
        this.propertyName = propertyName;
        this.description = description;
        this.roomsNumber = roomsNumber;
        this.livableArea = livableArea;
        this.address = address;
    }

    @Override
    public List<File> getFiles(DocumentSpi documentSpi) {
        return documentSpi.getFileByDocumentHolder(this);
    }

    @Override
    public Long id() {
        return this.id;
    }

    public String propertyName() {
        return propertyName;
    }

    public String description() {
        return description;
    }

    public int roomsNumber() {
        return roomsNumber;
    }

    public double livableArea() {
        return livableArea;
    }

    public Address address() {
        return address;
    }
}
