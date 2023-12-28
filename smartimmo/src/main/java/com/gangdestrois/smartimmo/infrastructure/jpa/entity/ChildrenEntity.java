package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Child;
import jakarta.persistence.*;

@Entity
@Table(name = "child")
public class ChildrenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_child")
    private Integer id;
    @Column(name = "age")
    private Integer age;

    public Child toModel() {
        return new Child(this.age);
    }

}
