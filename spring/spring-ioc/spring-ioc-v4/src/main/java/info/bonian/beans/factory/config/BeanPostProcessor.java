package info.bonian.beans.factory.config;

import info.bonian.beans.factory.BeanFactory;

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);

    void setBeanFactory(BeanFactory beanFactory);
}
