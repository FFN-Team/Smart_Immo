package com.gangdestrois.smartimmo.repository;

import com.gangdestrois.smartimmo.model.entity.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BienRepository extends JpaRepository<Bien,Long> {
    @Override
    List<Bien> findAll();
}
