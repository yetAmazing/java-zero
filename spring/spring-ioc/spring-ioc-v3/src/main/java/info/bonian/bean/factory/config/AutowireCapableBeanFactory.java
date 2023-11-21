package info.bonian.bean.factory.config;

import info.bonian.bean.factory.support.AbstractBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 注入bean
 * @author: here
 * @date: 2023/10/29 01:18
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    private final List<AutowiredAnnotationBeanPostProcessor> beanPostProcessors = new ArrayList<AutowiredAnnotationBeanPostProcessor>();

    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public int getBeanPostProcessorCount(){
        return this.beanPostProcessors.size();
    }

    public List<AutowiredAnnotationBeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }

    public Object applyBeanPostProcessBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for(AutowiredAnnotationBeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            beanPostProcessor.setBeanFactory(this);
            result = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }

    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for(BeanFactoryPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            result = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }
}
