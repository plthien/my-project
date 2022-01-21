package com.codegym.furamaresortmanagement.model.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity(name = "contract_detail")
@Data @AllArgsConstructor @NoArgsConstructor
public class ContractDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(0)
    private Long quantity;
    @ManyToOne(targetEntity = Contract.class)
    private Contract contract;
    @ManyToOne(targetEntity = ExtraService.class)
    private ExtraService extraService;
}
