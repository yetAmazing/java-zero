package info.bonian.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: i出
 * @author: here
 * @date: 2023/9/22 15:54
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{

    private Map<String, Object> singletonBeans = new ConcurrentHashMap<>();
    private List<String> beanNames = new ArrayList<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonBeans){
            this.singletonBeans.put(beanName, singletonObject);
            this.beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonBeans.get(beanName);
    }

    @Override
    public boolean containsSingleton(String name) {
        return this.beanNames.contains(name);
    }

    @Override
    public String[] getSingletonNames() {
        return new String[0];
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletonBeans){
            this.singletonBeans.remove(beanName);
            this.beanNames.remove(beanName);
        }
    }
}
