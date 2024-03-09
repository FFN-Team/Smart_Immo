package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.FileEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    @Query(value = """
            select f from FileEntity f where f.prospect = :prospect
            """)
    List<FileEntity> findAllByOwner(ProspectEntity prospect);

    @Query(value = """
            select f from FileEntity f where f.property = :property
            """)
    List<FileEntity> findAllByOwner(PropertyEntity property);
}
