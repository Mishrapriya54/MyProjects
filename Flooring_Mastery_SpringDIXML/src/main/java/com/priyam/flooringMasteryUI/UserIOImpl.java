package com.priyam.flooringMasteryUI;

import com.priyam.flooringMasteryDao.InvalidInputException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserIOImpl implements UserIO {

    @Override
    public void print(String message) {
        System.out.println(message);

    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        Scanner readStr = new Scanner(System.in);
        String value = readStr.nextLine();
        return value;
    }

    @Override
    public String readStringNextLine(String prompt) {
        System.out.print(prompt);
        Scanner readStr = new Scanner(System.in);
        String value = readStr.nextLine();
        return value;
    }

    @Override
    public Boolean readBool(String prompt) {
        System.out.println(prompt);
        Scanner readBool = new Scanner(System.in);
        Boolean value = readBool.hasNextLine();
        return value;
    }

//
    @Override
    public int readInt(String prompt) throws InputMismatchException {
        System.out.println(prompt);
        Scanner readint = new Scanner(System.in);
        int value = readint.nextInt();
        return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) throws InputMismatchException {
        System.out.println(prompt);
        Scanner readint = new Scanner(System.in);
        int value = readint.nextInt();
        while (value < min || value > max) {
            System.out.println("Please enter the value between given numbers");
            value = readint.nextInt();
        }
        return value;

    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        System.out.println(prompt);
        BigDecimal value = null;
        Scanner readBigDecimal = new Scanner(System.in);
        value = readBigDecimal.nextBigDecimal();
        //if(!value.toString().isEmpty()){
        while ((value.compareTo(min)) == -1 || (value.compareTo(max)) == 1) {
            System.out.println("Please Enter a valid area,it should be more than 100sq ft");
            value = readBigDecimal.nextBigDecimal();
        }
        return value;
    }

    @Override
    public String readDate(String prompt, boolean futureDate) throws ParseException, InvalidInputException {
        String strDate = null;
        Date dateCurrent = new Date();
        Date OrderDate = null;

        //Date parseDate=null;
        if (futureDate == false) {
            System.out.println(prompt);
            Scanner stringDate = new Scanner(System.in);
            String date = stringDate.nextLine();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                strDate = sdf.format(sdf.parse(date));
            } catch (ParseException e) {
                System.out.println("Please enter valid date.");
                return strDate;
            }
        }
        if (futureDate == true) {
            String date;
            do {

                System.out.println(prompt);
                Scanner stringDate = new Scanner(System.in);
                date = stringDate.nextLine();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                    OrderDate = sdf.parse(date);
                } catch (ParseException ex) {
                    System.out.println("Please enter valid date.");
                    return null;
                }
                if (OrderDate.compareTo(dateCurrent) > 0 || OrderDate.compareTo(dateCurrent) == 0) {
                    break;
                } else {
                    System.out.println("Please Enter valid current/future date.");
                }
            } while (!(OrderDate.compareTo(dateCurrent) > 0 || OrderDate.compareTo(dateCurrent) == 0));
            return date;
        }

        return strDate;
    }

    @Override
    public void printDate(Date msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
