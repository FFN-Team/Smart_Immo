package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyCriteriaEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyCriteriaRepository;

import java.util.List;

public class BuyerDataAdapter implements BuyerSpi {
    private final PropertyCriteriaRepository propertyCriteriaRepository;

    public BuyerDataAdapter(PropertyCriteriaRepository propertyCriteriaRepository) {
        this.propertyCriteriaRepository = propertyCriteriaRepository;
    }

    @Override
    public List<Buyer> findAllBuyers() {
        return propertyCriteriaRepository.findAll()
                .stream()
                .map(PropertyCriteriaEntity::toBuyerModel)
                .toList();
    }

    @Override
    public Buyer findBuyerById(Long buyerId) {
        PropertyCriteriaEntity propertyCriteriaEntity = propertyCriteriaRepository
                .findPropertyCriteriaEntitiesByBuyer_Id(buyerId);
        if(propertyCriteriaEntity != null) return propertyCriteriaEntity.toBuyerModel();
        else return null;
    }
}