package info.bonian.context;

import info.bonian.beans.BeanFactory;
import info.bonian.beans.SimpleBeanFactory;
import info.bonian.beans.XmlBeanDefinitionReader;
import info.bonian.core.ClassPathXmlResource;
import info.bonian.core.Resource;

/**
 * @description: 核心上下文
 * @author: here
 * @date: 2023/10/25 03:33
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher{

    private SimpleBeanFactory simpleBeanFactory;
    private String fileName;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        this.simpleBeanFactory = beanFactory;
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource);
        if(isRefresh) {
           beanFactory.refresh();
        }
    }
    @Override
    public void publishEvent(ApplicationEvent applicationEvent) {
        // doSomething as logo or save in db
    }

    @Override
    public Object getBean(String beanName) {
        return this.simpleBeanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.simpleBeanFactory.containsBean(beanName);
    }

    @Override
    public void registerBean(String beanName, Object bean) {
        this.simpleBeanFactory.registerBean(beanName, bean);
    }
}
