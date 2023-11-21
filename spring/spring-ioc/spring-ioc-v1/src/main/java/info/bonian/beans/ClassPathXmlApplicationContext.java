package info.bonian.beans;

import info.bonian.exception.UnExistBeanException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 其实是一个beanFactory，所以肯定有存储bean的地方
 * @author: here
 * @date: 2023/9/19 20:01
 */
public class ClassPathXmlApplicationContext implements BeanFactory, BeanDefinitionRegistry{

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<String> beanNames = new ArrayList<>();

    public ClassPathXmlApplicationContext(String filename) {
        ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(filename);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(this);
        xmlBeanDefinitionReader.readerXml(classPathXmlResource);
    }

    @Override
    public void removeBeanDefinition(String name) {
        boolean containsBean = this.containsBean(name);
        if (!containsBean) {
            throw new UnExistBeanException(name);
        }
        this.beanNames.remove(name);
        this.beanMap.remove(name);
        this.beanDefinitionMap.remove(name);
    }

    @Override
    public void registryBeanDefinition(BeanDefinition beanDefinition) {
        //  将beanDefinition转换为Object (早期bean)
        try {
            Class clazz = Class.forName(beanDefinition.getClazz());
            // 构造函数，set方法
            Object obj = clazz.getDeclaredConstructor().newInstance();
            this.beanNames.add(beanDefinition.getId());
            this.beanMap.put(beanDefinition.getId(), obj);
            this.beanDefinitionMap.remove(beanDefinition.getId(), beanDefinition);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanNames.contains(name);
    }

    @Override
    public Object getBean(String name) {
        return this.beanMap.get(name);
    }

    @Override
    public boolean containsBean(String name) {
        return this.beanNames.contains(name);
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanMap.get(name).getClass();
    }
}
