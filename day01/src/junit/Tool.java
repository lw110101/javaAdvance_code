package junit;
//要被测试的类
public class Tool {

	public static int getMax() {
		int a=5,b=3;
		int max=a>b?5:3;
		return max;
	}
	
	public static int getMin() {
		int a=3,b=5;
		int min=a<b?3:5;
		return min;
	}
}
