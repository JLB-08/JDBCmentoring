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
            
           
            addNewStudents(connection, 3,"louie",19);
            addNewStudents(connection, 5,"louie",19);
            printAllStudents(connection);
            connection.close();
            
        }catch(ClassNotFoundException e){
            System.out.println("Cannot load Driver");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printAllStudents(Connection connection) throws SQLException {
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
        }
    }
    
     public static void addNewStudents(Connection connection, int id, String name, int age) throws SQLException {
         PreparedStatement statement =  connection.prepareStatement("INSERT INTO groupmates VALUES (?, ?, ?)");
         
         statement.setInt(1, id);
         statement.setString(2, name);
         statement.setInt(3, age);
         
         int nb = statement.executeUpdate();
         if (nb > 0){
             System.out.println("Inserted " + nb + " rows");
         }else{
             System.out.println("Not inserted");
         }
     }

}
