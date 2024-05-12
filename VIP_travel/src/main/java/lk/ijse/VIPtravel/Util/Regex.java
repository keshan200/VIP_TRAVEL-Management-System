package lk.ijse.VIPtravel.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

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



    /*public static void addMaterial(String materialName, int qty, String supplierName, Date date, double unitPrice, int rawMaterialId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {

            String insertRawMaterialSql = "INSERT INTO RawMaterial (RawMaterialID, Name, Quantity) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertRawMaterialSql);
            try {
                preparedStatement.setInt(1, rawMaterialId);
                preparedStatement.setString(2, materialName);
                preparedStatement.setInt(3, qty);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            String insertSupplierDetailSql = "INSERT INTO SupplierDetail (RawMaterialID, SupplierID, Date, Price) VALUES (?, (SELECT SupplierID FROM Supplier WHERE Name = ?), ?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertSupplierDetailSql);
            try  {
                preparedStatement1.setInt(1, rawMaterialId);
                preparedStatement1.setString(2, supplierName);
                preparedStatement1.setDate(3, date);
                preparedStatement1.setDouble(4, unitPrice);
                preparedStatement1.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);

        }*/

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
