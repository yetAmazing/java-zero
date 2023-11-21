package info.bonian.test;

import info.bonian.context.ClassPathXmlApplicationContext;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/26 15:00
 */
public class Test {

    public static void main(String[] args) {
        String file = "beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(file);
        AService aService = (AService) context.getBean("aService");
        aService.sayHello();
    }
}
