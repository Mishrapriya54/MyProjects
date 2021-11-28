package com.priyam.vendingmachine.APP;

import com.priyam.vendingmachine.Controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@author: priya
 *@Date:05/06/2021
 */
public class App {
    public static void main(String[] args) {
//        UserIO myIo=new UserIOImpl();
//        VendingMachineView myView=new VendingMachineView(myIo);
//        VendingMachineAuditDAO myAudit=new VendingMachineAuditDaoImplt();
//        VendingMachineDao myDao=new VendingMachineDaoImpl();
//        VendingMachineService service=new VendingMachineServiceImpl(myDao,myAudit);
//        VendingMachineController controller=new VendingMachineController(service,myView);
//        controller.execute();

   ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        controller.execute();






    }
}


