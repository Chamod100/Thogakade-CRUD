package Controller.Customer;

import Model.DTO.CustomerInfoDTO;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerController implements CustomerService{

    @Override
    public void addCustomerDetails(String CustID, String Title, String Name, String Dob, double Salary, String Address, String City, String Province, String PostalCode){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1,CustID);
            preparedStatement.setObject(2,Title);
            preparedStatement.setObject(3,Name);
            preparedStatement.setObject(4,Dob);
            preparedStatement.setObject(5,Salary);
            preparedStatement.setObject(6,Address);
            preparedStatement.setObject(7,City);
            preparedStatement.setObject(8,Province);
            preparedStatement.setObject(9,PostalCode);

            preparedStatement.execute();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCustomerDetails(String CustID){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM Customer WHERE CustomerID=?");

            preparedStatement.setObject(1,CustID);

            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomerDetails(String CustID, String Title, String Name, String Dob, double Salary, String Address, String City, String Province, String PostalCode){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE Customer SET Title=?, Name=?, Dob=?, Salary=?, Address=?, City=?, Province=?, PostalCode=? WHERE CustomerID=?");

            preparedStatement.setObject(1,Title);
            preparedStatement.setObject(2,Name);
            preparedStatement.setObject(3,Dob);
            preparedStatement.setObject(4,Salary);
            preparedStatement.setObject(5,Address);
            preparedStatement.setObject(6,City);
            preparedStatement.setObject(7,Province);
            preparedStatement.setObject(8,PostalCode);
            preparedStatement.setObject(9,CustID);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<CustomerInfoDTO> loadCustomerDetails(){

        ObservableList<CustomerInfoDTO> customerInfoArray= FXCollections.observableArrayList( );


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerInfoDTO customerDTO = new CustomerInfoDTO(
                        resultSet.getString("CustomerID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Name"),
                        resultSet.getString("DateOfBirth"), // You can also use resultSet.getDate("dob") if DTO uses java.sql.Date
                        resultSet.getDouble("Salary"),
                        resultSet.getString("Address"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerInfoArray.add(customerDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerInfoArray;
    }
}
