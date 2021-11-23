package com.perennial.pht.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "visits")
public class Visits {

    public Visits() {
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private LocalDate date;
    private Integer patientId;
    private Integer doctorId;

    @ElementCollection
    @CollectionTable(name="visitSymptoms", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> symptoms;
    @ElementCollection
    @CollectionTable(name="visitDiagnosis", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> diagnosis;
    @ElementCollection
    @CollectionTable(name="prescriptionDrug", joinColumns = @JoinColumn(name="id"))
    private List<Advice> medicine;
}
