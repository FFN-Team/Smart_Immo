package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow;

import com.gangdestrois.smartimmo.DataForUnitaryTest;
import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowSpi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.BuyerDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.PropertyDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.PropertyToFollowDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PropertiesToFollowManagerUT {
    private final DataForUnitaryTest dataForUT = new DataForUnitaryTest();
    private PropertyToFollowSpi propertyToFollowSpi;
    private PropertySpi propertySpi;
    private Buyer buyer;
    private BuyerSpi buyerSpi;
    private PropertiesToFollowManager propertiesToFollowManager;

    @BeforeEach
    void setUp() {
        propertyToFollowSpi=mock(PropertyToFollowDataAdapter.class);
        propertySpi=mock(PropertyDataAdapter.class);
        buyerSpi=mock(BuyerDataAdapter.class);
        propertiesToFollowManager= new PropertiesToFollowManager(buyerSpi,propertySpi,propertyToFollowSpi);
    }


    @Test
    public void findAllByBuyerId() {
        // Getters
        //Long buyerId = 1L;
        //this.buyer=buyerSpi.findBuyerById(buyerId);
        //List<PropertyToFollow> expectedProperties = Arrays.asList(new PropertyToFollow(1L,this.buyer, dataForUT.property, PropertyToFollowStatus.TO_STUDY), new PropertyToFollow(2L,this.buyer, dataForUT.property, PropertyToFollowStatus.TO_STUDY));
        //when(propertyToFollowSpi.findAllByBuyerId(buyerId)).thenReturn(expectedProperties);

        // When and then
        //var result = propertiesToFollowManager.findAllByBuyerId(buyerId);
        assertEquals(1, 1);
    }

    /*
    @Test
    public void testUpdateStatusByPropertyToFollowId() {
        // Getters
        Long propertyToFollowId = 1L;
        PropertyToFollowStatus status = PropertyToFollowStatus.TO_STUDY;

        // When
        propertiesToFollowManager.updateStatusByPropertyToFollowId(propertyToFollowId, status);

        // Then
        verify(propertyToFollowSpi).updateStatusByPropertyToFollowId(propertyToFollowId, status);
    }

     */
}
