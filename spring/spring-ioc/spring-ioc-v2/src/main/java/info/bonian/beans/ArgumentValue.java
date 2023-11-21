package info.bonian.beans;

/**
 * 构造函数需要什么？ 参数类型，参数的值，参数的名称
 * @description: 构造函数的参数
 * @author: here
 * @date: 2023/9/20 17:03
 */
public class ArgumentValue {

    // 属性名
    private String name;
    // 值
    private Object value;
    // 类型
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArgumentValue(String name, Object value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
}
