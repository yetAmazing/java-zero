package info.bonian.beans;

/**
 * set的值需要什么特征？ 参数名称必然需要。因为set的类型可能是不一致的
 * 参数名称是唯一的
 * @description: 需要set的值
 * @author: here
 * @date: 2023/9/20 17:34
 */
public class PropertyValue {

    /**
     * 参数名称 set + 参数名称的首字母大写
     */
    private String name;

    private Object value;

    private String type;

    private boolean isRef;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isRef() {
        return isRef;
    }

    public void setRef(boolean ref) {
        isRef = ref;
    }

    public PropertyValue(String name, String type, Object value, boolean isRef) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.isRef = isRef;
    }
}
