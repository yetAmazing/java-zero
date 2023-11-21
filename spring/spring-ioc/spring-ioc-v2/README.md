spring-ioc-v2  预期实现的功能


v1具有的功能

1: 单例bean  ok
2: 解决循环依赖 (通过set方法注入) ok
3: 注解bean 
4：有参构造方法，set方法 ok
5：SimpleBeanFactory， Spring事件event，线程安全 ok
6: 关于异常，以及功能的划分

线路1: 
创建xml配置bean
Resource ClassPathXmlReader 读取xml文件，获取其中的Element
BeanDefinitionRegistry    解析element，获取其中的BeanDefinition，以及参数
BeanFactory    遍历BeanDefinition，创建bean

单例对应的是原型模式（每次取的时候，创建一定的复制），或者池化技术（对于创建时比较浪费资源）

单例与原型的区别：
Spring获取该bean的时候，创建一个新的
构造函数、set参数，可能是bean，也可能是基础对象


1 pojo
ArgumentValue  构造函数的参数
ArgumentValues
PropertyValue set方法的值
PropertyValues
BeanDefinition 原始bean定义

2 interface
Resource 【interface】 继承Iterator定义了资源迭代器
BeanDefinitionRegistry 【interface】 定义了管理BeanDefinition的方法
BeanFactory 【interface】 定义了管理bean的方法

3 class 
ClassPathXmlResource  实现 Resource 读取xml文件中的标签
XmlBeanDefinitionReader  将xml文件中的标签解析为BeanDefinition
DefaultSingletonBeanRegistry 实现了BeanFactory、SingletonBeanRegistry单例bean注册
ApplicationContext   实现了BeanFactory、springEvent、SpringListener等


spring三级缓存是哪三级，分别都是什么文件
spring如何解决循环依赖的
spring的bean
ApplicationContext 与 simpleBeanFactory有何区别
refresh















