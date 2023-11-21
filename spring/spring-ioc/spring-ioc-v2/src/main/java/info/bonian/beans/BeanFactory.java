package info.bonian.beans;

public interface BeanFactory {

    Object getBean(String beanName);

    boolean containsBean(String beanName);

    void registerBean(String beanName, Object bean);
}
