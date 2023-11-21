package info.bonian.bean.factory.config;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/28 17:32
 */
public class BeanDefinition {

    private final static String SCOPE_SINGLETON = "singleton";
    private final static String SCOPE_PROTOTYPE = "prototype";

    private String id;
    private String className;
    private boolean lazyInit;
    private String[] dependOn;
    private String initMethodName;
    private volatile Object beanClass;
    private PropertyValues propertyValues;
    private ConstructorArgumentValues constructorArgumentValues;
    private String scope = SCOPE_SINGLETON;

    public BeanDefinition (String id, String className) {
        this.id = id;
        this.className = className;
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

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String[] getDependOn() {
        return dependOn;
    }

    public void setDependOn(String[] dependOn) {
        this.dependOn = dependOn;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public Object getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Object beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues argumentValues) {
        this.propertyValues = argumentValues;
    }

    public ConstructorArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public void setConstructorArgumentValues(ConstructorArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

