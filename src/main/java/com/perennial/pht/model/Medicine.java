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
@Table(name = "medicine")
public class Medicine {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private String drugType; // tablet, capsule, syrup, injection, tube
    @Column
    private String drugName;
    @ElementCollection
    @CollectionTable(name="medicineTime", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> drugTime;


}
