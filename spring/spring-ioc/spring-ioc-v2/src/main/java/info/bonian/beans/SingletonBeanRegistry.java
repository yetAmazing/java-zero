package info.bonian.beans;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/9/22 15:51
 */
public interface SingletonBeanRegistry {

    /**
     * 自己原本想的是放入一个BeanDefinition
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();
}
