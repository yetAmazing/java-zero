package info.bonian.beans;

/**
 * @description: 用于获取xml中bean标签的属性
 * @author: here
 * @date: 2023/9/19 19:57
 */
public class BeanDefinition {

    /**
     * bean的名称，唯一标识
     */
    private String id;
    /**
     * bean对应的class
     */
    private String clazz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public BeanDefinition() {
    }

    public BeanDefinition(String id, String clazz) {
        this.id = id;
        this.clazz = clazz;
    }
}
