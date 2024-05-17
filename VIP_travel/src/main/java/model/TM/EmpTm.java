package model.TM;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmpTm {

    private String NIC;
    private String name;
    private String address;
    private  int tp;
    private  String userID;
    private  String email;
}
