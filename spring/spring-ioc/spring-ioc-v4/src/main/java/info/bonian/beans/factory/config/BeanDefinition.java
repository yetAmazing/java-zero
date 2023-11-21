package info.bonian.beans.factory.config;

import java.lang.invoke.VolatileCallSite;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/11/16 00:30
 */
public class BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    private boolean lazyInit = true;

    private String[] dependsOn;

    private ConstructorArgumentValues constructorArgumentValues;

    private PropertyValues propertyValues;

    private String initMethodName;

    private volatile Object beanClass;

    private String id;

    private String className;

    private String scope = SCOPE_SINGLETON;

    public BeanDefinition(String id, String className){
        this.id = id;
        this.className = className;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public ConstructorArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public void setConstructorArgumentValues(ConstructorArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public Class<?> getBeanClass() {
        // todo 记住这种写法
        return (Class<?>) this.beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isSingleton(){
        return SCOPE_SINGLETON.equals(this.getScope());
    }

    public boolean isPrototype(){
        return SCOPE_PROTOTYPE.equals(this.getScope());
    }
}
