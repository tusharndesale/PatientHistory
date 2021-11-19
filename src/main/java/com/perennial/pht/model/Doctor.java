package com.perennial.pht.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="doctor")
public class Doctor {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column
    private String name;
    @Column
    private long mobileNo;
    @Column
    private String gender;
    @Column
    private String address;
    @Column
    private String degree;
    @Column
    private String specialization;




}
