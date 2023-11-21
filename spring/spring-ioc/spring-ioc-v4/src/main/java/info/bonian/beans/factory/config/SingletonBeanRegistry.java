package info.bonian.beans.factory.config;


public interface SingletonBeanRegistry {


    void registerSingleton(String beanName, Object bean);

    boolean containsSingleton(String beanName);

    Object getSingleton(String beanName);

    String[] getSingletonNames();

    void removeSingleton(String name);
}
