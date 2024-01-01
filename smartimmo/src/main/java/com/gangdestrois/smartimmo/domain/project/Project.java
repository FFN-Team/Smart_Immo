package com.gangdestrois.smartimmo.domain.project;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public record Project(Long id, Prospect prospect) implements Model {
}
