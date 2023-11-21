package info.bonian.context;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/29 23:25
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent applicationEvent);
}
