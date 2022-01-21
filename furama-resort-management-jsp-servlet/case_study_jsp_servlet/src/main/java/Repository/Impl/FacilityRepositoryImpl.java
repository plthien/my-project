package Repository.Impl;

import Repository.FacilityRepository;
import bean.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacilityRepositoryImpl implements FacilityRepository {
    @Override
    public List<Facility> findAll() {
        List<Facility> facilityList = new ArrayList<>();

        try {
            CallableStatement callableStatement =BaseRepository.connection.prepareCall("{call get_all_facility()}");
            ResultSet resultSet = callableStatement.executeQuery();

            Facility facility = null;
            ServiceType serviceType = null;
            RentingType rentingType = null;

            while (resultSet.next()) {
                facility = new Facility();
                serviceType = new ServiceType();
                rentingType = new RentingType();

                facility.setId(resultSet.getString("id"));
                facility.setName(resultSet.getString("name"));
                facility.setUsableArea(resultSet.getDouble("usable_area"));
                facility.setFloors(resultSet.getInt("number_of_floors"));
                facility.setCost(resultSet.getDouble("cost"));
                facility.setCustomerMax(resultSet.getInt("customer_max"));
                serviceType.setName(resultSet.getString("service_type_name"));
                rentingType.setName(resultSet.getString("renting_by_name"));

                facility.setServiceType(serviceType);
                facility.setRentingType(rentingType);


                facilityList.add(facility);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return facilityList;
    }

    @Override
    public void save(Facility facility) {
        Connection connection = BaseRepository.connection;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("insert into facility(id,`name`,usable_area,number_of_floors,cost,customer_max,renting_by_id,service_type_id) " +
                    " values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, facility.getId());
            preparedStatement.setString(2, facility.getName());
            preparedStatement.setDouble(3, facility.getUsableArea());
            preparedStatement.setInt(4, facility.getFloors());
            preparedStatement.setDouble(5, facility.getCost());
            preparedStatement.setInt(6, facility.getCustomerMax());
            preparedStatement.setInt(7, facility.getRentingType().getId());
            preparedStatement.setInt(8, facility.getServiceType().getId());

            int rowAffect = preparedStatement.executeUpdate();

            if (rowAffect == 1) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.setAutoCommit(true);

        } catch (SQLException throwables) {
            try {
                connection.rollback();
                System.out.println(throwables.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Facility facility) {
        Connection connection = BaseRepository.connection;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(" update facility set `name`=?,usable_area=?,number_of_floors=?,cost=?,customer_max=?,renting_by_id=?,service_type_id=? " +
                    " where id=?");

            preparedStatement.setString(1, facility.getName());
            preparedStatement.setDouble(2, facility.getUsableArea());
            preparedStatement.setInt(3, facility.getFloors());
            preparedStatement.setDouble(4, facility.getCost());
            preparedStatement.setInt(5, facility.getCustomerMax());
            preparedStatement.setInt(6, facility.getRentingType().getId());
            preparedStatement.setInt(7, facility.getServiceType().getId());
            preparedStatement.setString(8, facility.getId());
            int rowAffect = preparedStatement.executeUpdate();

            if (rowAffect == 1) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.setAutoCommit(true);

        } catch (SQLException throwables) {
            try {
                connection.rollback();
                System.out.println(throwables.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(String id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("update facility set `status` = 0 where id=? ");
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Facility> findById(String id) {
        List<Facility> facilityList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("{call get_facility_by_id(?)}");
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Facility facility = null;
            ServiceType serviceType = null;
            RentingType rentingType = null;
            while (resultSet.next()) {
                facility = new Facility();
                serviceType = new ServiceType();
                rentingType = new RentingType();

                facility.setId(resultSet.getString("id"));
                facility.setName(resultSet.getString("name"));
                facility.setUsableArea(resultSet.getDouble("usable_area"));
                facility.setFloors(resultSet.getInt("number_of_floors"));
                facility.setCost(resultSet.getDouble("cost"));
                facility.setCustomerMax(resultSet.getInt("customer_max"));
                serviceType.setId(resultSet.getInt("service_type_id"));
                serviceType.setName(resultSet.getString("service_type_name"));
                rentingType.setId(resultSet.getInt("renting_by_id"));
                rentingType.setName(resultSet.getString("renting_by_name"));

                facility.setServiceType(serviceType);
                facility.setRentingType(rentingType);

                facilityList.add(facility);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return facilityList;
    }

    @Override
    public List<Facility> findByName(String name) {
        List<Facility> facilityList = new ArrayList<>();
        try {
            CallableStatement callableStatement = BaseRepository.connection.prepareCall("{call get_facility_by_name(?)}");
            callableStatement.setString(1, name);

            ResultSet resultSet = callableStatement.executeQuery();

            Facility facility = null;
            ServiceType serviceType = null;
            RentingType rentingType = null;

            while (resultSet.next()) {
                facility = new Facility();
                serviceType = new ServiceType();
                rentingType = new RentingType();

                facility.setId(resultSet.getString("id"));
                facility.setName(resultSet.getString("name"));
                facility.setUsableArea(resultSet.getDouble("usable_area"));
                facility.setFloors(resultSet.getInt("number_of_floors"));
                facility.setCost(resultSet.getDouble("cost"));
                facility.setCustomerMax(resultSet.getInt("customer_max"));
                serviceType.setName(resultSet.getString("service_type_name"));
                rentingType.setName(resultSet.getString("renting_by_name"));

                facility.setServiceType(serviceType);
                facility.setRentingType(rentingType);

                facilityList.add(facility);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return facilityList;
    }

    @Override
    public List<ServiceType> getServiceType() {
        List<ServiceType> serviceTypeList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("Select * from service_type");
            ResultSet resultSet = preparedStatement.executeQuery();

            ServiceType serviceType = null;
            while (resultSet.next()) {
                serviceType = new ServiceType();

                serviceType.setId(resultSet.getInt("id"));
                serviceType.setName(resultSet.getString("service_type_name"));

                serviceTypeList.add(serviceType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serviceTypeList;
    }


    @Override
    public List<RentingType> getRentingType() {
        List<RentingType> rentingTypeList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("Select * from renting_by");
            ResultSet resultSet = preparedStatement.executeQuery();

            RentingType rentingType = null;
            while (resultSet.next()) {
                rentingType = new RentingType();

                rentingType.setId(resultSet.getInt("id"));
                rentingType.setName(resultSet.getString("renting_by_name"));

                rentingTypeList.add(rentingType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rentingTypeList;
    }
}
