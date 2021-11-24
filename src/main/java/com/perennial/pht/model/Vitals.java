package com.perennial.pht.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "vitals")
public class Vitals {


        public Vitals() {
        }

        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer id;
        @Column
        private Integer patientId;
        @Column
        private Float height;   //In Cm
        @Column
        private Float weight;   // in Kg
        @Column
        private Float bodyTemperature; //in Fahrenheit
        @Column
        @Max(value = 100)
        private Float oxygenLevel;
        @Column
        private Float heartBeatRate;
        @Column
        @ElementCollection
        @CollectionTable(name="vitalsBloodPressure", joinColumns = @JoinColumn(name="id"))
        private List<Float> bloodPressure;



}
