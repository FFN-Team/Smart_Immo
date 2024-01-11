package com.gangdestrois.smartimmo.domain.file;

import java.util.List;

public interface Composite<T> {
    List<T> getChildren();

    void addChild(T t);

    Boolean removeChild(List<T> t);

    Boolean removeChildren(List<T> t);
}
