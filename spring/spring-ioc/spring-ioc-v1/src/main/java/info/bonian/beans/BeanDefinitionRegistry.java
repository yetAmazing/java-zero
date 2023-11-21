package info.bonian.beans;

/**
 * @description: 注册bean，判断bean是否存在，获取bean，移除bean
 * @author: here
 * @date: 2023/9/19 22:01
 */
public interface BeanDefinitionRegistry {

    void removeBeanDefinition(String name);

    void registryBeanDefinition(BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);
}
