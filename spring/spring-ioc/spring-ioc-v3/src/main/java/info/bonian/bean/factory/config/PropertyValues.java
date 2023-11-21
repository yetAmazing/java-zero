package info.bonian.bean.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 1: 判断是否为空
 * 2: 获取所有值
 * 3: 根据下标获取值
 * 4: 根据名字获取值
 * 5: 根据name判断是否包含
 * 6: 是否包含某个value
 * 7: 添加一个值
 * 8: 根据name移除值
 * 9: size
 * @description: 用于set的属性只
 * @author: here
 * @date: 2023/10/30 11:24
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public int size(){
        return this.propertyValues.size();
    }

    public PropertyValue get(int index){
        return this.propertyValues.get(index);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValues.toArray(new PropertyValue[this.propertyValues.size()]);
    }

    public PropertyValue getByPropertyName(String propertyName){
        if (propertyName == null || propertyName.equals("")) {
            return null;
        }
        for (PropertyValue propertyValue : this.propertyValues) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return this.propertyValues.isEmpty();
    }

    public boolean contains(PropertyValue propertyValue){
        if (propertyValue == null) {
            return false;
        }
        return this.propertyValues.contains(propertyValue);
    }

    public void add(PropertyValue propertyValue) {
        if (propertyValue != null && !contains(propertyValue)) {
            this.propertyValues.add(propertyValue);
        }
    }

    public void remove(PropertyValue propertyValue) {
        if (contains(propertyValue)) {
            this.propertyValues.remove(propertyValue);
        }
    }

    public PropertyValue get(String propertyValueName) {
        if (propertyValueName == null || propertyValueName.equals("") || propertyValues.size() == 0) {
            return null;
        }
        for (PropertyValue propertyValue : propertyValues) {
            if (propertyValue.getName().equals(propertyValueName)) {
                return propertyValue;
            }
        }
        return null;
    }

    public void remove(String propertyValueName) {
        PropertyValue propertyValue = get(propertyValueName);
        if (propertyValue != null) {
            this.propertyValues.remove(propertyValue);
        }
    }
}
