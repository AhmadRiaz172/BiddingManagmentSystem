package com.example.biddera;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

    public int VerifyUser(Login login){

        Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
    
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
			myStmt = myConn.createStatement();			
			myRs = myStmt.executeQuery("select * from `all_accounts`");
			while (myRs.next()) {
                String u = myRs.getString("username");
                String p = myRs.getString("password");
                if ( u.equals(login.getUsername()) )
                    if ( p.equals(login.getPassword()) )
                        return myRs.getInt("id"); 
            }
        }catch(Exception exe){
            System.out.println("Unable to read all accounts table, error: " +  exe);
        } 

        return -1;
    }

    
    public void registerProduct(RegisterProduct r){ 
        Connection myConn = null;

        try {  
            myConn = DriverManager.getConnection(connectionStrig, user , pass);	
            String query = "INSERT INTO `all_products`(`name`, `category`, `description`, `image`, `minbid`, `datetime`,`uid`) VALUES (?,?,?,?,?,?,?)";
            java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setString(1, r.getName());
            preparedStmt.setInt(2, r.getCategory());
            preparedStmt.setString(3, r.getDescription());
            preparedStmt.setString(4, r.getImage());
            preparedStmt.setInt(5, r.getMinbid());
            preparedStmt.setString(6,r.getDateTime().toString() );
            preparedStmt.setInt(7,r.getUserid());
            preparedStmt.execute();
        }catch(Exception exe){
            System.out.println(exe);
        } 
    }

    
    public ArrayList<POD> getAllPODs(){
        ArrayList <POD> p = new ArrayList<>(); 

        
        Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
    
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
			myStmt = myConn.createStatement();			
            myRs = myStmt.executeQuery("select * from `all_products`");
            
			while (myRs.next()) {
                POD p1 = new POD();
                p1.setName(myRs.getString("name"));
                p1.setDesc(myRs.getString("description"));
                p1.setEndingDate(myRs.getString("datetime"));
                p1.setCurrent(0);
                p1.setId(myRs.getInt("id"));
                System.out.println(p1.getId());
                p1.setMinimum(myRs.getInt("minbid"));
                p.add(p1);
    
            }
        }catch(Exception exe){
            System.out.println("Unable to read all products table, error: " +  exe);
        } 

        ArrayList <AllBids> allBids = new ArrayList<AllBids>();
        allBids = getAllbids();
        for ( int i = 0 ; i < p.size(); i ++ ){
            int max = 0;
            for ( int j = 0 ;j < allBids.size() ; j++ ){
                if ( allBids.get(j).getProductid() == p.get(i).getId()  ){
                    if ( allBids.get(j).getAmount() > max )
                        max = allBids.get(j).getAmount();
                }
            }  
            p.get(i).setCurrent(max);
        }
        return p;
    }


    public Details getProductDEtails( String id ){
        
        Details details = new Details();
        int pid=Integer.parseInt(id);  

        Connection myConn = null;
		Statement myStmt = null;
        ResultSet myRs = null;
        
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
            myStmt = myConn.createStatement();	
            String query = 	"SELECT * FROM `all_products` WHERE id = (?)";	
			java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, pid);
            myRs = preparedStmt.executeQuery();
            while (myRs.next()) {

                details.setBidendingdate(myRs.getString("datetime"));
                details.setName(myRs.getString("name"));
                details.setDescription(myRs.getString("description"));
                details.setCategory(details.getCategoryString(myRs.getInt("category")));
                
            }
        }catch(Exception exe){
            System.out.println("Unable to read all products table for given id, error: " +  exe);
        } 

        ArrayList <AllBids> allBids = new ArrayList<AllBids>();
        allBids = getAllbids();
        
            int max = 0;
            int count = 0 ;
            for ( int j = 0 ;j < allBids.size() ; j++ ){
                if ( allBids.get(j).getProductid() == pid  ){
                    count ++;
                    if ( allBids.get(j).getAmount() > max )
                        max = allBids.get(j).getAmount();
                }

            }  
            details.setNumberofbids(count);
            details.setMaxbid(max);


        return details;
    }


    public ArrayList<AllBids> getAllbids(){
        Connection myConn = null;
		Statement myStmt = null;
        ResultSet myRs = null;
        
        ArrayList <AllBids> allbids = new ArrayList<AllBids>();
        try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
			myStmt = myConn.createStatement();			
			myRs = myStmt.executeQuery("select * from `bids`");
			while (myRs.next()) {
                AllBids allBidstupple = new AllBids();
                allBidstupple.setId(myRs.getInt("id"));
                allBidstupple.setUserid(myRs.getInt("userid"));
                allBidstupple.setProductid(myRs.getInt("productid"));
                allBidstupple.setAmount(myRs.getInt("bidamount"));
                allbids.add(allBidstupple);
            }
        }catch(Exception exe){
            System.out.println("Unable to read all accounts table, error: " +  exe);
        } 

        return allbids;
    }

    public void addBid( int pid, int uid, int bid ){
        Connection myConn = null;
        try {  
            myConn = DriverManager.getConnection(connectionStrig, user , pass);	
            String query = "INSERT INTO `bids`( `userid`, `productid`, `bidamount`) VALUES (?,?,?)";
            java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, uid);
            preparedStmt.setInt(2, pid);
            preparedStmt.setInt(3, bid);
            preparedStmt.execute();
        }catch(Exception exe){
            System.out.println(exe);
        } 
    }

    public ArrayList<MyRegisteredProducts> getMyRegisteredProducrs( int uid ){
        ArrayList<MyRegisteredProducts> al = new ArrayList<MyRegisteredProducts>();
        Connection myConn = null;
		Statement myStmt = null;
        ResultSet myRs = null;
        
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
            myStmt = myConn.createStatement();	
            String query = 	"SELECT * FROM `all_products` WHERE uid = (?)";	
			java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, uid);
            myRs = preparedStmt.executeQuery();
            while (myRs.next()) {
                MyRegisteredProducts mp = new MyRegisteredProducts();
                mp.setName(myRs.getString("name"));
                mp.setId(myRs.getInt("id"));
                mp.setBidending(myRs.getString("datetime"));
                int a = myRs.getInt("category");
                mp.setCategory(mp.getCategoryString(a));
                al.add(mp);
            }
        }catch(Exception exe){
            System.out.println("Unable to read all products table for given uid, error: " +  exe);
        } 

        ArrayList <AllBids> allBids = new ArrayList<AllBids>();
        allBids = getAllbids();
        for ( int i = 0 ; i < al.size(); i ++ ){
            int max = 0;
            int count = 0 ;
            for ( int j = 0 ;j < allBids.size() ; j++ ){
                if ( allBids.get(j).getProductid() == al.get(i).getId()  ){
                    count ++;
                    if ( allBids.get(j).getAmount() > max )
                        max = allBids.get(j).getAmount();
                }

            }  
            al.get(i).setTotal(count);
            al.get(i).setCurrentmax(max);


        }

        return al;
    }

   
    public ArrayList<MyRegisteredProducts> getAllRegisteredProducrs(  ){
        ArrayList<MyRegisteredProducts> al = new ArrayList<MyRegisteredProducts>();
        Connection myConn = null;
		Statement myStmt = null;
        ResultSet myRs = null;
        
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
            myStmt = myConn.createStatement();	
            String query = 	"SELECT * FROM `all_products`";	
			java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            myRs = preparedStmt.executeQuery();
            while (myRs.next()) {
                MyRegisteredProducts mp = new MyRegisteredProducts();
                mp.setName(myRs.getString("name"));
                mp.setId(myRs.getInt("id"));
                mp.setBidending(myRs.getString("datetime"));
                int a = myRs.getInt("category");
                mp.setCategory(mp.getCategoryString(a));
                mp.setDescription(myRs.getString("description"));
                al.add(mp);
            }
        }catch(Exception exe){
            System.out.println("Unable to read all products table for given uid, error: " +  exe);
        } 

        ArrayList <AllBids> allBids = new ArrayList<AllBids>();
        allBids = getAllbids();
        for ( int i = 0 ; i < al.size(); i ++ ){
            int max = 0;
            int count = 0 ;
            for ( int j = 0 ;j < allBids.size() ; j++ ){
                if ( allBids.get(j).getProductid() == al.get(i).getId()  ){
                    count ++;
                    if ( allBids.get(j).getAmount() > max )
                        max = allBids.get(j).getAmount();
                }

            }  
            al.get(i).setTotal(count);
            al.get(i).setCurrentmax(max);


        }

        return al;
    }



    public ArrayList<MyRegisteredProducts> getMyBids( int uid ){

        ArrayList <AllBids> allBids = new ArrayList<AllBids>();
        allBids = getAllbids();
        ArrayList <AllBids> filteredBids = new ArrayList<AllBids>();
        for ( int i = 0 ; i < allBids.size() ; i++ )
            if ( allBids.get(i).getUserid() == uid )
                filteredBids.add(allBids.get(i));
            
        ArrayList <MyRegisteredProducts> mrp = new ArrayList<MyRegisteredProducts>();
        System.out.println("Size of all bids: " + allBids.size() );
        System.out.println("Size of filtered bids: " + filteredBids.size() );
        System.out.println("Get uid: " + uid );
        
        for ( int i = 0 ; i < filteredBids.size() ; i++ ){
            Details d = new Details();
            d = getProductDEtails(String.valueOf( filteredBids.get(i).getProductid()));
            MyRegisteredProducts mp = new MyRegisteredProducts();
            mp.setMyBid(filteredBids.get(i).getAmount());
            mp.setId( filteredBids.get(i).getProductid());
            mp.setName(d.getName());
            mp.setBidending(d.getBidendingdate());
            mrp.add(mp);
        }
      

        allBids = getAllbids();
        for ( int i = 0 ; i < mrp.size(); i ++ ){
            int max = 0;
            int count = 0 ;
            for ( int j = 0 ;j < allBids.size() ; j++ ){
                if ( allBids.get(j).getProductid() == mrp.get(i).getId()  ){
                    count ++;
                    if ( allBids.get(j).getAmount() > max )
                        max = allBids.get(j).getAmount();
                }

            }  
            mrp.get(i).setTotal(count);
            mrp.get(i).setCurrentmax(max);
        }


        return mrp;
    }



    public Register getRegistrationDetails( int uid  ){
        Register register = new Register();
        Connection myConn = null;
		Statement myStmt = null;
        ResultSet myRs = null;
        
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
            myStmt = myConn.createStatement();	
            String query = 	"SELECT * FROM `all_accounts` WHERE id = (?)";	
			java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, uid);
            myRs = preparedStmt.executeQuery();
            while (myRs.next()) {
              register.setId(uid);
              register.setAddress(myRs.getString("address"));
              register.setEmail(myRs.getString("email"));
              register.setCardnumber(myRs.getString("cardnumber"));
              register.setPassword(myRs.getString("password"));
              register.setUsername(myRs.getString("username"));
              
            }
        }catch(Exception exe){
            System.out.println("Unable to read all accounts table for given uid, error: " +  exe);
        } 

        return register;
    }


    
    public void updateRegistrationDetails(Register r){ 
        Connection myConn = null;

        try {  
            myConn = DriverManager.getConnection(connectionStrig, user , pass);	
            String query = "UPDATE `all_accounts` SET `id`=(?),`username`=?,`email`=(?),`address`=(?),`cardnumber`=(?),`password`=(?) WHERE id = (?)";
            java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            System.out.println("Id to delete: ");
            
            System.out.println( r.getId());
            preparedStmt.setInt(1, r.getId());
            preparedStmt.setString(2, r.getUsername());
            preparedStmt.setString(3, r.getEmail());
            preparedStmt.setString(4, r.getAddress());
            preparedStmt.setString(5, r.getCardnumber());
            preparedStmt.setString(6, r.getPassword());
            preparedStmt.setInt(7, r.getId());

            preparedStmt.execute();
        }catch(Exception exe){
            System.out.println(exe);
        } 
    
    }




    
    public int VerifyAdmin(Login login){

        Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
    
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
			myStmt = myConn.createStatement();			
			myRs = myStmt.executeQuery("select * from `admins`");
			while (myRs.next()) {
                String u = myRs.getString("username");
                String p = myRs.getString("password");
                if ( u.equals(login.getUsername()) )
                    if ( p.equals(login.getPassword()) )
                        return myRs.getInt("id"); 
            }
        }catch(Exception exe){
            System.out.println("Unable to read admins table, error: " +  exe);
        } 

        return -1;
    }


    public ArrayList<Register> getAllUserAccounts(){
        ArrayList<Register> al = new ArrayList<Register>();
        Connection myConn = null;
		Statement myStmt = null;
        ResultSet myRs = null;
        
		try {
			myConn = DriverManager.getConnection(connectionStrig,user,pass);	
            myStmt = myConn.createStatement();	
            String query = 	"SELECT * FROM `all_accounts`";	
			java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
            myRs = preparedStmt.executeQuery();
            while (myRs.next()) {
                Register register = new Register();
                register.setId(myRs.getInt("id"));
                register.setAddress(myRs.getString("address"));
                register.setCardnumber(myRs.getString("cardnumber"));
                register.setEmail(myRs.getString("email"));
                register.setUsername(myRs.getString("username"));
                register.setPassword(myRs.getString("password"));

                al.add(register);
            }
        }catch(Exception exe){
            System.out.println("Unable to read all accounts table for given uid, error: " +  exe);
        } 


        return al;
    }




}
