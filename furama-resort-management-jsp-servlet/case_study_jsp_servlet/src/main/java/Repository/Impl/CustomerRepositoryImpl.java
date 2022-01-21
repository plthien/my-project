package Repository.Impl;

import Repository.CustomerRepository;
import bean.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();

        try {
            CallableStatement callableStatement = BaseRepository.connection.prepareCall("{call get_all_customer()}");
            ResultSet resultSet = callableStatement.executeQuery();

            Customer customer = null;
            CustomerType customerType = null;

            while (resultSet.next()) {
                customer = new Customer();
                customerType = new CustomerType();

                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setBirthday(resultSet.getString("birthday"));
                customer.setGender(resultSet.getString("gender"));
                customer.setPersonalID(resultSet.getString("personal_id"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));

                customerType.setName(resultSet.getString("customer_type_name"));



                customer.setCustomerType(customerType);


                customerList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerList;
    }

    @Override
    public void save(Customer customer) {
        Connection connection = BaseRepository.connection;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("insert into customer(id,`name`,birthday,gender,personal_id,phone_number,email,customer_type_id,address) " +
                    " values(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,customer.getId());
            preparedStatement.setString(2,customer.getName());
            preparedStatement.setString(3,customer.getBirthday());
            preparedStatement.setString(4,customer.getGender());
            preparedStatement.setString(5,customer.getPersonalID());
            preparedStatement.setString(6,customer.getPhoneNumber());
            preparedStatement.setString(7,customer.getEmail());
            preparedStatement.setInt(8,customer.getCustomerType().getId());
            preparedStatement.setString(9,customer.getAddress());

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
    public void update(Customer customer) {
        Connection connection = BaseRepository.connection;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(" update customer set `name`=?,birthday=?," +
                    " gender=?,personal_id=?,phone_number=?,email=?,address=?,customer_type_id=? " +
                    " where id=?");

            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getBirthday());
            preparedStatement.setString(3,customer.getGender());
            preparedStatement.setString(4,customer.getPersonalID());
            preparedStatement.setString(5,customer.getPhoneNumber());
            preparedStatement.setString(6,customer.getEmail());
            preparedStatement.setString(7,customer.getAddress());
            preparedStatement.setInt(8,customer.getCustomerType().getId());
            preparedStatement.setString(9,customer.getId());

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
            CallableStatement callableStatement = BaseRepository.connection.prepareCall("{call delete_customer(?)}");

            callableStatement.setString(1,id);
            callableStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Customer> findById(String id) {
        List<Customer> customerList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("{call get_customer_by_id(?)}");
            preparedStatement.setString(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Customer customer = null;
            CustomerType customerType = null;
            while (resultSet.next()){
                customer = new Customer();
                customerType = new CustomerType();

                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setBirthday(resultSet.getString("birthday"));
                customer.setGender(resultSet.getString("gender"));
                customer.setPersonalID(resultSet.getString("personal_id"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));

                customerType.setId(resultSet.getInt("customer_type_id"));
                customerType.setName(resultSet.getString("customer_type_name"));
                customer.setCustomerType(customerType);
                customerList.add(customer);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customerList = new ArrayList<>();
        try {
            CallableStatement callableStatement = BaseRepository.connection.prepareCall("{call get_customer_by_name(?)}");
            callableStatement.setString(1,name);

            ResultSet resultSet = callableStatement.executeQuery();
            Customer customer = null;
            CustomerType customerType =null;

            while (resultSet.next()){
                customer = new Customer();
                customerType = new CustomerType();

                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setBirthday(resultSet.getString("birthday"));
                customer.setGender(resultSet.getString("gender"));
                customer.setPersonalID(resultSet.getString("personal_id"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));

                customerType.setName(resultSet.getString("customer_type_name"));

                customer.setCustomerType(customerType);

                customerList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<CustomerType> getCustomerType() {
        List<CustomerType> customerTypeList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("Select * from customer_type");
            ResultSet resultSet = preparedStatement.executeQuery();

            CustomerType customerType = null;
            while (resultSet.next()){
                customerType = new CustomerType();

                customerType.setId(resultSet.getInt("id"));
                customerType.setName(resultSet.getString("customer_type_name"));

                customerTypeList.add(customerType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerTypeList;
    }
}
