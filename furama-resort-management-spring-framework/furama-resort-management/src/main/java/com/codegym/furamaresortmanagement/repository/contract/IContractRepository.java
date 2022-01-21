package com.codegym.furamaresortmanagement.repository.contract;

import com.codegym.furamaresortmanagement.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
@Transactional
public interface IContractRepository extends JpaRepository<Contract, Long> {

    Page<Contract> findAllByCheckInDateOrCheckOutDateEqualsAndStatusIs(LocalDate checkInDate, LocalDate checkOutDate,Integer status ,Pageable pageable);

    Page<Contract> findAllByCustomer_NameContainingOrCustomer_IdContainingAndStatusIs(String customer_name, String customer_id, Integer status , Pageable pageable);

    @Query(value="select * from contracts where `status` = 1", nativeQuery=true)
    Page<Contract> findAll(Pageable pageable);

    @Modifying
    @Query(value="update contracts set `status` = 0 where id = :id", nativeQuery= true)
    void removeContractByID(@Param("id") Long id);
}
