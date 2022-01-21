package com.codegym.furamaresortmanagement.repository.facility;

import ch.qos.logback.core.boolex.EvaluationException;
import com.codegym.furamaresortmanagement.model.facility.Facility;
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
public interface IFacilityRepository extends JpaRepository<Facility, String> {

    @Query(value="select * from facilities where service_type_id = :id and `status` = 1 ", nativeQuery=true)
    Page<Facility> findAllByServiceTypeId(Pageable pageable, @Param("id") Integer id);

    @Query(value="select * from facilities where `status` = 1",nativeQuery = true)
    Page<Facility> findAll(Pageable pageable);

    @Query(value="select * from facilities where match(id,description,`name`) against(:keyword) and `status` = 1 ", nativeQuery=true)
    Page<Facility> findAllByKeyword(Pageable pageable,@Param("keyword")String keyword);

    @Modifying
    @Query(value="update facilities set `status` = 0 where id=:id" , nativeQuery=true)
    void deleteFacility(@Param("id")String id);

    @Query(value="select * from facilities where id=:id and `status` = 1", nativeQuery=true)
    Optional<Facility> findById(@Param("id") String id);

    @Query(value="select * from facilities where `status` = 1", nativeQuery=true)
    List<Facility> findAll();

}
