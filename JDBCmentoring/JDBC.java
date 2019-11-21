/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmentoring;

import java.sql.*;

/**
 *
 * @author 2ndyrGroupA
 */
public class JDBCmentoring {

    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/sample";
            Connection connection = DriverManager.getConnection(dbURL, "root", "");
            
            if(connection != null){
                System.out.println("Connection established!");
                
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM groupmates");
                
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    
                    System.out.println(id + ": " + name + ", " + age);
                }
                
                connection.close();
            }
            
        }catch(ClassNotFoundException e){
            System.out.println("Cannot load Driver");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
