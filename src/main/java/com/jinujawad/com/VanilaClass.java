package com.jinujawad.com;

import java.sql.*;

public class VanilaClass {

    public static void dataBaseQuery(){
        String url = "jdbc:postgresql://localhost:5432/Umuzi";
        String userName = "postgres";
        String password = "Km1975";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, userName, password);//Connecting to the database

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from customers");//selecting all the data from the customer table

            String data = "";
            while (resultSet.next()) { //Printing customer data from customer table
                data = resultSet.getInt(1)+"\t"+ resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+
                        resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getString(7)+"\t"+resultSet.getString(8)
                        +"\t"+resultSet.getString(7);
                System.out.println(data);
            }

            System.out.println("");
            resultSet = statement.executeQuery("select FirstName, LastName from customers");
            while (resultSet.next()){
                data = resultSet.getString(1)+"\t"+resultSet.getString(2);
                System.out.println(data);
            }

            System.out.println("");
            resultSet = statement.executeQuery("select FirstName, LastName\n" +
                    "from Customers\n" +
                    "where CustomerID = 1");
            resultSet.next();
            System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2));

            System.out.println("");
            int result = statement.executeUpdate("UPDATE Customers\n" +
                    "SET FirstName = 'Lerato', LastName = 'Mabitso'\n" +
                    "WHERE CustomerID = 1");
            if (result == 1){
                System.out.println("updated!");
            }else{
                System.out.println("Not updated");
            }

            result = statement.executeUpdate("DELETE FROM Customers\n" +
                    "WHERE CustomerID = 2");
            if (result == 1){
                System.out.println("Row Deleted!");
            }else{
                System.out.println("Row was not Deleted");
            }

            resultSet = statement.executeQuery("select COUNT(Status) \n" + "from Orders\n" +
                    "where Status = 'Shipped'");
            resultSet.next();
            System.out.println(resultSet.getInt(1));

            resultSet = statement.executeQuery("select MAX(Amount)\n" + "from Payments");
            resultSet.next();
            System.out.println(resultSet.getDouble(1));

            if (conn != null){
                System.out.println("Successfully connected to the Database!!!");
            }else {
                System.out.println("Couldn't connect to the Database");
            }

            conn.close();


        }catch (Exception e){ //Handle errors
            System.out.println(e);
        }
    }

}
