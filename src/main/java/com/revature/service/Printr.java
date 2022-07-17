package com.revature.service;

import static com.revature.Main.*;
import com.revature.model.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;

public class Printr {

    public static String n = System.getProperty("line.separator");
    public static ArrayList<String> subtext = new ArrayList<>();

    public static char dotsS = 0x2591;
    public static char dotsM = 0x2592;
    public static char dotsL = 0x2593;

    //single lines
    public static char sl_tb = 0x2502;
    public static char sl_lr = 0x2500;
    public static char sl_tblr = 0x253C;
    public static char sl_tr = 0x2514;
    public static char sl_trb = 0x251C;

    //double lines
    public static char lr = 0x2550;
    public static char tb = 0x2551;

    public static char tr = 0x255A;
    public static char br = 0x2554;
    public static char lb = 0x2557;
    public static char lt = 0x255D;

    public static char ltr = 0x2569;
    public static char lbr = 0x2566;
    public static char trb = 0x2560;
    public static char ltb = 0x2563;

    public static char ltrb = 0x256C;

    //TEXT COLOR:
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_NO_UNDERLINE = "\u001B[24m";

    //BACKGROUND COLOR
    public static final String ANSI_BLACK_BG = "\u001B[40m";
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
    public static final String ANSI_YELLOW_BG = "\u001B[43m";
    public static final String ANSI_BLUE_BG = "\u001B[44m";
    public static final String ANSI_PURPLE_BG = "\u001B[45m";
    public static final String ANSI_CYAN_BG = "\u001B[46m";
    public static final String ANSI_WHITE_BG = "\u001B[47m";

    public static final String ANSI_BRIGHT_BLACK_BG = "\u001B[100m";
    public static final String ANSI_BRIGHT_RED_BG = "\u001B[101m";
    public static final String ANSI_BRIGHT_GREEN_BG = "\u001B[102m";
    public static final String ANSI_BRIGHT_YELLOW_BG = "\u001B[103m";
    public static final String ANSI_BRIGHT_BLUE_BG = "\u001B[104m";
    public static final String ANSI_BRIGHT_PURPLE_BG = "\u001B[105m";
    public static final String ANSI_BRIGHT_CYAN_BG = "\u001B[106m";
    public static final String ANSI_BRIGHT_WHITE_BG = "\u001B[107m";

    //logo = 77 units wide
    //ten params:
    //%c%c%c%c%c%c%c%c%c%c

    //some universal frame lines
    public static final String top = String.format("%s%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%s",
            ANSI_CYAN,br,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lb,ANSI_RESET);

    public static final String mid = String.format("%s%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%s",
            ANSI_CYAN,trb,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,ltb,ANSI_RESET);

    public static final String bot = String.format("%s%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%s",
            ANSI_CYAN, tr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lt, ANSI_RESET);

    public static final String blank = String.format("%s%c                                                                           %c%s", ANSI_CYAN, tb, tb, ANSI_RESET);

    //the seperator for transaction data
    public static final String tableLine = String.format("%s%c%s  %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c  %s%c%s",
            ANSI_CYAN, tb, ANSI_RESET, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr,
            sl_tblr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr,
            sl_tblr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, sl_lr, ANSI_CYAN, tb, ANSI_RESET);

    public static final String welcome = String.format("                     Welcome to the CMD Banking Application!\n                Letters marked in %scyan%s are used to navigate.\n", ANSI_BRIGHT_CYAN, ANSI_RESET);
    public static final String login = String.format("\n -Please enter your credentials (25 chars, aA-zZ0-9_)                   %sB%sack", ANSI_BRIGHT_CYAN, ANSI_RESET);

