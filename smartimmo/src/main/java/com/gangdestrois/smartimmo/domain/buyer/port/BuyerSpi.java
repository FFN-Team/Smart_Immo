package com.gangdestrois.smartimmo.domain.buyer.port;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;

import java.util.List;

public interface BuyerSpi {
    List<Buyer> findAllBuyers();
    Buyer findBuyerById(Long id);
}
