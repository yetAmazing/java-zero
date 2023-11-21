package info.bonian.bean.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 解析构造函数
 * @author: here
 * @date: 2023/10/28 17:32
 */
public class ConstructorArgumentValues {

    private final List<ConstructorArgumentValue> constructorArgumentValueList = new ArrayList<>();

    /**
     * 1: size
     * 2: isEmpty
     */

    public int size() {
        return this.constructorArgumentValueList.size();
    }

    public boolean isEmpty(){
        return this.constructorArgumentValueList.isEmpty();
    }

    public ConstructorArgumentValue[] getConstructorArgumentValues() {
        return this.constructorArgumentValueList.toArray(new ConstructorArgumentValue[constructorArgumentValueList.size()]);
    }

    public List<ConstructorArgumentValue> getAll() {
        return this.constructorArgumentValueList;
    }

    public ConstructorArgumentValue get(int index) {
        if (index <0 || index >= size()) {
            return null;
        }
        return this.constructorArgumentValueList.get(index);
    }

    public ConstructorArgumentValue get(String ConstructorArgumentName) {
        if (ConstructorArgumentName == null || ConstructorArgumentName.equals("")) {
            return null;
        }
        if (size() == 0) {
            return null;
        }
        for (ConstructorArgumentValue cav : getConstructorArgumentValues()) {
            if (cav.getName().equals(ConstructorArgumentName)){
                return cav;
            }
        }
        return null;
    }

    public boolean contains(ConstructorArgumentValue cav) {
        return this.constructorArgumentValueList.contains(cav);
    }

    public boolean contains(String constructorArgumentValueName) {
        ConstructorArgumentValue cav = get(constructorArgumentValueName);
        if (cav != null) {
            return true;
        }
        return false;
    }

    public void remove(String ConstructorArgumentName) {
        ConstructorArgumentValue cav = get(ConstructorArgumentName);
        remove(cav);
    }

    public void remove(ConstructorArgumentValue cav) {
        this.constructorArgumentValueList.remove(cav);
    }

    public void add(ConstructorArgumentValue cav){
        if (cav != null && !contains(cav)) {
            this.constructorArgumentValueList.add(cav);
        }
    }
}
