package com.gangdestrois.smartimmo.domain.tool;

import java.util.List;

public interface Composite<T> {
    List<T> getChildren();

    void addChild(T t);

    Boolean removeChild(T t);

    Boolean removeChildren(List<T> t);
}
