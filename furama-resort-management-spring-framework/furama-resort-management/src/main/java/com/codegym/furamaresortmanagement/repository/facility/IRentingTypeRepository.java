package com.codegym.furamaresortmanagement.repository.facility;

import com.codegym.furamaresortmanagement.model.facility.RentingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentingTypeRepository extends JpaRepository<RentingType, Integer> {
}
