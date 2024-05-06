package model.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnTM {

    private  String ReturnID;
    private String states;
    private LocalDate date;
    private String NIC;
    private String regNO;
    private String damge;
    private String desc;




}
