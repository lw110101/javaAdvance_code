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

//Ԫע��    ��ʾע������÷�Χ
@Target({TYPE,FIELD , METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//@Target({TYPE,FIELD , METHOD})


//@Retention ��ָ��ע�����������
@Retention(value = RetentionPolicy.CLASS)       //�ֽ��뼶��ʱ��Ч
//@Retention(value = RetentionPolicy.RUNTIME)   //����ʱ��Ч
//@Retention(value = RetentionPolicy.SOURCE)    //Դ�뼶����Ч

@Documented  // ����ָ������Ԫ Annotation ���ε� Annotation �ཫ�� javadoc ������ȡ���ĵ���


@Inherited   //�������ε� Annotation �����м̳���.���ĳ����ʹ���˱� @Inherited ���ε� Annotation, �������ཫ�Զ����и�ע��

public @interface MyAnnotation_1 {

	String name();
	
	int age() default 20;
}
