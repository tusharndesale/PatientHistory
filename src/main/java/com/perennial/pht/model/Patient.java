package com.perennial.pht.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private long mobileNo;
    @Column
    private String gender;
    @Column
    private int age;
    @Column
    private String address;
    @ElementCollection
    @CollectionTable(name="patientSymptoms", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> symptoms;
    @ElementCollection
    @CollectionTable(name="patientDiseases", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> diseases;
    /*@ElementCollection
    @CollectionTable(name="prescriptionDrug", joinColumns = @JoinColumn(name="id"))
    private List<Vitals> vitals;*/

    //private Doctor doctor;




}
