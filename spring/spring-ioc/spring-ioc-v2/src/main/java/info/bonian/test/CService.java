package info.bonian.test;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/25 02:05
 */
public class CService {

    private AServiceImpl as;

    public AServiceImpl getAs() {
        return as;
    }
    public void setAs(AServiceImpl as) {
        this.as = as;
    }
    public CService() {
    }
    public void sayHello() {
        System.out.println("CService says hello");

    }
}
