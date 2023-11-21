package info.bonian.beans;

import org.dom4j.Element;

import java.util.List;
import java.util.Map;

/**
 * @description: 不是通过别人去取，而是直接放入beanFactory中
 * @author: here
 * @date: 2023/9/19 21:21
 */
public class XmlBeanDefinitionReader {
    private ClassPathXmlApplicationContext beanFactory;

    public XmlBeanDefinitionReader(ClassPathXmlApplicationContext beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void readerXml(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String clazz = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, clazz);
            beanFactory.registryBeanDefinition(beanDefinition);
        }
    }
}
