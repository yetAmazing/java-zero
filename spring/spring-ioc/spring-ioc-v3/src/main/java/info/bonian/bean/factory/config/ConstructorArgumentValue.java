package info.bonian.bean.factory.config;

/**
 * @description: 用于解析构造函数
 * @author: here
 * @date: 2023/10/28 17:32
 */
public class ConstructorArgumentValue {

    private final String name;
    private final String type;
    private final Object value;

    public ConstructorArgumentValue(String name, String type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
