package com.revature.model;
import com.revature.service.Jdbc;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class User{

    private String username;
    private String password;
    private  int userid;
    private int checkingId;
    private int savingsId;
    private int creditId;
    private HashMap<String, Integer> accountIds = new HashMap<String, Integer>();
    public HashMap<String, Account> myAccounts = new HashMap<String, Account>();

    public User(){}

    public User(String myname, String mypwd){
        username = myname;
        password = mypwd;
    }

    public User(String myname, String mypwd, int myid, int myCheckingId, int mySavingsId, int myCreditId){
        username = myname;
        password = mypwd;
        userid = myid;

        checkingId = myCheckingId;
        savingsId = mySavingsId;
        creditId = myCreditId;

        accountIds.put("H", myCheckingId);
        accountIds.put("S", mySavingsId);
        accountIds.put("C", myCreditId);

    }

    //creates account objects and maps them to the navchars
    public void mapAccounts(){

        try{
            myAccounts.put("H", Jdbc.setAccount(accountIds.get("H")));
            myAccounts.put("S", Jdbc.setAccount(accountIds.get("S")));
            myAccounts.put("C", Jdbc.setAccount(accountIds.get("C")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCheckingId() {
        return checkingId;
    }

    public void setCheckingId(int checkingId) {
        this.checkingId = checkingId;
    }

    public int getSavingsId() {return savingsId;}

    public void setSavingsId(int savingsId) {
        this.savingsId = savingsId;
    }

    public int getCreditId() {return creditId;}

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public int getIdByInput(String myInput){
        return accountIds.get(myInput);
    }

}
