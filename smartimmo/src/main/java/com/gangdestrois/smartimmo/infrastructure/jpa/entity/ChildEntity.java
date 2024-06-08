package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Child;
import jakarta.persistence.*;

@Entity
@Table(name = "child")
public class ChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_child")
    private Long id;
    @Column(name = "age")
    private Integer age;
    @JoinColumn(name = "fk_home", referencedColumnName = "id_home")
    @ManyToOne(targetEntity = HomeEntity.class)
    private HomeEntity home;

    public ChildEntity(Integer age) {
        this.age = age;
    }

    public ChildEntity() {
    }

    public Child toModel() {
        return new Child(this.age);
    }

    public static ChildEntity fromModelToEntity(Child child) {
        return new ChildEntity(child.age());
    }
}
