package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class CustomerModle {
    private String customerID;
    private String NIC;
    private String name;
    private  int telNO;
    private  String address;


    public CustomerModle(String NIC, String name, int telNO, String address) {
        this.NIC = NIC;
        this.name = name;
        this.telNO = telNO;
        this.address = address;
    }

    public CustomerModle(String NIC, String name) {
        this.NIC = NIC;
        this.name = name;
    }


}
