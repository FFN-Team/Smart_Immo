package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyToFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface PropertyToFollowRepository extends JpaRepository<PropertyToFollowEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from property_to_follow where fk_buyer = :buyerId", nativeQuery = true)
    void deleteAllByBuyer_Id(@Param("buyerId") Long buyerId);

    List<PropertyToFollowEntity> findPropertyToFollowEntitiesByBuyer_Id(Long buyerId);

    @Modifying
    @Transactional
    @Query(value = "update property_to_follow set status = :status where  id_property_to_follow= :propertyToFollowId", nativeQuery = true)
    void updateStatusByPropertyToFollowId(Long propertyToFollowId, String status);

    @Modifying
    @Transactional
    @Query(value = "delete from property_to_follow where fk_buyer = :buyerId and fk_property= :propertyId", nativeQuery = true)
    void deleteByBuyerIdAndPropertyId(@Param("buyerId") Long buyerId, @Param("propertyId") Long propertyId);
}
