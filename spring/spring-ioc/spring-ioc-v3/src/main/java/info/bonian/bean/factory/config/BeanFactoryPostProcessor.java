package info.bonian.bean.factory.config;

import info.bonian.bean.exception.BeansException;

/**
 * @description: 是bean创建完成前的处理器
 * @author: here
 * @date: 2023/10/29 01:04
 */
public interface BeanFactoryPostProcessor {
    /**
     * bean 初始化之前调用的方法
     * @param bean 豆子
     * @param beanName bean名称
     * @return 执行结果
     * @throws BeansException 异常
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * bean 初始化之后调用的方法
     * @param bean 对象
     * @param beanName bean名称
     * @return 执行结果
     * @throws BeansException 异常
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;


}
