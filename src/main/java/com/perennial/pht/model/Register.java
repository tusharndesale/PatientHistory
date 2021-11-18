package com.perennial.pht.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Register {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
  //  private Patient patient = new Patient();
    private LocalDate date;

}
