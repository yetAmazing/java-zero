package info.bonian.beans.factory;

import info.bonian.beans.BeansException;

import java.util.Map;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/11/18 21:33
 */
public interface ListableBeanFactory extends BeanFactory{

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}
