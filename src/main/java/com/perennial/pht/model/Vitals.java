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
@Table(name = "vitals")
public class Vitals {
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
         //  height, weight, Blood pressure, body temperature, Oxygen level
        @Column
        private Integer patientId;
        @Column
        private Float height;   //In Cm
        @Column
        private Float weight;   // in Kg
        @Column
        private Float bloodTemperature; //in Farenide
        @Column
        private Float oxygenLevel;
        @Column
        @ElementCollection
        @CollectionTable(name="vitalsBloodPressure", joinColumns = @JoinColumn(name="id"))
        private List<Float> bloodPressure;
}
