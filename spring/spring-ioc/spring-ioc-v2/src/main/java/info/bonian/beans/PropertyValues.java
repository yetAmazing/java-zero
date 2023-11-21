package info.bonian.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 是所有属性
 * @author: here
 * @date: 2023/9/22 15:47
 */
public class PropertyValues implements ValueList<PropertyValue>{

    private List<PropertyValue> list = new ArrayList<>();

    @Override
    public PropertyValue get(int index) {
        return this.list.get(index);
    }

    @Override
    public void add(PropertyValue propertyValue) {
        this.list.add(propertyValue);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public int count() {
        return this.list.size();
    }
}
