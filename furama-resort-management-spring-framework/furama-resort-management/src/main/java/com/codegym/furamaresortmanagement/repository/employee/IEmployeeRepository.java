package com.codegym.furamaresortmanagement.repository.employee;

import com.codegym.furamaresortmanagement.model.employee.Employee;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value="select * from employees where match(address,email,gender,`name`,personal_id,phone_number) against(:keyword) and `status` = 1 ", nativeQuery=true)
    Page<Employee> findEmployeeByKeyword(Pageable pageable, @Param("keyword") String keyword);

    @Query(value="select * from employees where employee_office_id = :officeId and `status` = 1 ",nativeQuery=true)
    Page<Employee> findAllByEmployeeOffice_Id(Pageable pageable,@Param("officeId") Integer officeId);

    @Query(value="select * from employees where `status` = 1 ",nativeQuery=true)
    Page<Employee> findAll(Pageable pageable);

    @Modifying
    @Query(value="update employees set `status` = 0 where id = :id", nativeQuery= true)
    void removeEmployeeById(@Param("id") int id);

    @Query(value="select * from employees where `status` = 1 ",nativeQuery=true)
    List<Employee> findAll();

    @Query(value="select * from employees where `status` = 1 and email=:email", nativeQuery= true)
    Employee findByEmailEquals(@Param("email") String email);


}
