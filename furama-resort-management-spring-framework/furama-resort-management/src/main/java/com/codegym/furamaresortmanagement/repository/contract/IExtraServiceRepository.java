package com.codegym.furamaresortmanagement.repository.contract;

import com.codegym.furamaresortmanagement.model.contract.ExtraService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IExtraServiceRepository extends JpaRepository<ExtraService,Long> {

    @Query(value="select * from extra_service where `status` = 1 ", nativeQuery=true)
    List<ExtraService> findAll();

}
