package info.bonian.context;

import java.util.EventObject;

/**
 * @description: 应用事件
 * @author: here
 * @date: 2023/9/27 13:41
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;
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
