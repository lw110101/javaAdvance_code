package utils;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;;

/**
 * ����id����
 * @author hasee ����3:15:11
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)     // ָ��ע��������ʱ����Ч
public @interface Id {

}
