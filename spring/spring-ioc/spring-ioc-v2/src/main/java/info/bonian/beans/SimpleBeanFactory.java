package info.bonian.beans;

import info.bonian.exception.BeanNotExistException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean可以使用的时候创建，也可以添加的时候创建
 * 简单工厂的bean，默认是单例的。但是原型的可以通过返回一个复制体
 * 注意：一定是先构造函数，再set方法。对象不存在如何set进入值。必须newInstance之后才能调用其中的方法
 * earlyBean  只构造，没有set1·
 * @description: 简单bean工厂
 * @author: here
 * @date: 2023/9/20 12:40
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry{
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final List<String> beanNames = new ArrayList<>();
    private final Map<String, Object> earlyBeanMap = new ConcurrentHashMap<>();

    public void refresh() {
        for (String name : beanNames) {
            try {
                getBean(name);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    /**
     * 1: 判断bean在SingletonMap中是否存在
     * 2: 如果存在则直接返回
     * 3：如果不存在，则判断beanDefinition是否存在，如果存在，则夹在加载
     * 解决循环依赖的逻辑
     * @param beanName bean的名称
     * @return bean
     */
    @Override
    public Object getBean(String beanName) {
        Object singletonBean = this.getSingleton(beanName);
        if (singletonBean == null) {
            // 核心在这里，返回的是早期bean
            singletonBean = this.earlyBeanMap.get(beanName);
            if (singletonBean == null) {
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                if (beanDefinition != null) {
                    singletonBean = createBean(beanDefinition);
                    this.registerSingleton(beanName, singletonBean);
                    // 预留beanPostProcess的位置
                    // step1: postProcessBeforeInitialization
                    // step2: afterPropertiesSet
                    // step3: init-method
                    // step4: postProcessAfterInitialization
                }
            }
        }
        if (singletonBean == null) {
            throw new BeanNotExistException("the bean of " + beanName + " is null");
        }
        return singletonBean;
    }

    private Object createBean(BeanDefinition bd) {
        String beanName = bd.getId();
        Class<?> clz;
        try {
            clz = Class.forName(bd.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //
        Object obj = doCreateBean(bd, clz);
        //  注册早期bean
        this.earlyBeanMap.put(beanName, obj);
        handleProperty(bd, clz, obj);
        return obj;
    }

    /**
     * 创建早期bean，只有构造方法，构造方法需要用到的bean，默认是
     * @param bd
     * @param clz
     * @return
     */
    private Object doCreateBean(BeanDefinition bd, Class<?> clz) {
        Constructor<?> con;
        Object earlyBean;
        ArgumentValues argumentValues = bd.getConstructorArgumentValues();
        Class<?>[] paramTypes =  new Class[argumentValues.count()];
        Object[] paramValues = new Object[argumentValues.count()];
        for (int i=0; i< argumentValues.count(); i++) {
            ArgumentValue argumentValue = argumentValues.get(i);
            String type = argumentValue.getType();
            if ("java.lang.Integer".equals(type) || "Integer".equals(type)) {
                paramTypes[i] = Integer.class;
                paramValues[i] = (Integer) argumentValue.getValue();
            } else if ("int".equals(type)) {
                paramTypes[i] = int.class;
                paramValues[i] = Integer.valueOf(argumentValue.getValue().toString()).intValue();
            } else if ("java.lang.String".equals(type) || "String".equals(type)) {
                paramTypes[i] = String.class;
                paramValues[i] = (String) argumentValue.getValue();
            } else {
                paramTypes[i] = String.class;
                paramValues[i] = (String) argumentValue.getValue();
            }
        }
        try {
            con = clz.getConstructor(paramTypes);
            // 只调用了构造函数，没有调用set方法的早期bean
            earlyBean = con.newInstance(paramValues);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return earlyBean;
    }

    private void handleProperty(BeanDefinition bd, Class<?> clz, Object earlyBean) {
        Object bean = null;
        PropertyValues propertyValues = bd.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }
        for (int i = 0; i < propertyValues.count(); i++) {
            PropertyValue propertyValue = propertyValues.get(i);
            String name = propertyValue.getName();
            String type = propertyValue.getType();
            Object value = propertyValue.getValue();
            String methodName = "set" + name.substring(0,1).toUpperCase() +  name.substring(1);
            Class<?>[] paramTypes = new Class[1];
            Object[] paramValues = new Object[1];
            if (!propertyValue.isRef()) {
                if ("java.lang.Integer".equals(type) || "integer".equals(type)) {
                    paramTypes[0] = Integer.class;
                    paramValues[0] = Integer.valueOf(value.toString());
                } else if ("int".equals(type)) {
                    paramTypes[0] = int.class;
                    paramValues[0] = Integer.valueOf(value.toString());
                } else if ("java.lang.String".equals(type) || "string".equals(type)) {
                    paramTypes[0] = String.class;
                    paramValues[0] = String.valueOf(value);
                } else {
                    paramTypes[0] = String.class;
                    paramValues[0] = String.valueOf(value);
                }
            } else {
                try {
                    paramTypes[0] = Class.forName(type);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                // 为什么不是早期bean
                paramValues[0] = getBean((String) value);
            }
            try {
                Method method = clz.getMethod(methodName, paramTypes);
                bean = method.invoke(earlyBean, paramValues);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.beanNames.contains(beanName);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        super.registerSingleton(beanName, obj);
        this.beanNames.add(beanName);
    }

    @Override
    public void registryBeanDefinition(String name, BeanDefinition beanDefinition) {
        // 先判断原来是否存在
        if (this.beanDefinitionMap.containsKey(name)) {
            this.removeBeanDefinition(name);
        }
        //  判断BeanDefinition是否initLazy，如果不是，则创建bean
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanNames.add(name);
        // 这个地方出问题了。不能这么早去加载，因为beanDefinition还没放入其中
        if (!beanDefinition.isLazyInit()) {
            Object bean  = createBean(beanDefinition);
            this.registerSingleton(name, bean);
        }
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanNames.remove(name);
        super.removeSingleton(name);
    }

}
