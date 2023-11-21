package info.bonian.beans;

import info.bonian.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;


/**
 * 传入beanFactory
 * reader 需要返回什么
 * @description: 获取BeanDefinition
 * @author: here
 * @date: 2023/9/27 14:25
 */
public class XmlBeanDefinitionReader {

    private SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }


    public void loadBeanDefinition(Resource resource) {
        while (resource.hasNext()) {
            // 这个是一个bean
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition bd = new BeanDefinition(beanId, beanClassName);

            List<Element> propertyList = element.elements("property");
            List<String> refs = new ArrayList<>();
            PropertyValues propertyValues = new PropertyValues();
            for(Element e : propertyList) {
                // 名称、类型、
                String name = e.attributeValue("name");
                String type = e.attributeValue("type");
                String value = e.attributeValue("value");
                String ref = e.attributeValue("ref");
                boolean isRef = false;
                String pv = "";
                // value有值的时候，说明非引用对象。ref的值不是true或者false，而是指向对象
                if (value != null && !value.equals("")) {
                    isRef = false;
                    pv = value;
                } else {
                    isRef = true;
                    pv = ref;
                    refs.add(ref);
                }
                propertyValues.add(new PropertyValue(name, type, pv, isRef));
            }

            List<Element> argumentValueList  = element.elements("constructor-arg");
            ArgumentValues argumentValues = getArgumentValues(argumentValueList);

            String isLazy_str = element.attributeValue("lazyInit");
            if (isLazy_str != null && "false".equals(isLazy_str)) {
                bd.setLazyInit(false);
            }

            String initMethodName = element.attributeValue("initMethodName");
            if (initMethodName!= null) {
                initMethodName = initMethodName.trim();
                if (initMethodName != null && initMethodName.length()>0) {
                    bd.setInitMethodName(initMethodName);
                }
            }

            try {
                Class<?> clazz = Class.forName(beanClassName);
                bd.setBeanClass(clazz);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String[] refArray = refs.toArray(new String[0]);
            bd.setDependsOn(refArray);
            bd.setPropertyValues(propertyValues);
            bd.setConstructorArgumentValues(argumentValues);
            this.simpleBeanFactory.registryBeanDefinition(beanId, bd);
        }
    }

    private ArgumentValues getArgumentValues(List<Element> propertyList) {
        ArgumentValues argumentValues = new ArgumentValues();
        for (Element element : propertyList) {
            String name = element.attributeValue("name");
            String type = element.attributeValue("type");
            Object value = element.attributeValue("value");
            argumentValues.add(new ArgumentValue(name, value, type));
        }
        return argumentValues;
    }

}
