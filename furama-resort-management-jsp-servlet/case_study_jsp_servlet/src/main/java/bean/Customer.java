package bean;

public class Customer extends Person {
    private String id;
    private CustomerType customerType;

    public Customer() {
    }

    public Customer(String name, String birthday, String gender, String personalID, String phoneNumber, String email, String address, String id, CustomerType customerType) {
        super(name, birthday, gender, personalID, phoneNumber, email, address);
        this.id = id;
        this.customerType = customerType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
