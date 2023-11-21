package info.bonian.bean.factory.support;

import info.bonian.bean.factory.config.SingletonBeanRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 默认单例bean的注册
 * @author: here
 * @date: 2023/10/28 17:31
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(10);
    private List<String> beanNames = new ArrayList<String>();
    private Map<String, Set<String>> dependentBeanMap = new ConcurrentHashMap<String, Set<String>>();
    private Map<String, Set<String>> dependenciesForBeanMap = new ConcurrentHashMap<String, Set<String>>();
    public void registrySingleton(String beanName, Object singletonObject) {
        if (singletonObjects.containsKey(beanName)) {
            singletonObjects.remove(beanName);
            beanNames.remove(beanName);
        }
        singletonObjects.put(beanName, singletonObject);
        beanNames.add(beanName);
    }

    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public boolean containsSingleton(String beanName) {
        return singletonObjects.containsKey(beanName);
    }

    public String[] getSingletonNames() {
        return this.beanNames.toArray(new String[0]);
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletonObjects){
            this.singletonObjects.remove(beanName);
            this.beanNames.remove(beanName);
        }
    }

    protected void registerDependentBean(String beanName, String dependentBeanName) {
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans != null && dependentBeans.contains(dependentBeanName)) {
            return;
        }

        synchronized (this.dependentBeanMap) {
            if (dependentBeans == null) {
                dependentBeans = new HashSet<String>();
                this.dependentBeanMap.put(beanName, dependentBeans);
            }
            dependentBeans.add(dependentBeanName);
        }

        synchronized (this.dependenciesForBeanMap) {
            Set<String> dependenciesForBeans = this.dependenciesForBeanMap.get(dependentBeanName);
            if (dependenciesForBeans == null) {
                dependenciesForBeans = new HashSet<String>();
                this.dependenciesForBeanMap.put(dependentBeanName, dependenciesForBeans);
            }
            dependenciesForBeans.add(beanName);
        }
    }

    protected boolean hasDependentBean(String beanName) {
        return this.dependentBeanMap.containsKey(beanName);
    }

    protected String[] getDependenciesForBean(String beanName) {
        Set<String> dependenciesForBeans = dependenciesForBeanMap.get(beanName);
        if (dependenciesForBeans == null) {
            return new String[0];
        }
        return dependenciesForBeans.toArray(new String[0]);
    }

    protected String[] getDependentBean(String beanName) {
        Set<String> dependentBeans = dependentBeanMap.get(beanName);
        if (dependentBeans == null) {
            return new String[0];
        }
        return dependentBeans.toArray(new String[0]);
    }
}
