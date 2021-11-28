package com.priyam.vendingmachine.APP;
import com.priyam.vendingmachine.Controller.VendingMachineController;
import com.priyam.vendingmachine.DAO.VendingMachineAuditDAO;
import com.priyam.vendingmachine.DAO.VendingMachineAuditDaoImplt;
import com.priyam.vendingmachine.DAO.VendingMachineDao;
import com.priyam.vendingmachine.DAO.VendingMachineDaoImpl;
import com.priyam.vendingmachine.ServiceLayer.VendingMachineService;
import com.priyam.vendingmachine.ServiceLayer.VendingMachineServiceImpl;
import com.priyam.vendingmachine.UI.UserIO;
import com.priyam.vendingmachine.UI.UserIOImpl;
import com.priyam.vendingmachine.UI.VendingMachineView;

/**
 *@author: priya
 *@Date:04/15/2021
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo=new UserIOImpl();
        VendingMachineView myView=new VendingMachineView(myIo);
        VendingMachineAuditDAO myAudit=new VendingMachineAuditDaoImplt();
        VendingMachineDao myDao=new VendingMachineDaoImpl();
        VendingMachineService service=new VendingMachineServiceImpl(myDao,myAudit);
        VendingMachineController controller=new VendingMachineController(service,myView);
        controller.execute();
    }
}


