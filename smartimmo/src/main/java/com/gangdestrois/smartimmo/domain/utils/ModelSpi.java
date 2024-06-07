package com.gangdestrois.smartimmo.domain.utils;

import java.util.Optional;

public interface ModelSpi<T extends Model> {
    Optional<T> findById(Long id);
}
