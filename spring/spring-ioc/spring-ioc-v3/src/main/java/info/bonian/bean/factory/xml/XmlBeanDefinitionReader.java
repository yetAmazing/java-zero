package info.bonian.bean.factory.xml;

import info.bonian.bean.factory.config.*;
import info.bonian.bean.factory.support.SimpleBeanFactory;
import info.bonian.core.Resource;
import org.dom4j.Element;

import java.util.List;

/**
 * @description: 读取xml文件
 * @author: here
 * @date: 2023/10/30 10:52
 */
public class XmlBeanDefinitionReader {

    private SimpleBeanFactory beanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 注意：
     * 1.获取子节点，是elements("")
     * 2.获取当前节点的属性attributeValue("")
     * 3.注意，不要返回null，而是返回新建对象
     * @param resource
     */
    public void loadBeanDefinition(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanName = element.attributeValue("class");

            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanName);
            String initMethod = element.attributeValue("init-method");
            beanDefinition.setInitMethodName(initMethod);


            List<Element> constructorList = element.elements("constructor-arg");
            ConstructorArgumentValues cavs = handleConstructor(constructorList);
            beanDefinition.setConstructorArgumentValues(cavs);

            List<Element> propertyList = element.elements("property");
            PropertyValues pvs = handleProperty(propertyList);
            beanDefinition.setPropertyValues(pvs);

            try{
                Class<?> clz = Class.forName(beanName);
                beanDefinition.setBeanClass(clz);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            beanFactory.registryBeanDefinition(beanId, beanDefinition);
        }
    }

    private PropertyValues handleProperty(List<Element> propertyElements) {
        PropertyValues propertyValues = new PropertyValues();
        for (Element element : propertyElements){
            // 引用对象的类型 beanClass，或者String、Integer之类
            String type = element.attributeValue("type");
            // 引用对象的名称
            String name = element.attributeValue("name");
            // 对象的值，但是如果是引用对象的时候，此处是没有值的
            String value = element.attributeValue("value");
            // bean的名称
            String ref = element.attributeValue("ref");
            // 如果如果ref不为null，则为常规对象
            boolean isRef = false;
            Object pv = null;
            if (ref != null) {
                isRef = true;
                pv = ref;
            } else {
                pv = value;
            }

            PropertyValue propertyValue = new PropertyValue(type, name, pv, isRef);
            propertyValues.add(propertyValue);
        }
        return propertyValues;
    }

    /**
     * 注意点：有ref
     * @param constructorElements
     * @return
     */
    private ConstructorArgumentValues handleConstructor(List<Element> constructorElements){
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        if (constructorElements != null && constructorElements.size() > 0) {
            for (Element element : constructorElements) {
                String type = element.attributeValue("type");
                String name = element.attributeValue("name");
                Object value = element.attributeValue("value");
                ConstructorArgumentValue constructorArgumentValue = new ConstructorArgumentValue(name, type, value);
                constructorArgumentValues.add(constructorArgumentValue);
            }
        }
        return constructorArgumentValues;
    }

}
