package liuwei03.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtils {
	public static void initFrame(JFrame frame,int width,int height) {
			//获取工具类
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			//获取屏幕分辨率
			Dimension screenSize = toolkit.getScreenSize();
			//x
			int x = (int) screenSize.getWidth();
			//y
			int y = (int) screenSize.getHeight();
			//设置窗口的尺寸
			frame.setBounds((x-width)/2, (y-height)/2, width, height);
			//可视化
			frame.setVisible(true);
			//关闭窗口
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
}

