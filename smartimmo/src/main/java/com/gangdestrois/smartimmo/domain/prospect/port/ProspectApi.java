package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Map;

public interface ProspectApi {
    List<Prospect> findAll();
    Map<String, Map<String, Long>> countByAgeGroup();
    Map<String, Map<String, Long>> countByProfession();
    Map<String, Map<String, Long>> countByContactOrigin();
}
