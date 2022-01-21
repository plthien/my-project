package com.codegym.furamaresortmanagement.service.facility;

import com.codegym.furamaresortmanagement.model.facility.ServiceType;

import java.util.List;

public interface IServiceTypeService {
    List<ServiceType> findAll();
}
