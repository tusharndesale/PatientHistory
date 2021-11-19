package com.perennial.pht.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name="prescription")
public class Prescription {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private Integer patientId;
    @Column
    private Integer doctorId;

    @ElementCollection
    @CollectionTable(name="prescriptionDrug", joinColumns = @JoinColumn(name="id"))
    private List<Medicine> medicine;

    @Column
    private LocalDate date;

}
