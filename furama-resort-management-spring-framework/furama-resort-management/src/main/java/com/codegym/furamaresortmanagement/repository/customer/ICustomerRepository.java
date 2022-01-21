package com.codegym.furamaresortmanagement.repository.customer;

import com.codegym.furamaresortmanagement.model.customer.Customer;
import com.codegym.furamaresortmanagement.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer,String> {

    @Query(value="select * from customers where customer_type_id = :customerTypeId and `status` = 1 ",nativeQuery=true)
    Page<Customer> findAllByCustomerTypeId(Pageable pageable,@Param("customerTypeId") int id );

    @Query(value="select * from customers where match(id,address,email,gender,`name`,personal_id,phone_number) against(:keyword) and `status` = 1 ", nativeQuery=true)
    Page<Customer> findAllByKeyword(Pageable pageable,@Param("keyword") String keyword );

    @Query(value="select * from customers where `status` = 1 ",nativeQuery=true)
    Page<Customer> findAll(Pageable pageable);


    @Query(value="select * from customers where `status` = 1 and id =:id",nativeQuery=true)
    Optional<Customer> findById(@Param("id") String id);

    @Modifying
    @Query(value="update customers set `status` = 0 where id =:id", nativeQuery=true)
    void removeCustomerById(@Param("id") String id);

    @Query(value="select * from customers where `status` = 1",nativeQuery=true)
    List<Customer> findAll();

}
