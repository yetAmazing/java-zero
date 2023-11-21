package info.bonian.bean.factory;

public interface BeanFactory {

    Object getBean(String name);

//    container 容器  contains 包含  contain
    boolean containsBean(String name);

    Class<?> getType(String name);

    // 是否单例模式
    boolean isSingleton(String name);

    // 判断是否原型模式
    boolean isPrototype(String name);
}
