package liuwei02;
//Runnable ʵ���߳�

public class SellTickets implements Runnable {

	int num = 100;

	public void run() {
		while (true) {
			synchronized (SellTickets.class) {
				if (num > 0) {
					System.out.println(Thread.currentThread().getName() + "�����˵�" + num + "��Ʊ");
					num--;

				} else {
					System.out.println("�����ˣ�");
					break;
				}
			}
		}
	}

	public static void main(String[] args) {

		SellTickets s = new SellTickets();
		Thread thread1 = new Thread(s, "����1");
		Thread thread2 = new Thread(s, "����2");
		Thread thread3 = new Thread(s, "����3");
		thread1.start();
		thread2.start();
		thread3.start();

	}
}
