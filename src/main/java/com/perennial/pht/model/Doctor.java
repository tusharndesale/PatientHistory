package com.perennial.pht.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Table(name="doctor")
public class Doctor {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private String name;
    @Column
    private LocalDate DOB;
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
    @ElementCollection
    @CollectionTable(name="doctorsVitals", joinColumns = @JoinColumn(name="id"))
    private List<String> vitals;




}
