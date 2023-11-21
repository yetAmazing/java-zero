package info.bonian.test;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/28 23:55
 */
public class BService implements BaseService{
    public void sayHello() {
        System.out.println("BService say hello");
    }

    private void init(){
        System.out.println("BService invoke init method");
    }
}
