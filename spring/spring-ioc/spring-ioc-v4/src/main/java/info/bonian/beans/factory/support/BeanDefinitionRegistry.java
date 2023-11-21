package info.bonian.beans.factory.support;

import info.bonian.beans.factory.config.BeanDefinition;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/11/16 00:22
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    void removeBeanDefinition(String name);
}
