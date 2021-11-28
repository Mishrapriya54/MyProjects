package com.priyam.flooringMasteryUI;

import com.priyam.flooringMasteryDao.InvalidInputException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;

public interface UserIO {

void print(String msg);

int readInt(String prompt)throws InputMismatchException ;

    int readInt(String prompt, int min, int max)throws InputMismatchException ;

    Boolean readBool(String prompt);

    String readString(String prompt);

    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
    
    String readDate(String prompt,boolean futureDate)throws ParseException,InvalidInputException;
    
    void printDate(Date msg);
       
    public String readStringNextLine(String prompt) ;
}

