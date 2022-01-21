package bean;

public class ContractDetail {
    private String id;
    private Contract contract;
    private ExtraService extraService;
    private  int quantity;
    private double total;

    public ContractDetail() {
    }

    public ContractDetail(String id, Contract contract, ExtraService extraService, int quantity, double total) {
        this.id = id;
        this.contract = contract;
        this.extraService = extraService;
        this.quantity = quantity;
        this.total = total;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ExtraService getExtraService() {
        return extraService;
    }

    public void setExtraService(ExtraService extraService) {
        this.extraService = extraService;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
