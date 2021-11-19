package com.perennial.pht.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column
    private String name;
    @Column
    private long mobileNo;
    @Column
    private GenderType gender;
    @Column
    private int age;
    @Column
    private String address;
    @ElementCollection
    @CollectionTable(name="patientSymptoms", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> symptoms;
    @ElementCollection
    @CollectionTable(name="patientDiseases", joinColumns = @JoinColumn(name="id"))
    @Column
    private List<String> diseases;
    /*@ElementCollection
    @CollectionTable(name="prescriptionDrug", joinColumns = @JoinColumn(name="id"))
    private List<Vitals> vitals;*/

    //private Doctor doctor;

    public enum GenderType {

        TYPE1("MALE"),
        TYPE2("FEMALE"),
        TYPE3("OTHERS");

        private String code;

        private GenderType(String code) {
            this.code=code;
        }

        @JsonCreator
        public static GenderType decode(final String code) {
            return Stream.of(GenderType.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
        }

        @JsonValue
        public String getCode() {
            return code;
        }
    }


}
