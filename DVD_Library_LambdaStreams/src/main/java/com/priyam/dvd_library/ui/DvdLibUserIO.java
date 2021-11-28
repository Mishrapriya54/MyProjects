
package com.priyam.dvd_library.ui;

import java.util.Date;

public interface DvdLibUserIO {
    void print(String msg);
    ////chage the print method
    void printDate(Date msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
    
    Date readDate(String prompt);
}
