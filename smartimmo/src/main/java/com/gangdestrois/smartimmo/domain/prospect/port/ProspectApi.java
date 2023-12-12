package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;

import java.util.Set;
import java.util.List;
import java.util.Map;
  
public interface ProspectApi {
    Set<Event> notifyForProspectsThatMayBuyBiggerHouse();

    void subscription(EventListener eventListener);
    
    List<Prospect> findAll();
    ProspectStatisticsResponse countByAgeGroup();
    ProspectStatisticsResponse countByProfession();
    ProspectStatisticsResponse countByContactOrigin();
}
