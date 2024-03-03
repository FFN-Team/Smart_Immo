package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProspectRepository extends JpaRepository<ProspectEntity, Long> {

    @Override
    List<ProspectEntity> findAll();

    @Query("""
            SELECT COUNT(*) 
            FROM ProspectEntity 
            WHERE YEAR(CURRENT_DATE) - YEAR(dateOfBirth) BETWEEN :age_min AND :age_max
            """)
    long countByAgeBetween(@Param("age_min") int ageMin, @Param("age_max") int ageMax);

    @Query("""
            SELECT profession, COUNT(*) 
            FROM ProspectEntity 
            GROUP BY profession 
            ORDER BY COUNT(*)
            """)
    List<Object[]> countByProfession();

    @Query("""
            SELECT contactOrigin, COUNT(*) 
            FROM ProspectEntity 
            GROUP BY contactOrigin 
            ORDER BY COUNT(*)
            """)
    List<Object[]> countByContactOrigin();

    List<ProspectEntity> findAllByContactOrigin(ContactOrigin contactOrigin);

    List<ProspectEntity> findAllByTitle(Title title);

    List<ProspectEntity> findAllByProfession(Profession profession);

    List<ProspectEntity> findAllByAuthorizeContactOnSocialMedia(boolean authorizeContactOnSocialMedia);

    @Query("""
                SELECT p
                FROM ProspectEntity p
                WHERE CASE 
                    WHEN :operator = 'EQUALS' THEN EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM p.dateOfBirth) = :age
                    WHEN :operator = 'NOT_EQUAL_TO' THEN EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM p.dateOfBirth) <> :age
                    WHEN :operator = 'GREATER_THAN' THEN EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM p.dateOfBirth) > :age
                    WHEN :operator = 'LESS_THAN' THEN EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM p.dateOfBirth) < :age
                    WHEN :operator = 'GREATER_THAN_OR_EQUAL_TO' THEN EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM p.dateOfBirth) >= :age
                    WHEN :operator = 'LESS_THAN_OR_EQUAL_TO' THEN EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM p.dateOfBirth) <= :age
                    ELSE false
                END
            """)
    List<ProspectEntity> findAllByAge(@Param("age") Integer age, @Param("operator") String operator);

}
