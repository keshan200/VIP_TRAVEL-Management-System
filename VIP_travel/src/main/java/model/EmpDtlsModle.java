package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class EmpDtlsModle {

    private String NIC;
    private String name;
    private String address;
    private  int tp;
    private  String userID;
    private String password;
    private  String email;


}
