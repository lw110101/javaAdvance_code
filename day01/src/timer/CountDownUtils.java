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
		//从哪天开始倒计时 比如一个月后
		int totalSeconds=30*24*60*60+59;
		//初始化时间
		initTime(totalSeconds);
		//创建定时器任务
		new Timer().schedule(new TimerTask() {
			public void run() {
				//当secondNotAlready为true,即second>0;
				if(secondNotAlready) {
					startTime();
				}else {
					cancel();    //停止任务
				}
			}	
		}, 0, 1000); 
		
	}
	/**
	 * 计算各个值的变动：
	 * 
	 */
	private static void startTime() {
		
		if(secondNotAlready) {
			if(second>0) {
				second--;    //秒
				if(second==0 && !minuteNotAlready) {
					secondNotAlready=false;
				}
			}else {
				if(minute>0) {
					minute--;
					second=59;   //分
					if(minute==0 && !hourNotAlready) {
						minuteNotAlready=false;
					}
				}else {
					if(hour>0) {
						hour--;
						minute=59;
						second=59;   //时
						if(hour==0&&!dayNotAlready) {
							hourNotAlready=false;
						}
					}else {
						if(day>0) {
							day--;
							hour=23;
							minute=59;
							second=59;  //天
							if(day==0) {
								dayNotAlready=false;
								return ;
							}
						}
					}
				}
			}
		}
		System.out.println("距离截止日期还有:"+day+"天"+hour+"时"+ minute+"分"+second+"秒");
	}
	/**
	 * 一：初始化时间
	 */
	private static void initTime(int totalSeconds) {
		resetData();
		if(totalSeconds>0) {
			//获取秒数
			secondNotAlready=true;
			second=totalSeconds;
			if(second>=60) {
				//获取分钟数
				minuteNotAlready=true;
				minute=second/60;
				second=second%60;
				if(minute>=60) {
					//获取时数
					hourNotAlready=true;
					hour=minute/60;
					minute=minute%60;
					if(hour>24) {
						//获取天数
						dayNotAlready=true;
						day=hour/24;
						hour=hour%24;
					} 
				}
			}
				
		}
		System.out.println("《===== 初始格式化后:"+day+"天"+hour+"时"+ minute+"分"+second+"秒"+"======》");
	}
	//重置数据
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
