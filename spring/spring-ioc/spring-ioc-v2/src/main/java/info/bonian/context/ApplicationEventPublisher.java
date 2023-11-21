package info.bonian.context;

/**
 * @description: 事件发布接口
 * @author: here
 * @date: 2023/9/27 13:40
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent applicationEvent);
}
