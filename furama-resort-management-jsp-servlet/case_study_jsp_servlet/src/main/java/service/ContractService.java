package service;

import bean.*;

import java.util.List;

public interface ContractService {
    List<Contract> findAllContract();

    List<ContractDetail> findAllContractDetail();

    void saveContract(Contract contract);

    void saveContractDetail(ContractDetail contractDetail);

    void updateContract(Contract contract);

    void updateContractDetail(ContractDetail contractDetail);

    void remove(String id);

    void removeContractDetail(String id);

    List<Contract> findById(String id);

    List<ContractDetail> findContractDetailById(String id);

    List<ExtraService> getExtraService();

}
