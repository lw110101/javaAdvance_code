 package timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownUtils {

	private static int day=0;
	private static int hour=0;	
	private static int minute=0;
	private static int second=0;
	
	private static boolean dayNotAlready=false;
	private static boolean hourNotAlready=false;
	private static boolean minuteNotAlready=false;
	private static boolean secondNotAlready=false;
	
	public static void main(String[] args) {
		//�����쿪ʼ����ʱ ����һ���º�
		int totalSeconds=30*24*60*60+59;
		//��ʼ��ʱ��
		initTime(totalSeconds);
		//������ʱ������
		new Timer().schedule(new TimerTask() {
			public void run() {
				//��secondNotAlreadyΪtrue,��second>0;
				if(secondNotAlready) {
					startTime();
				}else {
					cancel();    //ֹͣ����
				}
			}	
		}, 0, 1000); 
		
	}
	/**
	 * �������ֵ�ı䶯��
	 * 
	 */
	private static void startTime() {
		
		if(secondNotAlready) {
			if(second>0) {
				second--;    //��
				if(second==0 && !minuteNotAlready) {
					secondNotAlready=false;
				}
			}else {
				if(minute>0) {
					minute--;
					second=59;   //��
					if(minute==0 && !hourNotAlready) {
						minuteNotAlready=false;
					}
				}else {
					if(hour>0) {
						hour--;
						minute=59;
						second=59;   //ʱ
						if(hour==0&&!dayNotAlready) {
							hourNotAlready=false;
						}
					}else {
						if(day>0) {
							day--;
							hour=23;
							minute=59;
							second=59;  //��
							if(day==0) {
								dayNotAlready=false;
								return ;
							}
						}
					}
				}
			}
		}
		System.out.println("�����ֹ���ڻ���:"+day+"��"+hour+"ʱ"+ minute+"��"+second+"��");
	}
	/**
	 * һ����ʼ��ʱ��
	 */
	private static void initTime(int totalSeconds) {
		resetData();
		if(totalSeconds>0) {
			//��ȡ����
			secondNotAlready=true;
			second=totalSeconds;
			if(second>=60) {
				//��ȡ������
				minuteNotAlready=true;
				minute=second/60;
				second=second%60;
				if(minute>=60) {
					//��ȡʱ��
					hourNotAlready=true;
					hour=minute/60;
					minute=minute%60;
					if(hour>24) {
						//��ȡ����
						dayNotAlready=true;
						day=hour/24;
						hour=hour%24;
					} 
				}
			}
				
		}
		System.out.println("��===== ��ʼ��ʽ����:"+day+"��"+hour+"ʱ"+ minute+"��"+second+"��"+"======��");
	}
	//��������
	private static void resetData() {
		day=0;
		hour=0;
		minute=0;
		second=0;
		hourNotAlready=false;
		minuteNotAlready=false;
		secondNotAlready=false;
		dayNotAlready=false;
	}
}
