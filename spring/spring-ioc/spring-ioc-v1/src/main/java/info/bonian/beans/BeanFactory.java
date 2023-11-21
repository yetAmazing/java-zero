package info.bonian.beans;

/**
 * @description: beanFactory, 能够用来获取bean
 * 获取bean、判断是否包含bean，获取bean的类型，判断bean是否单例
 * @author: here
 * @date: 2023/9/19 20:01
 */
public interface BeanFactory {

    Object getBean(String name);

    boolean containsBean(String name);

    Class<?> getType(String name);


}
