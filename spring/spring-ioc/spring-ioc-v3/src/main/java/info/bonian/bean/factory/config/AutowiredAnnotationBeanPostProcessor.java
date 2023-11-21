package info.bonian.bean.factory.config;

import info.bonian.bean.exception.BeansException;
import info.bonian.bean.factory.annotation.Autowired;

import java.lang.reflect.Field;

/**
 * 是一个处理@autowired注解的处理器
 * @description: 处理autowired
 * @author: here
 * @date: 2023/10/29 01:18
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanFactoryPostProcessor {

    private AutowireCapableBeanFactory beanFactory;

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    /**
     *
     * bean有属性被标记为autowired，获取所有filed，遍历，找出需要autowired的属性，根据其名称，到beanFactory获取该bena
     * 然后用field的set方法，给bean注入autowiredObject
     * @param bean 由spring管理的对象
     * @param beanName bean名称
     * @return 被操作的对象
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        Class<?> clazz = bean.getClass();
        // getFields 与 getDeclaredFields 有什么区别  获取声明的字段

        // getDeclaredFields 获取的是private的；getFields 获取的是所有的
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                String fieldName = field.getName();
                // 获取被注入的对象
                Object autowiredObj = this.beanFactory.getBean(fieldName);
                try {
                    field.setAccessible(true);
                    //
                    field.set(bean, autowiredObj);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
