package info.bonian.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 注解上一定要有元注解，元注解有四个
 * Documented： 在源文件中保留（source）、在class中保留（class），在运行时保留（runtime）
 * 作用范围：属性，类，接口、注解、（type）
 * 生效时间
 * source（来源、源文件）、resource（资源，用于文件夹，存放东西的地方）
 * 这个注解的作用，是标识的属性是有spring负责注入bean
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {


}
