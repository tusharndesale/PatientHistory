package com.perennial.pht.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Register {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private LocalDate date;

}
