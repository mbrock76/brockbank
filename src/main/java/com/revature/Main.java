package com.revature;

import com.revature.service.*;
import com.revature.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.*;

public class Main {

    /*state represents the depth of traversal into the application, and is used to limit functionality
      in the navChars array*/
    public static int state = 0;

    /*navChars are used to limit functionality to within the scope of the applications current state:
    L:  login/out
    X:  exit application
    H:  checking/debit account scope
    S:  savings account scope
    C:  credit card scope
    W:  withdraw funds
    D:  deposit funds
    T:  transfer funds(requires a destination input)
    P:  pay bill, cc only*/
    public static final String[] navChars = {"LX", "LXHSC", "LXHSCWDT<>", "LXHSCWDT<>", "LXHSCP<>"};
    public static User myuser;
    public static Scanner myScnr = new Scanner(System.in);
    public static boolean myexit = false;//used to exit a loop out of reach TODO why doesnt option myexit: work?
    public static String input;
    //public static Logger mylogger = LogManager.getLogger(Main.class); //todo cant add to classpath in cmd

    public static void main(String[] args){

        try{
            Jdbc.setConn();//init the static class
        }catch (Exception e){
            e.printStackTrace();
        }


        Printr.subtext.add(Printr.welcome);
        print();

        //main loop
        while(!myexit){

            input = myScnr.nextLine();
            input = input.toUpperCase();

            //exclude anything not in navchars
            if(valNavInput(input)){

                switch (input){
                    //log in or out
                    case "L":
                        logInOut();
                        break;
                    //force quit
                    case "X":
                        System.out.println(" Application closed.");
                        System.exit(0);
                        break;
                    //fall through to switch between, or in/out, of the 3 accounts
                    case "H":
                    case "S":
                    case "C":
                        //enters account sub-state or returns to loggedin state
                        if(state == navChars[1].indexOf(input)){
                            state = 1;
                        }else{
                            state = navChars[1].indexOf(input);
                        }

                        print();
                        break;
                    //fall through for account actions
                    case "W":
                    case "D":
                    case "P":
                    case "T":
                        modBalance();
                        break;
                    //fall through to change the displayed month
                    case "<":
                    case ">":
                        shiftMonth();
                        print();
                        break;
                }
            }else{
                System.out.println(String.format("Invalid input. Please enter one of the %scyan%s navigation characters.", Printr.ANSI_BRIGHT_CYAN, Printr.ANSI_RESET));
                try{
                    Thread.sleep(1500);

                }catch (Exception e){
                    e.printStackTrace();
                }
                print();
            }
        }

    }

    //calls Printr class methods to output strings
    public static void print(){

        try{
            //clear the screen, cmd only
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }

        //print the brock bank logo then fork, finish with subtext
        Printr.logo();
        if(state == 0){
            Printr.login();
            Printr.end();
        }else{
            Printr.loggedin();
            Printr.accounts();
        }
        Printr.printSubText();
    }

    //check that input is one of the navigation characters
    public static boolean valNavInput(String myinput){

        if(myinput.length() == 1 && navChars[state].contains(myinput.toUpperCase())){
            return true;
        }
        return false;
    }

