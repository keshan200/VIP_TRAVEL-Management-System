package model.TM;

public class CustomerTM {

    private String customerID;
    private String NIC;
    private String name;
    private  int telNO;
    private  String address;

    public CustomerTM() {}

    public CustomerTM(String customerID, String NIC, String name, int telNO, String address) {
        this.customerID = customerID;
        this.NIC = NIC;
        this.name = name;
        this.telNO = telNO;
        this.address = address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTelNO() {
        return telNO;
    }

    public void setTelNO(int telNO) {
        this.telNO = telNO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerID='" + customerID + '\'' +
                ", NIC='" + NIC + '\'' +
                ", name='" + name + '\'' +
                ", telNO=" + telNO +
                ", address='" + address + '\'' +
                '}';
    }
}
