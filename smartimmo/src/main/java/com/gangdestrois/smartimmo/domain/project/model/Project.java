package com.gangdestrois.smartimmo.domain.project.model;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.tool.Model;

public record Project(Long id, Prospect prospect) implements Model {
}
