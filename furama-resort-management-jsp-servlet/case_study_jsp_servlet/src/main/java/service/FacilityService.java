package service;

import bean.Facility;
import bean.RentingType;
import bean.ServiceType;

import java.util.List;

public interface FacilityService {
    List<Facility> findAll();

    void save(Facility facility);

    void update(Facility facility);

    void remove(String id);

    List<Facility> findById(String id);

    List<Facility> findByName(String name);

    List<ServiceType> getServiceType();

    List<RentingType> getRentingType();
}
