package service.Impl;

import Repository.ContractRepository;
import Repository.Impl.ContractRepositoryImpl;
import bean.*;
import service.ContractService;

import java.util.List;

public class ContractServiceImpl implements ContractService {
    private ContractRepository contractRepository = new ContractRepositoryImpl();
    @Override
    public List<Contract> findAllContract() {
        return contractRepository.findAllContract();
    }

    @Override
    public List<ContractDetail> findAllContractDetail() {
        return contractRepository.findAllContractDetail();
    }

    @Override
    public void saveContract(Contract contract) {
        contractRepository.saveContract(contract);
    }

    @Override
    public void saveContractDetail(ContractDetail contractDetail) {
        contractRepository.saveContractDetail(contractDetail);
    }

    @Override
    public void updateContract(Contract contract) {
        contractRepository.updateContract(contract);
    }

    @Override
    public void updateContractDetail(ContractDetail contractDetail) {
        contractRepository.updateContractDetail(contractDetail);
    }

    @Override
    public void remove(String id) {
        contractRepository.remove(id);
    }

    @Override
    public void removeContractDetail(String id) {
        contractRepository.removeContractDetail(id);
    }

    @Override
    public List<Contract> findById(String id) {
        return contractRepository.findById(id);
    }

    @Override
    public List<ContractDetail> findContractDetailById(String id) {
        return contractRepository.findContractDetailById(id);
    }

    @Override
    public List<ExtraService> getExtraService() {
        return contractRepository.getExtraService();
    }


}
