
package com.priyam.dvd_library.dao;

public class DvdLibException extends Exception{
    public DvdLibException(String msg){
    super(msg);
    }
    public DvdLibException(String msg,Throwable cause){
    super(msg,cause);
    }
    
}