    //the initial block
    public static void logo(){

        System.out.println(top);
        System.out.println(String.format("%s%c%s          %c%c%c%c%c%c                 %c      %c%c%c%c%c%c            %c                %s%c%s", ANSI_CYAN, tb, ANSI_RESET, br, lr, lr, lr, lr, lb, tb, br, lr, lr, lr, lr, lb, tb, ANSI_CYAN, tb, ANSI_RESET));
        System.out.println(String.format("%s%c%s          %c    %c                 %c  %c   %c    %c            %c  %c             %s%c%s", ANSI_CYAN, tb, ANSI_RESET, tb, tb, tb, tb, tb, tb, tb, tb, ANSI_CYAN, tb, ANSI_RESET));
        System.out.println(String.format("%s%c%s          %c%c%c%c%c%c%c %c%c%c%c %c%c%c%c %c%c%c%c %c%c%c%c%c  %c%c%c%c%c%c%c %c%c%c%c %c%c%c%c %c%c%c%c%c            %s%c%s", ANSI_CYAN, tb, ANSI_RESET, trb, lr, lr, lr, lr, ltr, lb, trb, lr, lr, lb, br, lr, lr, lb, br, lr, lr, lb, trb, lr, lr, ltr, lb, trb, lr, lr, lr, lr, ltr, lb, lr, lr, lr, lb, trb, lr, lr, lb, trb, lr, lr, ltr, lb, ANSI_CYAN, tb, ANSI_RESET));
        System.out.println(String.format("%s%c%s          %c     %c %c    %c  %c %c    %c   %c  %c     %c %c%c%c%c %c  %c %c   %c            %s%c%s", ANSI_CYAN, tb, ANSI_RESET, tb, tb, tb, tb, tb, tb, tb, tb, tb, tb, br, lr, lr, ltb, tb, tb, tb, tb, ANSI_CYAN, tb, ANSI_RESET));
        System.out.println(String.format("%s%c%s          %c%c%c%c%c%c%c %c    %c%c%c%c %c%c%c%c %c   %c  %c%c%c%c%c%c%c %c%c%c%c %c  %c %c   %c            %s%c%s", ANSI_CYAN, tb, ANSI_RESET, tr, lr, lr, lr, lr, lr, lt, tb, tr, lr, lr, lt, tr, lr, lr, lt, tb, tb, tr, lr, lr, lr, lr, lr, lt, tr, lr, lr, lt, tb, tb, tb, tb, ANSI_CYAN, tb, ANSI_RESET));

        //System.out.println(ANSI_BLACK + ANSI_WHITE_BACKGROUND + "ASDFASDF" + ANSI_RESET);

    }

    //logged out state
    public static void login(){
        System.out.println(mid);

        System.out.println(String.format("%s%c%s E%sx%sit                          %sL%sogin/Register                              %s%c%s", ANSI_CYAN, tb, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));

    }

