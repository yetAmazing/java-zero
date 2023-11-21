package info.bonian.test;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/28 23:55
 */
public class CService implements BaseService{

    private AService aService;

    public AService getAService() {
        return aService;
    }

    public void setAService(AService aService) {
        this.aService = aService;
    }

    public void sayHello() {
        System.out.println("CService say hello");
    }
}
