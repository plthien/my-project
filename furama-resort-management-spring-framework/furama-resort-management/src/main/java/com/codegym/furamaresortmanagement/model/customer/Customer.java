package com.codegym.furamaresortmanagement.model.customer;

import com.codegym.furamaresortmanagement.custom_id.CustomIdGenerator;
import com.codegym.furamaresortmanagement.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "customers")
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer extends Person implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @GenericGenerator(
            name = "customer_seq",
            strategy = "com.codegym.furamaresortmanagement.custom_id.CustomIdGenerator",
            parameters = {
                    @Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "KH-"),
                    @Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    @ManyToOne(targetEntity = CustomerType.class)
    private CustomerType customerType;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        LocalDate date_of_birth = customer.getBirthday();
        LocalDate date_now = LocalDate.now();
        if (Period.between(date_of_birth,date_now).getYears() < 18) {
            errors.rejectValue("birthday","birthday.lessthan18");
        }
    }
}
