package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;

public interface ProspectManagerApi {
    List<Prospect> findAll();
}
