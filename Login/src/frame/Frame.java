package frame;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import dao.IAdminDao;
import dao.impl.AdminDao;
import entity.Admin;
import util.FrameUtils;

public class Frame {

	private JFrame frame;
	private IAdminDao dao = new AdminDao();

	public Frame() {
		frame = new JFrame("注册登录");
		login();

	}

	public void login() {

		JPanel panel = new JPanel();
		frame.add(panel);
		// 布局管理
		panel.setLayout(null);

		/******** 用户名模块 *********/

		Label label_userName = new Label("用户名");
		label_userName.setBounds(120, 30, 50, 25);
		TextField filed_userName = new TextField(15);
		filed_userName.setBounds(180, 30, 180, 25);
		panel.add(label_userName);
		panel.add(filed_userName);

		// ********密码模块*********/
		Label label_pwd = new Label("密码");
		label_pwd.setBounds(120, 70, 50, 25);
		JPasswordField filed_pwd = new JPasswordField(15);
		filed_pwd.setBounds(180, 70, 180, 25);
		panel.add(label_pwd);
		panel.add(filed_pwd);

		// ********按钮模块*********/
		JButton reg_buton = new JButton("注册");
		reg_buton.setBounds(150, 120, 60, 30);
		JButton login_button = new JButton("登录");
		login_button.setBounds(225, 120, 60, 30);
		panel.add(reg_buton);
		panel.add(login_button);
		// ******************注册事件 *************************
		class MyListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 需要封装的对象
				Admin admin = new Admin();
				// 获取事件源
				String actionCommand = e.getActionCommand();
				// 获取数据
				String userName = filed_userName.getText();
				String password = new String(filed_pwd.getPassword());
				// 封装数据
				admin.setUserName(userName);
				admin.setPassword(password);
				// 判断
				if ("登录".equals(actionCommand)) {
					Admin a = dao.login(admin);
					if (a != null) {
						JOptionPane.showMessageDialog(panel, "登录成功", "登录", JOptionPane.OK_OPTION);
					} else {
						JOptionPane.showMessageDialog(panel, "账号密码错误，登录失败！", "错误", JOptionPane.OK_OPTION);
					}

				} else if ("注册".equals(actionCommand)) {
					dao.register(admin);
					JOptionPane.showMessageDialog(panel, "注册成功", "注册", JOptionPane.OK_OPTION);
				}

			}

		}
		// 添加事件
		reg_buton.addActionListener(new MyListener());
		login_button.addActionListener(new MyListener());
		// 初始化框架
		FrameUtils.initFrame(frame, 450, 280);

	}

}
