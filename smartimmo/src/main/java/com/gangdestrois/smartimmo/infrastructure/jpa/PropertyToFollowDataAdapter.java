package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.enums.PropertyToFollowStatus;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowSpi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.BuyerEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyToFollowEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyToFollowRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertyToFollowDataAdapter implements PropertyToFollowSpi {
    PropertyToFollowRepository propertyToFollowRepository;

    @Autowired
    public PropertyToFollowDataAdapter(PropertyToFollowRepository propertyToFollowRepository) {
        this.propertyToFollowRepository = propertyToFollowRepository;
    }

    @Override
    public List<PropertyToFollow> findAll() {
        return propertyToFollowRepository.findAll()
                .stream()
                .map(PropertyToFollowEntity::toModel)
                .toList();
    }

    @Override
    public List<PropertyToFollow> findAllByBuyerId(Long buyerId) {
        return propertyToFollowRepository.findPropertyToFollowEntitiesByBuyer_Id(buyerId)
                .stream()
                .map(PropertyToFollowEntity::toModel)
                .toList();
    }

    @Override
    @Transactional
    public void savePropertyToFollowForBuyer(Buyer buyer, Property property) {
        PropertyToFollowEntity propertyToFollowSaved = propertyToFollowRepository.save(
                new PropertyToFollowEntity(
                        BuyerEntity.fromModelToEntity(buyer),
                        PropertyEntity.fromModelToEntity(property),
                        PropertyToFollowStatus.TO_STUDY
                )
        );
    }

    @Override
    public void deletePropertyToFollowForBuyer(Long buyerId, Long propertyId) {
        propertyToFollowRepository.deleteByBuyerIdAndPropertyId(buyerId, propertyId);
    }

    @Override
    public void updateStatusByPropertyToFollowId(Long propertyToFollowId, PropertyToFollowStatus status) {
        propertyToFollowRepository.updateStatusByPropertyToFollowId(propertyToFollowId, status.name());
    }
}
