package com.codegym.furamaresortmanagement.service.facility.impl;

import com.codegym.furamaresortmanagement.model.facility.RentingType;
import com.codegym.furamaresortmanagement.repository.facility.IRentingTypeRepository;
import com.codegym.furamaresortmanagement.service.facility.IRentingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentingTypeService implements IRentingTypeService {
    @Autowired
    private IRentingTypeRepository iRentingTypeRepository;

    @Override
    public List<RentingType> findAll() {
        return iRentingTypeRepository.findAll();
    }
}
