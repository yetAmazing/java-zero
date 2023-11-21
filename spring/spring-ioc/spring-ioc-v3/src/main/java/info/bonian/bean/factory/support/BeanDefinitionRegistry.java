package info.bonian.bean.factory.support;

import info.bonian.bean.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String name, BeanDefinition beanDefinition);

    void removeBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);
}
