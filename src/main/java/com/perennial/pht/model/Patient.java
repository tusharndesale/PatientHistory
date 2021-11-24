package com.perennial.pht.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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
    @NotNull
    private String name;
    @Column
    private long mobileNo;
    @Column
    private String gender;
    @Column
    private Date DOB;
    @Column
    private String address;

}
