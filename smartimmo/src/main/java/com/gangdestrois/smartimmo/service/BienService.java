package com.gangdestrois.smartimmo.service;

import com.gangdestrois.smartimmo.model.entity.Bien;
import com.gangdestrois.smartimmo.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BienService {
    @Autowired
    private BienRepository bienRepository;

    public Iterable<Bien> getBien() {
        return bienRepository.findAll();
    }

}
