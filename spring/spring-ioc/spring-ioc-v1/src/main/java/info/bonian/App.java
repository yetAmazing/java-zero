package info.bonian;

import info.bonian.beans.ClassPathXmlApplicationContext;
import info.bonian.service.DemoBean;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        DemoBean demoBean = (DemoBean)ctx.getBean("demoBean");
        demoBean.smallMarkBrother();
    }
}
