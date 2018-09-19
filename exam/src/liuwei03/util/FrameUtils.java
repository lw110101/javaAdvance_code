package liuwei03.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtils {
	public static void initFrame(JFrame frame,int width,int height) {
			//��ȡ������
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			//��ȡ��Ļ�ֱ���
			Dimension screenSize = toolkit.getScreenSize();
			//x
			int x = (int) screenSize.getWidth();
			//y
			int y = (int) screenSize.getHeight();
			//���ô��ڵĳߴ�
			frame.setBounds((x-width)/2, (y-height)/2, width, height);
			//���ӻ�
			frame.setVisible(true);
			//�رմ���
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
}

