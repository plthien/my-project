package com.codegym.furamaresortmanagement.repository.contract;

import com.codegym.furamaresortmanagement.model.contract.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IContractDetailRepository extends JpaRepository<ContractDetail, Long> {
}
