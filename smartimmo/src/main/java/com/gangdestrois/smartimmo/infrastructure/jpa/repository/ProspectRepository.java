package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProspectRepository extends JpaRepository<ProspectEntity,Long> {
    @Override
    List<ProspectEntity> findAll();

    @Query("SELECT COUNT(*) " +
            "FROM ProspectEntity " +
            "WHERE YEAR(CURRENT_DATE) - YEAR(dateOfBirth) BETWEEN :age_min AND :age_max")
    long countByAgeBetween(@Param("age_min") int ageMin, @Param("age_max") int ageMax);

    @Query("SELECT profession, COUNT(*) " +
            "FROM ProspectEntity " +
            "GROUP BY profession " +
            "ORDER BY COUNT(*)")
    List<Object[]> countByProfession();

    @Query("SELECT contactOrigine, COUNT(*) " +
            "FROM ProspectEntity " +
            "GROUP BY contactOrigine " +
            "ORDER BY COUNT(*)")
    List<Object[]> countByContactOrigin();
}
