package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.SalesHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesHistoryRepository extends JpaRepository<SalesHistoryEntity, Long> {
    @Query(value = """
        SELECT a.street_number || ' ' || s.street_name,
            CAST(ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS numeric), 2) AS DOUBLE PRECISION),
            CAST(0 AS DOUBLE PRECISION)
        FROM sales_history sh INNER JOIN property_owner po ON sh.fk_property_owner = po.id_property_owner
            INNER JOIN property p ON po.fk_property = p.id_property
            INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN street s ON a.fk_street = s.id_street
        WHERE sh.date_of_sale BETWEEN :start_date AND :end_date AND EXISTS (
            SELECT *
            FROM property p2 INNER JOIN address a2 ON p2.fk_address = a2.id_address
            WHERE p2.id_property = :property_id AND a.street_number = a2.street_number
                AND a.fk_street = a2.fk_street AND a.fk_area = a2.fk_area AND a.fk_city = a2.fk_city
        )
        GROUP BY a.street_number || ' ' || s.street_name
        UNION ALL
        SELECT a.street_number || ' ' || s.street_name, NULL, NULL
        FROM property p INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN street s ON a.fk_street = s.id_street
        WHERE p.id_property = :property_id
        LIMIT 1;
        """, nativeQuery = true
    )
    List<Object[]> findAveragePricePerSquareMeterOfPropertySBuilding(
            @Param("property_id") Long propertyId,
            @Param("start_date") LocalDate startDate,
            @Param("end_date") LocalDate endDate
    );

    @Query(value = """
        WITH building (averagePrice) AS (
            SELECT CAST(ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS numeric), 2) AS DOUBLE PRECISION)
            FROM sales_history sh INNER JOIN property_owner po ON sh.fk_property_owner = po.id_property_owner
                INNER JOIN property p ON po.fk_property = p.id_property
                INNER JOIN address a ON p.fk_address = a.id_address
            WHERE sh.date_of_sale BETWEEN :start_date AND :end_date AND EXISTS (
                SELECT *
                FROM property p2 INNER JOIN address a2 ON p2.fk_address = a2.id_address
                WHERE p2.id_property = :property_id AND a.street_number = a2.street_number
                    AND a.fk_street = a2.fk_street AND a.fk_area = a2.fk_area AND a.fk_city = a2.fk_city
            )
        )
            
        SELECT s.street_name, CAST(ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS numeric), 2) AS DOUBLE PRECISION),
            CAST(ROUND(CAST(
                (ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS NUMERIC), 2) - averagePrice) * 100 / (averagePrice)
             AS NUMERIC), 2) AS DOUBLE PRECISION)
        FROM sales_history sh INNER JOIN property_owner po ON sh.fk_property_owner = po.id_property_owner
            INNER JOIN property p ON po.fk_property = p.id_property
            INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN street s ON a.fk_street = s.id_street,
            building
        WHERE sh.date_of_sale BETWEEN :start_date AND :end_date AND EXISTS (
            SELECT *
            FROM property p2 INNER JOIN address a2 ON p2.fk_address = a2.id_address
            WHERE p2.id_property = :property_id AND a.fk_street = a2.fk_street AND a.fk_area = a2.fk_area
                AND a.fk_city = a2.fk_city
        )
        GROUP BY s.street_name, averagePrice
        UNION ALL
        SELECT s.street_name, NULL, NULL
        FROM property p INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN street s ON a.fk_street = s.id_street
        WHERE p.id_property = :property_id
        LIMIT 1;
        """, nativeQuery = true
    )
    List<Object[]> findAveragePricePerSquareMeterOfPropertySStreet(
            @Param("property_id") Long propertyId,
            @Param("start_date") LocalDate startDate,
            @Param("end_date") LocalDate endDate
    );

    @Query(value = """
        WITH building (averagePrice) AS (
            SELECT CAST(ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS numeric), 2) AS DOUBLE PRECISION)
            FROM sales_history sh INNER JOIN property_owner po ON sh.fk_property_owner = po.id_property_owner
                INNER JOIN property p ON po.fk_property = p.id_property
                INNER JOIN address a ON p.fk_address = a.id_address
        WHERE sh.date_of_sale BETWEEN :start_date AND :end_date AND EXISTS (
                SELECT *
                FROM property p2 INNER JOIN address a2 ON p2.fk_address = a2.id_address
                WHERE p2.id_property = :property_id AND a.street_number = a2.street_number
                    AND a.fk_street = a2.fk_street AND a.fk_area = a2.fk_area AND a.fk_city = a2.fk_city
            )
        )
        
        SELECT ar.area_name, CAST(ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS numeric), 2) AS DOUBLE PRECISION),
            CAST(ROUND(CAST(
                (ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS NUMERIC), 2) - averagePrice) * 100 / (averagePrice)
             AS NUMERIC), 2) AS DOUBLE PRECISION)
        FROM sales_history sh INNER JOIN property_owner po ON sh.fk_property_owner = po.id_property_owner
            INNER JOIN property p ON po.fk_property = p.id_property
            INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN area ar ON a.fk_area = ar.id_area,
            building
        WHERE sh.date_of_sale BETWEEN :start_date AND :end_date AND EXISTS (
            SELECT *
            FROM property p2 INNER JOIN address a2 ON p2.fk_address = a2.id_address
            WHERE p2.id_property = :property_id AND a.fk_area = a2.fk_area AND a.fk_city = a2.fk_city
        )
        GROUP BY ar.area_name, averagePrice
        UNION ALL
        SELECT ar.area_name, NULL, NULL
        FROM property p INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN area ar ON a.fk_area = ar.id_area
        WHERE p.id_property = :property_id
        LIMIT 1;
        """, nativeQuery = true
    )
    List<Object[]> findAveragePricePerSquareMeterOfPropertySArea(
            @Param("property_id") Long propertyId,
            @Param("start_date") LocalDate startDate,
            @Param("end_date") LocalDate endDate
    );

    @Query(value = """
        WITH building (averagePrice) AS (
            SELECT CAST(ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS numeric), 2) AS DOUBLE PRECISION)
            FROM sales_history sh INNER JOIN property_owner po ON sh.fk_property_owner = po.id_property_owner
                INNER JOIN property p ON po.fk_property = p.id_property
                INNER JOIN address a ON p.fk_address = a.id_address
        WHERE sh.date_of_sale BETWEEN :start_date AND :end_date AND EXISTS (
                SELECT *
                FROM property p2 INNER JOIN address a2 ON p2.fk_address = a2.id_address
                WHERE p2.id_property = :property_id AND a.street_number = a2.street_number
                    AND a.fk_street = a2.fk_street AND a.fk_area = a2.fk_area AND a.fk_city = a2.fk_city
            )
        )
        
        SELECT c.city_name, CAST(ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS numeric), 2) AS DOUBLE PRECISION),
            CAST(ROUND(CAST(
                (ROUND(CAST(SUM(sh.price)/SUM(p.livable_area) AS NUMERIC), 2) - averagePrice) * 100 / (averagePrice)
             AS NUMERIC), 2) AS DOUBLE PRECISION)
        FROM sales_history sh INNER JOIN property_owner po ON sh.fk_property_owner = po.id_property_owner
            INNER JOIN property p ON po.fk_property = p.id_property
            INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN city c ON a.fk_city = c.id_city,
            building
        WHERE sh.date_of_sale BETWEEN :start_date AND :end_date AND EXISTS (
            SELECT *
            FROM property p2 INNER JOIN address a2 ON p2.fk_address = a2.id_address
            WHERE p2.id_property = :property_id AND a.fk_city = a2.fk_city
        )
        GROUP BY c.city_name, averagePrice
        UNION ALL
        SELECT c.city_name, NULL, NULL
        FROM property p INNER JOIN address a ON p.fk_address = a.id_address
            INNER JOIN city c ON a.fk_city = c.id_city
        WHERE p.id_property = :property_id
        LIMIT 1;
        """, nativeQuery = true
    )
    List<Object[]> findAveragePricePerSquareMeterOfPropertySCity(
            @Param("property_id") Long propertyId,
            @Param("start_date") LocalDate startDate,
            @Param("end_date") LocalDate endDate
    );
}
