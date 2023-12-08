package com.gangdestrois.smartimmo.domain.buyer.port;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;

import java.util.List;

public interface BuyerApi {
    List<Buyer> findAllBuyers();
    Buyer findBuyerById(int id);
}
