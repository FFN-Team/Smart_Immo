package com.gangdestrois.smartimmo.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name="bien")
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bien_id")
    private Integer id;

    @Column(name="nom_bien")
    private String nomBien;

    private String description;

}
