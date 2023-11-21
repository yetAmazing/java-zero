package info.bonian.beans.factory.config;

import info.bonian.beans.BeansException;
import info.bonian.beans.factory.BeanFactory;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/11/17 23:41
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
