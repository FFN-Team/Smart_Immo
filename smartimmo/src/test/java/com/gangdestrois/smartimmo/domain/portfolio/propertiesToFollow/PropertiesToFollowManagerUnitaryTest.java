package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow;

import com.gangdestrois.smartimmo.DataForUnitaryTest;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.error.ExceptionEnum;
import com.gangdestrois.smartimmo.domain.error.NotFoundException;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowSpi;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.BuyerDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.PropertyDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.PropertyToFollowDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PropertiesToFollowManagerUnitaryTest {
    private PropertyToFollowSpi propertyToFollowSpi;
    private PropertySpi propertySpi;
    private BuyerSpi buyerSpi;
    private PropertiesToFollowManager propertiesToFollowManager;

    @BeforeEach
    void setUp() {
        propertyToFollowSpi = mock(PropertyToFollowDataAdapter.class);
        propertySpi = mock(PropertyDataAdapter.class);
        buyerSpi = mock(BuyerDataAdapter.class);
        propertiesToFollowManager = new PropertiesToFollowManager(buyerSpi, propertySpi, propertyToFollowSpi);
    }

    @Test
    public void savePropertiesToFollowForBuyer_should_throw_not_found_exception_buyer_is_not_found() {
        // Getters
        Long buyerId = 1L;
        when(buyerSpi.findBuyerById(buyerId)).thenReturn(null);

        // When and then
        var result = assertThrows(NotFoundException.class, () ->
                propertiesToFollowManager.savePropertiesToFollowForBuyer(buyerId));
        assertEquals(ExceptionEnum.BUYER_NOT_FOUND, result.getError());
    }
}
