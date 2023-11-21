package info.bonian.bean.factory.support;

import info.bonian.bean.factory.BeanFactory;
import info.bonian.bean.factory.config.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: todo 余下的自行完善
 * @author: here
 * @date: 2023/10/29 01:24
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private List<String> beanDefinitionNames = new ArrayList<String>();

    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<String, Object>();


    public void refresh() {
        for(String beanName : beanDefinitionNames) {
            try{
                getBean(beanName);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public Object getBean(String name) {
        Object singleton = this.getSingleton(name);
        if (singleton == null) {
            BeanDefinition bd = beanDefinitionMap.get(name);

        }
        return null;
    }

    public boolean containsBean(String name) {
        return false;
    }

    public Class<?> getType(String name) {
        return null;
    }

    public boolean isSingleton(String name) {
        return false;
    }

    public boolean isPrototype(String name) {
        return false;
    }

    public void registryBeanDefinition(String name, BeanDefinition beanDefinition) {

    }

    public void removeBeanDefinition(String beanName) {

    }

    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    abstract public Object applyBeanPostProcessBeforeInitialization(Object bean, String beanName);

    abstract public Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName);
}
