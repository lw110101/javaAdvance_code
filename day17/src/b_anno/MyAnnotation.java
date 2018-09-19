package b_anno;

public @interface MyAnnotation {

	/*//不带默认值的
	String name();
	
	//带默认值的
	int age() default 20;
	
	//数组
	String[] id();
	*/
	//默认名称的注解
	String value();
}
