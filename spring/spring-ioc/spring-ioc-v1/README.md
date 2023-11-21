1 实现的功能是什么样的

SpringIOC其实就是对bean进行管理
获取需要注册的bean，创建bean，然后使用这个bean做demo演示
原本在使用的地方需要new，现在可以直接通过BeanFactory或者ApplicationContext中获取


bean定义  service
需要配置的bean  beans.xml
解析xml文件   ClassPathXmlResource  Resource Iterator
解析BeanDefinition    XmlBeanDefinitionReader
存储Bean      BeanFactory
丰富的beanFactory  ApplicationContext
样例 