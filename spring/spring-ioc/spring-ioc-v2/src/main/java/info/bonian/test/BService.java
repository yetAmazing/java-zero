package info.bonian.test;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/25 02:04
 */
public class BService {
    private CService bbs;

    public CService getBbs() {
        return bbs;
    }
    public void setBbs(CService bbs) {
        this.bbs = bbs;
    }
    public BService() {
    }
    public void sayHello() {
        System.out.println("BService says hello");
        bbs.sayHello();
    }
}