    //if the user exists print centered name and change "L" to logout
    public static void loggedin(){
        System.out.println(mid);
        StringBuilder front = new StringBuilder(String.format("%s%c%s E%sx%sit", ANSI_CYAN, tb, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET));
        StringBuilder back = new StringBuilder(myuser.getUsername());
        int mod = (77 - 14 - myuser.getUsername().length()) % 2;
        for(int i = 0; i < ((77 - 14 - myuser.getUsername().length()) / 2); i++){
            front.append(" ");
            back.append(" ");
        }
        front.append((mod == 1)?" ": "");
        System.out.print(front);
        System.out.print(back);
        System.out.println(String.format("%sL%sogout %s%c%s", ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
    }

    //the three account names, will highlight
    public static void accounts(){

        System.out.println(String.format("%s%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%s",
                ANSI_CYAN, trb,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lbr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lbr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,ltb, ANSI_RESET));

        StringBuilder accountLine = new StringBuilder(String.format("%s%c%s", ANSI_CYAN, tb, ANSI_RESET));
        if(state == 2){
            accountLine.append(String.format("       %s %sC%sh%secking %s       %s%c%s", ANSI_WHITE_BG, ANSI_BLACK, ANSI_CYAN, ANSI_BLACK, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
        }else{
            accountLine.append(String.format("        C%sh%secking        %s%c%s", ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
        }
        if(state == 3){
            accountLine.append(String.format("        %s %sS%savings %s       %s%c%s", ANSI_WHITE_BG, ANSI_CYAN, ANSI_BLACK, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
        }else{
            accountLine.append(String.format("         %sS%savings        %s%c%s", ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
        }
        if(state == 4){
            accountLine.append(String.format("          %s %sC%sredit %s       %s%c%s", ANSI_WHITE_BG, ANSI_CYAN, ANSI_BLACK, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
        }else{
            accountLine.append(String.format("           %sC%sredit        %s%c%s", ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
        }
        System.out.println(accountLine);

        if(state == 1){
            System.out.println(String.format("%s%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%s",
                    ANSI_CYAN, tr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,ltr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,ltr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lr,lt, ANSI_RESET));
        }else {
            System.out.println(String.format("%s%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%s",
                    ANSI_CYAN, trb, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, ltr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, ltr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, lr, ltb, ANSI_RESET));


            ArrayList<String[]> myTx = null;
            try {
                myTx = Jdbc.getTransactions(myuser.getIdByInput(Character.toString(navChars[1].charAt(state))));
            } catch (Exception e) {

            }

            if(myTx != null){

                //create the header with current month and account balance
                StringBuilder accountHdr = new StringBuilder(String.format("%s%c%s                %s<%s %s %s>%s                  $ %s",
                        ANSI_CYAN, tb, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET, myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getCurrentViewDate().getMonth(), ANSI_BRIGHT_CYAN, ANSI_RESET, myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getBalance()));
                //TODO why is accounthdr.lenth() 88?
                for(int i = Double.toString(myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getBalance()).length() + myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getCurrentViewDate().getMonth().toString().length() + 41; i < 76; i++){
                    accountHdr.append(" ");
                }
                accountHdr.append(String.format("%s%c%s", ANSI_CYAN, tb, ANSI_RESET));
                System.out.println(accountHdr);
                System.out.println(blank);

                System.out.println(String.format("%s%c%s           Date:        %c         Desc:          %c         Amount:         %s%c%s", ANSI_CYAN, tb, ANSI_RESET, sl_tb, sl_tb, ANSI_CYAN, tb, ANSI_RESET));

                System.out.println(tableLine);

                //iterates throught the transactions and spaces them out
                for(String[] each: myTx){

                    if(String.format("%02d", myuser.myAccounts.get(Character.toString(navChars[1].charAt(state))).getCurrentViewDate().getMonthValue()).equals(each[0].substring(5,7))){
                        StringBuilder data = new StringBuilder(String.format("%s%c%s        %s      %c", ANSI_CYAN, tb, ANSI_RESET, each[0], sl_tb));//date

                        StringBuilder front = new StringBuilder();
                        StringBuilder back = new StringBuilder();
                        int mod = (24 - each[1].length()) % 2;
                        for(int i = 0; i < ((24 - each[1].length()) / 2); i++){
                            front.append(" ");
                            back.append(" ");
                        }
                        front.append((mod == 1)?" ": "");
                        data.append(String.format("%s%s%s%c", front, each[1], back, sl_tb));//desc

                        front = new StringBuilder();
                        back = new StringBuilder();
                        mod = (25 - String.format("%.2f", Double.parseDouble(each[2])).length()) % 2;
                        for(int i = 0; i < ((25 - String.format("%.2f", Double.parseDouble(each[2])).length()) / 2); i++){
                            front.append(" ");
                            back.append(" ");
                        }
                        front.append((mod == 1)?" ": "");
                        data.append(String.format("%s%.2f%s%s%c%s", front, Double.parseDouble(each[2]), back, ANSI_CYAN, tb, ANSI_RESET));//amount

                        System.out.println(data);
                        System.out.println(tableLine);
                    }
                }
                System.out.println(blank);

                if(state == 4){
                    System.out.println(String.format("%s%c%s                                %sP%sayment                                    %s%c%s", ANSI_CYAN, tb, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
                }else{
                    System.out.println(String.format("%s%c%s         %sW%sithdraw                %sD%seposit                  %sT%sransfer         %s%c%s", ANSI_CYAN, tb, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_BRIGHT_CYAN, ANSI_RESET, ANSI_CYAN, tb, ANSI_RESET));
                }

                System.out.println(bot);
            }
        }
    }

    //bottom cap
    public static void end(){

        System.out.println(bot);
    }

    //prints text outside the app enclosure
    public static void printSubText(){
        for(String each:subtext){
            System.out.print(each);
        }
    }

}
