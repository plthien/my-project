package com.codegym.furamaresortmanagement.model.facility;

import com.codegym.furamaresortmanagement.custom_id.CustomIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity(name = "facilities")
@Data @AllArgsConstructor @NoArgsConstructor
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facility_seq")
    @GenericGenerator(
            name = "facility_seq",
            strategy = "com.codegym.furamaresortmanagement.custom_id.CustomIdGenerator",
            parameters = {
                    @Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "DV-"),
                    @Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    @NotBlank
    private String name;
    @Min(0)
    private double usableArea;
    @Min(0)
    private int floors;
    @Min(0)
    private double basicCost;
    @Min(0)
    private int customerMax;
    @NotBlank
    private String description;
    @ManyToOne(targetEntity = ServiceType.class)
    private ServiceType serviceType;
    @ManyToOne(targetEntity = RentingType.class)
    private RentingType rentingType;
    @Column(name = "status", columnDefinition = "INT DEFAULT 1")
    private int status;
}
