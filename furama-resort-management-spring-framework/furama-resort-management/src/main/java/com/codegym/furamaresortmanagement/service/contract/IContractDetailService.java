package com.codegym.furamaresortmanagement.service.contract;

import com.codegym.furamaresortmanagement.model.contract.ContractDetail;

import java.util.List;

public interface IContractDetailService {
    List<ContractDetail> findAll();

    void saveContractDetail(ContractDetail contractDetail);

    ContractDetail findOne(Long id);
}
