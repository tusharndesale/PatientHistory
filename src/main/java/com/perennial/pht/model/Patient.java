package com.perennial.pht.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private String name;
    @Column
    private long mobileNo;
    @Column
    private String gender;
    @Column
    private LocalDate DOB;
    @Column
    private String address;

    /*@ElementCollection
    @CollectionTable(name="prescriptionDrug", joinColumns = @JoinColumn(name="id"))
    private List<Vitals> vitals;*/

    //private Doctor doctor;




}
