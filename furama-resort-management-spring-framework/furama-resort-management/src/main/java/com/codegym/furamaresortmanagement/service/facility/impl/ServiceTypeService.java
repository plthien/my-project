package com.codegym.furamaresortmanagement.service.facility.impl;

import com.codegym.furamaresortmanagement.model.facility.ServiceType;
import com.codegym.furamaresortmanagement.repository.facility.IServiceTypeRepository;
import com.codegym.furamaresortmanagement.service.facility.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService implements IServiceTypeService {
    @Autowired
    private IServiceTypeRepository iServiceTypeRepository;

    @Override
    public List<ServiceType> findAll() {
        return iServiceTypeRepository.findAll();
    }
}
