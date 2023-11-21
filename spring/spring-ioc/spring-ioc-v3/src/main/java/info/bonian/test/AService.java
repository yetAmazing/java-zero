package info.bonian.test;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/28 23:55
 */
public class AService implements BaseService{
    private String name;
    private int level;

    private String property1;
    private String property2;
    private BService bService;


    public AService(String name, int level){
        this.name = name;
        this.level = level;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public BService getBService() {
        return bService;
    }

    public void setBService(BService bService) {
        this.bService = bService;
    }

    public void init(){
        // todoSomething
        System.out.println("AService init method was invoke");
    }

    public void sayHello() {
        System.out.println("AService say hello");
    }
}
