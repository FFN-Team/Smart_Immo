package com.gangdestrois.smartimmo;

import com.gangdestrois.smartimmo.domain.document.Folder;
import com.gangdestrois.smartimmo.domain.property.model.*;

public class DataForUnitaryTest {
    public final Folder folder1 = new Folder("test", "test", "test", "test");
    public final byte[] file = new byte[1];
    public final Property property=new Property(1L, "test", "test", 2, 50, new Address(1L, 1, 1, 1, new Street(1L, "streetName", 20.0), new Area(1L, "areaName", 20.0), new City(1L, "cityName", 20.0)));
    public final Property property2=new Property(2L, "test", "test", 3, 60, new Address(1L, 1, 1, 1, new Street(1L, "streetName", 20.0), new Area(1L, "areaName", 20.0), new City(1L, "cityName", 20.0)));

}
