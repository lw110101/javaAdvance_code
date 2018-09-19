package timer;

public class ThreadDemo extends Thread{
	
	public ThreadDemo(String name) {
		super(name);
		
	}
	@Override
	public void run() {
		int count = 0;
		int i, j;
		if (currentThread().getName().equals("线程A")) {
			for (i = 2; i <= 1000; i++) {
				for (j = 2; j < i; j++) {
					if (i % j == 0) {
						break;
					}	
				}
				if (j >= i) {
					count++;
				}
			}
		} else if (currentThread().getName().equals("线程B")) {
			for (i = 1000; i <= 2000; i++) {
				for (j = 2; j < i; j++) {
					if (i % j == 0) {
						break;
					}
				}
				if (j >= i) {
					count++;
				}
			}
		}
		System.out.println("质数个数是：" + count);
	}
	public static void main(String[] args) {
		Thread t1=new ThreadDemo("线程A");
		Thread t2=new ThreadDemo("线程B");
		t1.start();
		t2.start();
	}

}
