package com.codegym.furamaresortmanagement.service.contract.impl;

import com.codegym.furamaresortmanagement.model.contract.ExtraService;
import com.codegym.furamaresortmanagement.repository.contract.IExtraServiceRepository;
import com.codegym.furamaresortmanagement.service.contract.IExtraServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExtraServiceService implements IExtraServiceService {
    @Autowired
    private IExtraServiceRepository iExtraServiceRepository;

    @Override
    public List<ExtraService> findAll() {
        return iExtraServiceRepository.findAll();
    }
}
