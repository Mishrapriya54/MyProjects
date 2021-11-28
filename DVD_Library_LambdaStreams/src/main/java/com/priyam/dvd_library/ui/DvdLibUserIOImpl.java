/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.priyam.dvd_library.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DvdLibUserIOImpl implements DvdLibUserIO{
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
    public int readInt(String prompt) {
        System.out.println(prompt);
        Scanner readint = new Scanner(System.in);
        int value = readint.nextInt();
        return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        Scanner readint = new Scanner(System.in);
        int value = readint.nextInt();
        while (value < min || value > max) {
            System.out.println("Please enter the value between two given numbers");
            value = readint.nextInt();
        }
        return value;

    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        Scanner readdouble = new Scanner(System.in);
        double value = readdouble.nextDouble();
        return value;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        Scanner readdouble = new Scanner(System.in);
        double value = readdouble.nextDouble();
        while (value < min || value > max) {
            System.out.println("Please enter the value between two given numbers");
            value = readdouble.nextDouble();
        }
        return value;
    }

    @Override
    public float readFloat(String prompt) {

        System.out.println(prompt);
        Scanner readdouble = new Scanner(System.in);
        float value = readdouble.nextFloat();
        return value;

    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        System.out.println(prompt);
        Scanner readfloat = new Scanner(System.in);
        float value = readfloat.nextFloat();
        while (value < min || value > max) {
            System.out.println("Please enter the value between two given numbers");
            value = readfloat.nextFloat();
        }
        return value;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        Scanner readlong = new Scanner(System.in);
        long value = readlong.nextLong();
        return value;

    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        Scanner readlong = new Scanner(System.in);
        long value = readlong.nextLong();
        while (value < min || value > max) {
            System.out.println("Please enter the value between two given numbers");
            value = readlong.nextLong();
        }
        return value;
    }

    @Override
    public void printDate(Date msg) {
        System.out.println(msg);
    }

    @Override
    public Date readDate(String prompt) {
        System.out.println(prompt);
         Date parseDate=null;
         Scanner stringDate = new Scanner(System.in);
         String date=stringDate.nextLine();
         try{
         parseDate=new SimpleDateFormat("MM/dd/yyyy").parse(date);}
        catch (ParseException e){;}
         return parseDate;
    }
    
    
}
