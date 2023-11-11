package com.gangdestrois.smartimmo.service;

import com.gangdestrois.smartimmo.model.dto.response.BienResponse;
import com.gangdestrois.smartimmo.model.entity.Bien;
import com.gangdestrois.smartimmo.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BienService {
    @Autowired
    private BienRepository bienRepository;

    public List<BienResponse> getBien() {
        var biens = bienRepository.findAll();
        List<BienResponse> bienResponses = new ArrayList<>();
        biens.forEach(bien -> {
            bienResponses.add(new BienResponse(bien.getNomBien(), bien.getDescription()));
        });
        return bienResponses;
    }

}
