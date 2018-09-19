package b_anno;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//元注解    表示注解的作用范围
@Target({TYPE,FIELD , METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//@Target({TYPE,FIELD , METHOD})


//@Retention ：指定注解的声明周期
@Retention(value = RetentionPolicy.CLASS)       //字节码级别时有效
//@Retention(value = RetentionPolicy.RUNTIME)   //运行时有效
//@Retention(value = RetentionPolicy.SOURCE)    //源码级别有效

@Documented  // 用于指定被该元 Annotation 修饰的 Annotation 类将被 javadoc 工具提取成文档。


@Inherited   //被它修饰的 Annotation 将具有继承性.如果某个类使用了被 @Inherited 修饰的 Annotation, 则其子类将自动具有该注解

public @interface MyAnnotation_1 {

	String name();
	
	int age() default 20;
}
