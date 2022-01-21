package com.codegym.furamaresortmanagement.service.contract.impl;

import com.codegym.furamaresortmanagement.model.contract.ContractDetail;
import com.codegym.furamaresortmanagement.repository.contract.IContractDetailRepository;
import com.codegym.furamaresortmanagement.service.contract.IContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContractDetailService implements IContractDetailService {
    @Autowired
    private IContractDetailRepository iContractDetailRepository;

    @Override
    public List<ContractDetail> findAll() {
        return iContractDetailRepository.findAll();
    }

    @Override
    public void saveContractDetail(ContractDetail contractDetail) {
        iContractDetailRepository.save(contractDetail);
    }

    @Override
    public ContractDetail findOne(Long id) {
        return iContractDetailRepository.findById(id).orElse(null);
    }
}
