package model;

public class EmpDtlsModle {

    private String empID;


    private String NIC;

    private String name;
    private String address;

    private String userID;

    public EmpDtlsModle () {
    }

    public EmpDtlsModle(String empID, String NIC, String name, String address, String userID) {
        this.empID = empID;
        this.NIC = NIC;
        this.name = name;
        this.address = address;
        this.userID = userID;
    }


    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "EmpDtlsModle{" +
                "empID='" + empID + '\'' +
                ", NIC='" + NIC + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
