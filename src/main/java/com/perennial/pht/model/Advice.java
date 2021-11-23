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
@Table(name = "advice")
public class Advice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private String adviceType; // tablet, capsule, syrup, injection, tube, Exercise
    @Column
    private String adviceName;
    @ElementCollection
    @CollectionTable(name="adviceTime", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> adviceTime;


}
