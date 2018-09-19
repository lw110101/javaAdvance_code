package utils;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;;

/**
 * 描述id主键
 * @author hasee 下午3:15:11
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)     // 指定注解在运行时期有效
public @interface Id {

}
