package info.bonian.context;

import info.bonian.bean.factory.BeanFactory;
import info.bonian.bean.factory.config.AutowireCapableBeanFactory;
import info.bonian.bean.factory.config.BeanFactoryPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/29 23:24
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {

    private AutowireCapableBeanFactory beanFactory;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessorList = new ArrayList<BeanFactoryPostProcessor>();


    public ClassPathXmlApplicationContext(String fileName){
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh){
        // todo 自行实现

    }

    private void onRefresh() {
        this.beanFactory.refresh();
    }

    public Object getBean(String name) {
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

    public void publishEvent(ApplicationEvent applicationEvent) {

    }
}
