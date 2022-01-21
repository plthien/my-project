package bean;

public class Facility {
    private String id;
    private String name;
    private double usableArea;
    private int floors;
    private double cost;
    private int customerMax;
    private ServiceType serviceType;
    private RentingType rentingType;

    public Facility() {
    }

    public Facility(String id, String name, double usable_area, int floors, double cost, int customerMax, ServiceType serviceType, RentingType rentingType) {
        this.id = id;
        this.name = name;
        this.usableArea = usable_area;
        this.floors = floors;
        this.cost = cost;
        this.customerMax = customerMax;
        this.serviceType = serviceType;
        this.rentingType = rentingType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(double usableArea) {
        this.usableArea = usableArea;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCustomerMax() {
        return customerMax;
    }

    public void setCustomerMax(int customerMax) {
        this.customerMax = customerMax;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public RentingType getRentingType() {
        return rentingType;
    }

    public void setRentingType(RentingType rentingType) {
        this.rentingType = rentingType;
    }
}
