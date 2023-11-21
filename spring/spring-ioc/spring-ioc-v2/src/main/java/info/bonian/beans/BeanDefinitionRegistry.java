package info.bonian.beans;

/**
 * @description: 注册beanDefinition
 * @author: here
 * @date: 2023/9/20 16:42
 */
public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String name, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    void removeBeanDefinition(String name);
}
