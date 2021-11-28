/**
 *@author Priya Mishra
 *email: mpriya1954@gmail.com
 *date: 2/27/2021
 *purpose: This class calls controller.
 */
package com.priyam.dvd_library.app;
import com.priyam.dvd_library.controller.DvdLibController;
import com.priyam.dvd_library.dao.DvdLibDao;
import com.priyam.dvd_library.dao.DvdLibDaoImpl;
import com.priyam.dvd_library.ui.DvdLibUserIO;
import com.priyam.dvd_library.ui.DvdLibUserIOImpl;
import com.priyam.dvd_library.ui.DvdLibUserView;

public class DvdLibApp {
    public static void main(String[] args) {  
    //dependency injection    
    DvdLibUserIO myIo = new DvdLibUserIOImpl();
    DvdLibUserView myView = new DvdLibUserView(myIo);
    DvdLibDao myDao = new DvdLibDaoImpl();
    DvdLibController controller =new DvdLibController(myDao, myView);
    //calling execute method in controller
    controller.execute();
    }
}
