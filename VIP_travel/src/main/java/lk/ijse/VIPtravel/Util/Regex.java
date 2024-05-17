package lk.ijse.VIPtravel.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {



    /* String nic = "adfsfs"; // Example NIC number
        if (nic.matches("^\\d{9}[VvXx]?$")) {
            System.out.println("NIC is valid");
        } else {
            new Alert(Alert.AlertType.ERROR, "invailid").show();
        }


        String regNumber = "58854"; // Example vehicle registration number
        if (regNumber.matches("^[A-Za-z]{2,3}-\\d{3,4}$")) {
            System.out.println("Vehicle registration number is valid");
        } else {
            System.out.println("Vehicle registration number is invalid");
        }

        String year = "2024"; // Example year
        if (year.matches("^(19|20)\\d{2}$")) {
            System.out.println("Year is valid");
        } else {
            System.out.println("Year is invalid");
        }


        String price = "99.99"; // Example price
        if (price.matches("^\\d+(\\.\\d{1,2})?$")) {
            System.out.println("Price is valid");
        } else {
            System.out.println("Price is invalid");
        }


      //  ^(?!-)\d+(\.\d{1,2})?$   cant add negative number
*/


    public static boolean isTextFieldValid(TextField textField, String text){
        String field = "";

        switch (textField) {

            case NAME:
                field = "^[A-Za-z\\s]{4,}$";
                break;
            case TELNO:
                field = "^(\\+94|0)?[1-9][0-9]{8}$";
                break;
            case EMAIL:
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case NIC:
                field = "^\\\\d{9}[VvXx]?$";
                break;

        }


        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }



    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-focus-color: green; -fx-unfocus-color: green;");
            // textField.setStyle("-fx-control-inner-background: green; -fx-focus-color: green; -fx-unfocus-color: green;");

            return true;
        } else {
            textField.setStyle("-fx-focus-color: red; -fx-unfocus-color: red;");
            //textField.setStyle("-fx-control-inner-background: red; -fx-focus-color: red; -fx-unfocus-color: red;");
            return false;
        }
    }


}
