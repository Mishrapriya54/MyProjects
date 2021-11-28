package com.priyam.vendingmachine.UI;

import java.math.BigDecimal;
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
    public Boolean readBool(String prompt) {
        System.out.println(prompt);
        Scanner readBool = new Scanner(System.in);
        Boolean value = readBool.hasNextLine();
        return value;
    }

//
    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        Scanner readint = new Scanner(System.in);
        int value = readint.nextInt();
        return value;
    }

//Reading integer values entered by user,in a specific range,set in view class.
    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        Scanner readint = new Scanner(System.in);
        int value = readint.nextInt();
        while (value < min || value > max) {
            System.out.println("Please enter the value between given numbers");
            value = readint.nextInt();
        }
        return value;

    }
//Reading bigdecimal values entered by user,in a specific range,set in view class.

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        System.out.println(prompt);

        Scanner readBigDecimal = new Scanner(System.in);
        BigDecimal value = readBigDecimal.nextBigDecimal();
        while ((value.compareTo(min)) == -1 || (value.compareTo(max)) == 1) {
            System.out.println("Please Enter a Valid amount.");
            value = readBigDecimal.nextBigDecimal();

        }

        return value;
    }

}
