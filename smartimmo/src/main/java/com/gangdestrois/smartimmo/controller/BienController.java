package com.gangdestrois.smartimmo.controller;

import com.gangdestrois.smartimmo.model.entity.Bien;
import com.gangdestrois.smartimmo.service.BienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BienController {
    @Autowired
    private BienService bienService;

    @GetMapping("/bien")
    public Iterable<Bien> getBien(){
        return bienService.getBien();
    }

}
