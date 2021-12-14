package com.perennial.pht.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
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
   // @Column
    //private LocalDate DOB;
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
