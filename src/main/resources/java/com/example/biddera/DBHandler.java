package com.example.biddera;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHandler {
    private static final String connectionStrig =  "jdbc:mysql://localhost:3306/biddera?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String user = "root";
	private static final String pass = "";

    public void AddNewUser( Register r ){
        
        Connection myConn = null;
		
        try {  
            myConn = DriverManager.getConnection(connectionStrig, user , pass);	
            String query = "INSERT INTO `all_accounts`( `username`, `email`, `address`, `cardnumber`, `password`) VALUES (?,?,?,?,?)";
            java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setString(1, r.getUsername());
            preparedStmt.setString(2, r.getEmail());
            preparedStmt.setString(3, r.getAddress());
            preparedStmt.setString(4, r.getCardnumber());
            preparedStmt.setString(5, r.getPassword());
            preparedStmt.execute();
            
        }catch(Exception exe){
            System.out.println(exe);
        } 
    }    


}
