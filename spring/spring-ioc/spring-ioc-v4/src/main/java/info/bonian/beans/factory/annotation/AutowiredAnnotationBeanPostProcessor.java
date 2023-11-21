package info.bonian.beans.factory.annotation;

import info.bonian.beans.factory.BeanFactory;
import info.bonian.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * BeanPostProcessor
 * @description: bean的一种处理器
 * @author: here
 * @date: 2023/11/15 19:23
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private BeanFactory beanFactory;

    /**
     * 1: 获取bean的class
     * 2: 根据class获取所有private属性
     * 3: 循环判断属性是否有autowired注解
     * 4: 如果有，则通过BeanFactory的getBean方法获取bean，set值
     * @param bean
     * @param beanName
     * @return
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Object result = bean;

        // fixme，虽然是一个Object，但是传进来的时候，是一个已经调用构造函数的对象。此处当时没想起来
        Class<?> clz = bean.getClass();
        // 此处有两种，一种是根据名称获取一个属性，一种是获取所有
        Field[] fields = clz.getDeclaredFields();
        if (fields == null) {
            return result;
        }

        for (Field field : fields) {
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                String fileName = field.getName();
                Object autowiredBean = this.getBeanFactory().getBean(fileName);
                try {
                    field.setAccessible(true);
                    field.set(bean, autowiredBean);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private BeanFactory getBeanFactory(){
        return this.beanFactory;
    }
}
