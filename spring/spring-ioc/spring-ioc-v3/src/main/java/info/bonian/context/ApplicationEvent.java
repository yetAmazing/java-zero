package info.bonian.context;

import java.util.EventObject;

/**
 * @description: 事件
 * @author: here
 * @date: 2023/10/29 23:25
 */
public class ApplicationEvent extends EventObject {

    private static final long serializationUID = 1L;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
