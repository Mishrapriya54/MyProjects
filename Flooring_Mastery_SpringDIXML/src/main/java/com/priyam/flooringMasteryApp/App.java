package com.priyam.flooringmasteryApp;
import com.priyam.flooringMasteryController.FlooringMasteryController;
import java.text.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author : priya
 * @Date : 5/13/2021
 */
public class App {
    public static void main(String[] args) throws ParseException{
   ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
           ctx.getBean("controller", FlooringMasteryController.class);
        controller.execute();

}
}