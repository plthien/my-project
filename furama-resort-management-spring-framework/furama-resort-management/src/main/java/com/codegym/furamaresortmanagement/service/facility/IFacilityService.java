package com.codegym.furamaresortmanagement.service.facility;

import com.codegym.furamaresortmanagement.model.facility.Facility;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFacilityService {
    Page<Facility> findFacilityByServiceType(int page, int size, String sortField, String sortDirection, Integer serviceTypeId);

    Page<Facility> findAllFacility(int page, int size, String sortField, String sortDirection);

    Page<Facility> findFacilityByKeyword(int page, int size, String sortField, String sortDirection, String keyword);

    void saveFacility(Facility facility);

    void deleteFacility(String id);

    Facility findById(String id);

    List<Facility> findAll();
}