    //multipurpose method to login or register
    public static void logInOut(){

        //clear subtext and append the new prompt
        Printr.subtext.removeAll(Printr.subtext);
        Printr.subtext.add(Printr.login);
        Printr.subtext.add(String.format("\n  %c Username: ", Printr.sl_tr));
        print();

        String myUsername = "";
        String myPassword = "";

        //fork based on whether the state is logged in or not
        if(state == 0) {

            while (true) {

                //get user input and setup a regex matcher
                String temp = myScnr.nextLine();
                //temp = temp.replaceAll("[^a-zA-Z0-9_]", "");
                Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
                Matcher m = p.matcher(temp);

                //check whether the input is outside the constraints
                if (m.find() || temp.length() > 25) {
                    System.out.println("\n Please remain within the constraints.");
                    try{
                        Thread.sleep(1500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    print();

                //backup or out
                } else if (temp.toUpperCase().equals("B")) {

                    if (Printr.subtext.get(Printr.subtext.size() - 1).contains("Password")) {
                        Printr.subtext.removeAll(Printr.subtext);
                        Printr.subtext.add(Printr.login);
                        Printr.subtext.add(String.format("\n  %c Username: ", Printr.sl_tr));
                        print();
                    } else {
                        Printr.subtext.removeAll(Printr.subtext);
                        Printr.subtext.add(Printr.welcome);
                        print();
                        break;
                    }
                //flush a blank input
                } else if (temp.equals("")) {
                    print();
                } else {
                    //if the depth is at password:
                    if (Printr.subtext.get(Printr.subtext.size() - 1).contains("Password")) {
                        myPassword = temp;

                        try{
                            //check if the user exists
                            myuser = Jdbc.getUser(myUsername);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        //if it does...
                        if (myuser != null) {
                            //check the password
                            if (myuser.getPassword().equals(myPassword)) {
                                try{
                                    //load account info
                                    myuser.mapAccounts();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                Printr.subtext.removeAll(Printr.subtext);
                                Printr.subtext.add(String.format("\n                            Welcome back %s!", myuser.getUsername()));
                                state = 1;
                                print();
                                try{
                                    Thread.sleep(1500);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                Printr.subtext.removeAll(Printr.subtext);
                                print();
                                break;

                            //wrong password
                            } else {
                                System.out.println("\n The credentials do not match our records.");
                                try{
                                    Thread.sleep(1500);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                //dial back the subtext (restart login process)
                                Printr.subtext.removeAll(Printr.subtext);
                                Printr.subtext.add(Printr.login);
                                Printr.subtext.add(String.format("\n  %c Username: ", Printr.sl_tr));
                                print();
                            }
                        //username does not exist, register instead
                        } else {
                            try{
                                myuser = Jdbc.createUser(myUsername, myPassword);
                                myuser.mapAccounts();
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            Printr.subtext.removeAll(Printr.subtext);
                            Printr.subtext.add(String.format("\n                               Welcome %s!", myuser.getUsername()));
                            state = 1;
                            print();
                            try{
                                Thread.sleep(1500);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Printr.subtext.removeAll(Printr.subtext);
                            print();

                            break;
                        }

                    //if the depth is at username
                    } else {

                        myUsername = temp;
                        Printr.subtext.remove(Printr.subtext.size() - 1);
                        Printr.subtext.add(String.format("\n  %c Username: %s", Printr.sl_trb, myUsername));
                        Printr.subtext.add(String.format("\n  %c Password: ", Printr.sl_tr));
                        print();

                    }
                }
            }
        //logout instead
        }else{
            state = 0;
            Printr.subtext.removeAll(Printr.subtext);
            Printr.subtext.add(Printr.welcome);
            print();
        }
    }

    //dual purpose add or subtract method
    public static void modBalance(){

        //correlates user input to transaction type
        HashMap<String, String> actions = new HashMap<String,String>();
        actions.put("W", "Withdrawal");
        actions.put("D", "Deposit");
        actions.put("T", "Transfer");
        actions.put("P", "Payment");

        Printr.subtext.removeAll(Printr.subtext);
        Printr.subtext.add(String.format("-%s:                                                            %sB%sack", actions.get(input), Printr.ANSI_BRIGHT_CYAN, Printr.ANSI_RESET));
        Printr.subtext.add(String.format("\n %c Amount: ", Printr.sl_tr));
        print();

        boolean outer = true;
        while(outer) {

            //get input and sanitize
            String temp = myScnr.nextLine();

            //back out
            if(temp.toUpperCase().equals("B")){
                Printr.subtext.removeAll(Printr.subtext);
                print();
                outer = false;

            //validate whether input is a double
            }else if(checkMyDubs(temp)){

                //if it is, shorten it to currency todo find out if there is a currency class
                Double myAmount = Double.parseDouble(String.format("%.2f", Double.parseDouble(temp)));

                //if subract:
                if(input.equals("W") || input.equals("T")){

                    //make it negative
                    myAmount = -myAmount;

                    //but still check if the number is smaller than the balance, not lower than
                    if(Math.abs(myAmount) <= myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getBalance()){

                        //transfer
                        if(input.equals("T")){
                            Printr.subtext.removeAll(Printr.subtext);
                            Printr.subtext.add(String.format("-%s:                                                            %sB%sack", actions.get(input), Printr.ANSI_BRIGHT_CYAN, Printr.ANSI_RESET));
                            Printr.subtext.add(String.format("\n %c Amount: %.2f", Printr.sl_trb, myAmount));
                            Printr.subtext.add(String.format("\n %c Destination account(%sHSC%s): ", Printr.sl_tr, Printr.ANSI_BRIGHT_CYAN, Printr.ANSI_RESET));
                            print();

                            boolean inner = true;
                            while(inner){
                                //get the account destination, could have a username input also for foreign accounts
                                String transferDest = myScnr.nextLine();
                                transferDest = transferDest.toUpperCase();

                                //validate input to ohe of three accounts
                                if("HSC".contains(transferDest) && transferDest.length() == 1){
                                    try{
                                        //subtract from one record and add to the other
                                        Jdbc.setBalance(myuser.myAccounts.get(transferDest).getAccountId(), Math.abs(myAmount), actions.get(input).toLowerCase());
                                        Jdbc.setBalance(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getAccountId(), myAmount, actions.get(input).toLowerCase());
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                    //likewise adjust the balance in the user's account objects to match
                                    myuser.myAccounts.get(transferDest).setBalance(myuser.myAccounts.get(transferDest).getBalance() + Math.abs(myAmount));
                                    myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).setBalance(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getBalance() + myAmount);

                                    //that hashmap for semantics
                                    System.out.println(actions.get(input) + " successful!");
                                    try{
                                        Thread.sleep(1500);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    //clear the subtext and the while switches
                                    Printr.subtext.removeAll(Printr.subtext);
                                    print();

                                    inner = false;
                                    outer = false;

                                //back up one
                                }else if(transferDest.equals("B")){
                                    Printr.subtext.remove(Printr.subtext.size()-1);
                                    print();
                                    inner = false;

                                //catch bad input
                                }else{
                                    System.out.println("Please enter one of the three account identifiers.");
                                    try{
                                        Thread.sleep(1500);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    print();
                                }
                            }

                        //single withdrawal
                        }else{
                            try{
                                Jdbc.setBalance(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getAccountId(), myAmount, actions.get(input).toLowerCase());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).setBalance(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getBalance() + myAmount);

                            System.out.println(actions.get(input) + " successful!");
                            try{
                                Thread.sleep(1500);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Printr.subtext.removeAll(Printr.subtext);
                            print();

                            outer = false;
                        }


                    }else{
                        System.out.println("Insufficient funds.");
                        try{
                            Thread.sleep(1500);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        print();
                    }
                }else if(input.equals("D") || input.equals("P")){

                    try{
                        Jdbc.setBalance(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getAccountId(), Math.abs(myAmount), actions.get(input).toLowerCase());

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).setBalance(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getBalance() + myAmount);

                    System.out.println(actions.get(input) + " successful!");
                    try{
                        Thread.sleep(1500);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Printr.subtext.removeAll(Printr.subtext);
                    print();

                    outer = false;
                }
            }else{
                System.out.println("Please enter a valid number.");
                try{
                    Thread.sleep(1500);

                }catch (Exception e){
                    e.printStackTrace();
                }
                print();
            }
        }
    }

    public static boolean checkMyDubs(String myInput){

        try{
            Double temp = Double.parseDouble(String.format("%.2f", Double.parseDouble(myInput)));
            if(temp > 0.00){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public static void shiftMonth(){

        if(input.equals("<")){
            myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).setCurrentViewDate(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getCurrentViewDate().minusMonths(1));
        }else{
            myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).setCurrentViewDate(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getCurrentViewDate().plusMonths(1));
        }
    }
}
