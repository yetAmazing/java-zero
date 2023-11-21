package info.bonian.bean.factory.config;

public interface SingletonBeanRegistry {

    void registrySingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

}
