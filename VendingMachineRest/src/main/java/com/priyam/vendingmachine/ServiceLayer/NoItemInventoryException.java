package com.priyam.vendingmachine.ServiceLayer;
public class NoItemInventoryException extends Exception{
      public NoItemInventoryException(String message){
        
       super(message);
    }
     public NoItemInventoryException(String message,Throwable cause){
        
       super(message,cause);
    }
}
