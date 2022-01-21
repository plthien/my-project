package service.Impl;

import Repository.FacilityRepository;
import Repository.Impl.FacilityRepositoryImpl;
import bean.Facility;
import bean.RentingType;
import bean.ServiceType;
import service.FacilityService;

import java.util.List;

public class FacilityServiceImpl implements FacilityService {
    private FacilityRepository facilityRepository = new FacilityRepositoryImpl();
    @Override
    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public void save(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public void update(Facility facility) {
        facilityRepository.update(facility);
    }

    @Override
    public void remove(String id) {
        facilityRepository.remove(id);
    }

    @Override
    public List<Facility> findById(String id) {
        return facilityRepository.findById(id);
    }

    @Override
    public List<Facility> findByName(String name) {
        return facilityRepository.findByName(name);
    }

    @Override
    public List<ServiceType> getServiceType() {
        return facilityRepository.getServiceType();
    }

    @Override
    public List<RentingType> getRentingType() {
        return facilityRepository.getRentingType();
    }
}
