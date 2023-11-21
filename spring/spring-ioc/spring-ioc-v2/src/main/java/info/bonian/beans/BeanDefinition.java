package info.bonian.beans;

/**
 * @description: 获取的信息
 * @author: here
 * @date: 2023/9/20 16:44
 */
public class BeanDefinition {
    private final static String SCOPE_SINGLETON = "singleton";
    private final static String SCOPE_PROTOTYPE = "prototype";

    /**
     * 是添加bean的时候加载，还是使用bean的时候加载
     */
    private boolean lazyInit = true;
    /**
     * bean之间的依赖关系
     */
    private String[] dependsOn;
    /**
     * 构造函数
     */
    private ArgumentValues constructorArgumentValues;

    /**
     * set方法
     */
    private PropertyValues propertyValues;

    /**
     * 初始化方法
     */
    private String initMethodName;

    /**
     * 对应的bean的class
     */
    private volatile Class<?> beanClass;

    private String id;
    private String className;

    /**
     * 单例还是原型
     * 原型：每次使用的时候，复制出去一个
     * 单例：每次使用，都是同一个
     * 多例：类似池化思想
     */
    private String scope = SCOPE_SINGLETON;

    public BeanDefinition(String id, String className) {
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

    public ArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public void setConstructorArgumentValues(ArgumentValues constructorArgumentValues) {
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
        return beanClass;
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
}
