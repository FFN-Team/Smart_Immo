package com.gangdestrois.smartimmo.controller;

import com.gangdestrois.smartimmo.model.dto.response.BienResponse;
import com.gangdestrois.smartimmo.service.BienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BienController {
    @Autowired
    private BienService bienService;

    @GetMapping("/bien")
    public List<BienResponse> getBien(){
        return bienService.getBien();
    }

}
