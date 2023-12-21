package com.revature.service;

import com.revature.model.*;
import java.sql.*;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

import static com.revature.Main.*;

public class Jdbc {

    private static Connection myjdbc;

    public static void setConn(){

        try{
            myjdbc = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project0",
                    "mbrock76", "H@rk0n3n_76");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static User getUser(String myUsername){

        User myuser = null;
        try{
            PreparedStatement mystmt = myjdbc.prepareStatement("SELECT * FROM users WHERE username =?");
            mystmt.setString(1, myUsername);
            System.out.println("asdf");
            ResultSet myresult = mystmt.executeQuery();


            if(myresult.next()){
                myuser = new User(
                        myresult.getString("username"),
                        myresult.getString("password"),
                        myresult.getInt("user_id"),
                        myresult.getInt("checking_id"),
                        myresult.getInt("savings_id"),
                        myresult.getInt("credit_id")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return myuser;
    }

    public static User createUser(String myUsername, String myPassword){

        User tempuser = null;
        try{
            //create the new user record with some default values
            PreparedStatement stmt = myjdbc.prepareStatement("insert into users(username, password) values(?,?)");
            stmt.setString(1, myUsername);
            stmt.setString(2,myPassword);
            stmt.execute();

            //get the newly created id
            tempuser = getUser(myUsername);

            //create the 3 account records for the user
            String[] accountTypes = {"checking", "savings", "credit"};
            for(String each: accountTypes){
                stmt = myjdbc.prepareStatement("INSERT INTO accounts(user_id, type) VALUES(?,?)");
                stmt.setInt(1, tempuser.getUserid());
                stmt.setString(2,each);
                stmt.execute();
            }

            //map the new account ids
            HashMap<String,Integer> accountIds = new HashMap<String,Integer>();

            stmt = myjdbc.prepareStatement("SELECT * FROM accounts WHERE user_id =?");
            stmt.setInt(1, tempuser.getUserid());
            ResultSet myresult = stmt.executeQuery();

            while (myresult.next()) {
                accountIds.put(myresult.getString("type"), myresult.getInt("account_id"));
            }

            //set the account ids in the user table
            for(Map.Entry<String,Integer> temp:accountIds.entrySet()){
                stmt = myjdbc.prepareStatement("UPDATE users SET "+temp.getKey()+"_id=? WHERE user_id =?");
                stmt.setInt(1,temp.getValue());
                stmt.setInt(2,tempuser.getUserid());
                stmt.execute();
            }
            tempuser = getUser(tempuser.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }

        return tempuser;
    }

    //gathers account data and creates an account object, to be stored in the user object
    public static Account setAccount(int myid){

        Account temp = null;
        try{
            PreparedStatement stmt = myjdbc.prepareStatement("SELECT * FROM accounts WHERE account_id =?");
            stmt.setInt(1, myid);
            ResultSet myresult = stmt.executeQuery();
            if(myresult.next()){
                temp = new Account(myresult.getInt("account_id"),myresult.getDouble("balance"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return temp;
    }

    //gets a list of transactions filtered by user id
    public static ArrayList<String[]> getTransactions (int myAccountId){

        ArrayList<String[]> temp = new ArrayList<>();
        try{
            PreparedStatement stmt = myjdbc.prepareStatement("SELECT * FROM transactions WHERE account_id =?");
            stmt.setInt(1, myAccountId);
            ResultSet myresult = stmt.executeQuery();

            while (myresult.next()) {
                temp.add(new String[]{myresult.getString("date"), myresult.getString("description"), myresult.getString("amount")});
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return temp;
    }

    //sets the balance of an account with a positive or negative number
    public static void setBalance(int myAccountId, double myAmount, String myDesc){

        try{
            PreparedStatement stmt = myjdbc.prepareStatement("UPDATE accounts SET balance=balance+? WHERE account_id =?");
            stmt.setDouble(1,myAmount);
            stmt.setInt(2,myAccountId);
            stmt.execute();

            stmt = myjdbc.prepareStatement("INSERT INTO transactions(account_id, amount, description) VALUES(?,?,?)");
            stmt.setDouble(2,myAmount);
            stmt.setInt(1,myAccountId);
            stmt.setString(3, myDesc);
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
