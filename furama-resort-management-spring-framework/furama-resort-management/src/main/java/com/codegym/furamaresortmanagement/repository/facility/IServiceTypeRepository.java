package com.codegym.furamaresortmanagement.repository.facility;

import com.codegym.furamaresortmanagement.model.facility.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
}
