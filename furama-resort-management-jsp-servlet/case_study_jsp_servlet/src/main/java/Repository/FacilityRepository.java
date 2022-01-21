package Repository;

import bean.*;

import java.util.List;

public interface FacilityRepository {
    List<Facility> findAll();

    void save(Facility facility);

    void update(Facility facility);

    void remove(String id);

    List<Facility> findById(String id);

    List<Facility> findByName(String name);

    List<ServiceType> getServiceType();

    List<RentingType> getRentingType();
}
