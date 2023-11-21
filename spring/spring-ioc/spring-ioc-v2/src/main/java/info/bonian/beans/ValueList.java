package info.bonian.beans;

/**
 * 添加
 * 移除
 * @param <T>
 */
public interface ValueList<T> {

    T get(int index);

    void add(T t);

    boolean isEmpty();

    int count();

}
