package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.property.entite.Property;

import java.util.List;

public interface PropertyToFollowSpi {
    List<PropertyToFollow> findAll();
    List<PropertyToFollow> findAllByBuyerId(int buyerId);

    //Enregistre une nouvelle PropertyToFollow associée à l'acheteur et à la propriété spécifiés
    void savePropertyToFollow(Buyer buyer, Property property);

    //Supprime toutes les entités PropertyToFollow associées à un acheteur spécifié par son ID
    void deletePropertiesToFollowForBuyer(int buyerId);
}
