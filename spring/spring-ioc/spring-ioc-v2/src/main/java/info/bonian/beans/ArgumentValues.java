package info.bonian.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 构造函数的所有参数
 * @author: here
 * @date: 2023/9/20 17:03
 */
public class ArgumentValues implements ValueList<ArgumentValue>{

    private List<ArgumentValue> list = new ArrayList<>();

    @Override
    public ArgumentValue get(int index) {
        return this.list.get(index);
    }

    @Override
    public void add(ArgumentValue argumentValue) {
        this.list.add(argumentValue);
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
