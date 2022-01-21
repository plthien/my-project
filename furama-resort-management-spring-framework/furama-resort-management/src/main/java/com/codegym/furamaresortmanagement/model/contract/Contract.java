package com.codegym.furamaresortmanagement.model.contract;

import com.codegym.furamaresortmanagement.model.customer.Customer;
import com.codegym.furamaresortmanagement.model.employee.Employee;
import com.codegym.furamaresortmanagement.model.facility.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Entity(name = "contracts")
@Data @AllArgsConstructor @NoArgsConstructor
public class Contract implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "check_in_date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Check In Date must be in the present or future!")
    private LocalDate checkInDate;
    @Column(name = "check_out_date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Check Out Date must be in the present or future!")
    private LocalDate checkOutDate;
    @Min(0)
    private Double deposit;
    @Min(0)
    private Double total;
    @ManyToOne(targetEntity = Employee.class)
    private Employee employee;
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;
    @ManyToOne(targetEntity = Facility.class)
    private Facility facility;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ContractDetail> contractDetails;
    @Column(name = "status",columnDefinition = "INT default 1")
    private int status;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contract contract = (Contract) target;
        LocalDate checkInDate = contract.getCheckInDate();
        LocalDate checkOutDate = contract.getCheckOutDate();

        if (checkInDate.isAfter(checkOutDate)){
            errors.rejectValue("checkOutDate","checkOutDate.isBeforeCheckInDate");
        }
    }
//    public Double getTotal(){
//        double total = 0.0;
//
//        for ( ContractDetail contractDetail: this.contractDetails) {
//            total += contractDetail.getQuantity() * contractDetail.getExtraService().getPrice();
//        }
//
//        long period;
//        switch (this.facility.getRentingType().getName()){
//            case "Hour":
//                period = Duration.between(this.checkInDate, this.checkOutDate).getSeconds()/60;
//                break;
//            case "Date":
//                period =  Duration.between(this.checkInDate, this.checkOutDate).getSeconds()/(60 * 24);
//                break;
//            case "Month":
//                period =  Duration.between(this.checkInDate, this.checkOutDate).getSeconds()/(60 * 24 * 30);
//                break;
//            default:
//                period =  Duration.between(this.checkInDate, this.checkOutDate).getSeconds()/(60 * 24 * 365);
//                break;
//        }
//
//        total += this.facility.getBasicCost() * period * this.facility.getRentingType().getFactorCost();
//
//        return total;
//    }
//
//    public Double getDeposit(){
//        this.deposit = this.total * 0.1;
//        return deposit;
//    }

}
