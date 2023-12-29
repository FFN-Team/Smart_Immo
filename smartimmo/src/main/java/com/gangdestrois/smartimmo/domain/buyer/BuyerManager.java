package com.gangdestrois.smartimmo.domain.buyer;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerApi;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;

import java.util.List;

public class BuyerManager implements BuyerApi {
    private final BuyerSpi buyerSpi;

    public BuyerManager(BuyerSpi buyerSpi) {
        this.buyerSpi = buyerSpi;
    }

    @Override
    public List<Buyer> findAllBuyers() {
        return buyerSpi.findAllBuyers();
    }

    @Override
    public Buyer findBuyerById(Long id) {
        return buyerSpi.findBuyerById(id);
    }
}
