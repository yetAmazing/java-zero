package info.bonian.test;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/9/27 13:55
 */
public class AServiceImpl implements AService{

    private String name;
    private int level;

    private String property1;

    private String property2;

    private BService ref1;

    public AServiceImpl(){

    }

    public AServiceImpl(String name, int level) {
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

    public BService getRef1() {
        return ref1;
    }

    public void setRef1(BService ref1) {
        this.ref1 = ref1;
    }

    @Override
    public void sayHello() {
        System.out.println("AServiceImpl say: name=" + name + ";level=" + level + "property1=" + property1+ "property2=" + property2);
        ref1.sayHello();
    }
}
