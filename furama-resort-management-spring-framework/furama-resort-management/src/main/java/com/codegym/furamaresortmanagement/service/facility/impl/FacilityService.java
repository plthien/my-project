package com.codegym.furamaresortmanagement.service.facility.impl;

import com.codegym.furamaresortmanagement.model.facility.Facility;
import com.codegym.furamaresortmanagement.repository.facility.IFacilityRepository;
import com.codegym.furamaresortmanagement.service.facility.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FacilityService implements IFacilityService {
    @Autowired
    private IFacilityRepository iFacilityRepository;

    @Override
    public Page<Facility> findFacilityByServiceType(int page, int size, String sortField, String sortDirection, Integer serviceTypeId) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return iFacilityRepository.findAllByServiceTypeId(pageable,serviceTypeId);
    }

    @Override
    public Page<Facility> findAllFacility(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return iFacilityRepository.findAll(pageable);
    }

    @Override
    public Page<Facility> findFacilityByKeyword(int page, int size, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return iFacilityRepository.findAllByKeyword(pageable,keyword);
    }

    @Override
    public void saveFacility(Facility facility) {
        iFacilityRepository.save(facility);
    }

    @Override
    public void deleteFacility(String id) {
        iFacilityRepository.deleteFacility(id);
    }

    @Override
    public Facility findById(String id) {
        return iFacilityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Facility> findAll() {
        return iFacilityRepository.findAll();
    }
}
