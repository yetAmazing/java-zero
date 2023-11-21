package info.bonian.bean.factory.annotation;

import java.lang.annotation.*;

/**
 * 修饰成员变量，运行时生效
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
}
