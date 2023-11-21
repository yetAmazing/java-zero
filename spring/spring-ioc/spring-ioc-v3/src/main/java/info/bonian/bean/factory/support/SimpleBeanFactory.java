package info.bonian.bean.factory.support;

import info.bonian.bean.exception.NoSuchBeanException;
import info.bonian.bean.factory.BeanFactory;
import info.bonian.bean.factory.config.BeanDefinition;
import info.bonian.bean.factory.config.ConstructorArgumentValue;
import info.bonian.bean.factory.config.ConstructorArgumentValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: here
 * @date: 2023/10/28 17:31
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanDefinitionRegistry, BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    private Map<String, Object> earlySingletonBeanMap = new ConcurrentHashMap<>();


    @Override
    public Object getBean(String beanName) {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            singleton = this.earlySingletonBeanMap.get(beanName);
            if (singleton == null) {
                BeanDefinition beanDefinition = getBeanDefinition(beanName);
                if (beanDefinition == null) {
                    throw new NoSuchBeanException();
                }
                singleton = createBean(beanName, beanDefinition);
                // beanpostprocessor
                // step 1 : postProcessBeforeInitialization
                // step 2 : afterPropertiesSet
                // step 3 : init-method
                // step 4 : postProcessAfterInitialization。
            }
        }
        //
        if (singleton == null) {
            throw new NoSuchBeanException("bean is null");
        }
        return singleton;
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> clz = null;
        Object earlyBeanObject = doCreateBean(beanDefinition);
        this.earlySingletonBeanMap.put(beanName, earlyBeanObject);
        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    // 要看一下源码实现
    private Object doCreateBean(BeanDefinition beanDefinition)  {
        String className = beanDefinition.getClassName();
        Class<?> clz;
        Object bean = null;
        try{
            clz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ConstructorArgumentValues cavs = beanDefinition.getConstructorArgumentValues();
        if (cavs == null && cavs.size() == 0) {
            try {
                bean = clz.getDeclaredConstructor().newInstance();
                return bean;
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

        String cName;
        String cType;
        Object cValue;
        Class<?>[] cParams = new Class<?>[cavs.size()];
        Object[] cValues = new Object[cavs.size()];
        for (int i = 0;i < cavs.size(); i++ ) {
            ConstructorArgumentValue cav = cavs.get(i);
            cName = cav.getName();
            cType = cav.getType();
            cValue = cav.getValue();
            if ("int".equals(cType) || "java.lang.Integer".equals(cType) || "Integer".equals(cType)) {
                cParams[i] = Integer.class;
                cValues[i] = Integer.valueOf(cValue.toString());
            } else if ("string".equals(cType) || "java.lang.String".equals(cType) || "String".equals(cType)) {
                cParams[i] = String.class;
                cValues[i] = cValue.toString();
                // 如果有点，说明是类。如果没点就算是类，
            } else if (cType.contains(".")) {
                //  此时可能是类
                try {
                    Class<?> paramClz = Class.forName(cType);
                    cParams[i] = paramClz;
                    cValues[i] = getBean(cName);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                cParams[i] = String.class;
                cValues[i] = String.valueOf(cValue);
            }
        }
        try{
            Constructor<?> constructor = clz.getDeclaredConstructor(cParams);
            bean = constructor.newInstance(cValues);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public void registryBeanDefinition(String name, BeanDefinition beanDefinition) {
        BeanDefinition existBeanDefinition = this.beanDefinitionMap.get(name);
        if (existBeanDefinition != null) {
            this.beanDefinitionMap.get(name);
        }
        this.beanDefinitionMap.put(name, beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        if (containsBean(beanName)) {
            this.beanDefinitionMap.remove(beanName);
            this.beanDefinitionNames.remove(beanName);
        }
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    private Object createEarlyBean(String beanName, BeanDefinition beanDefinition) {

        return null;
    }
}
