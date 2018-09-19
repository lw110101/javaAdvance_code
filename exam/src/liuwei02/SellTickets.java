package liuwei02;
//Runnable 实现线程

public class SellTickets implements Runnable {

	int num = 100;

	public void run() {
		while (true) {
			synchronized (SellTickets.class) {
				if (num > 0) {
					System.out.println(Thread.currentThread().getName() + "卖出了第" + num + "张票");
					num--;

				} else {
					System.out.println("售罄了！");
					break;
				}
			}
		}
	}

	public static void main(String[] args) {

		SellTickets s = new SellTickets();
		Thread thread1 = new Thread(s, "窗口1");
		Thread thread2 = new Thread(s, "窗口2");
		Thread thread3 = new Thread(s, "窗口3");
		thread1.start();
		thread2.start();
		thread3.start();

	}
}
