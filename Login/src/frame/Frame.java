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
		frame = new JFrame("ע���¼");
		login();

	}

	public void login() {

		JPanel panel = new JPanel();
		frame.add(panel);
		// ���ֹ���
		panel.setLayout(null);

		/******** �û���ģ�� *********/

		Label label_userName = new Label("�û���");
		label_userName.setBounds(120, 30, 50, 25);
		TextField filed_userName = new TextField(15);
		filed_userName.setBounds(180, 30, 180, 25);
		panel.add(label_userName);
		panel.add(filed_userName);

		// ********����ģ��*********/
		Label label_pwd = new Label("����");
		label_pwd.setBounds(120, 70, 50, 25);
		JPasswordField filed_pwd = new JPasswordField(15);
		filed_pwd.setBounds(180, 70, 180, 25);
		panel.add(label_pwd);
		panel.add(filed_pwd);

		// ********��ťģ��*********/
		JButton reg_buton = new JButton("ע��");
		reg_buton.setBounds(150, 120, 60, 30);
		JButton login_button = new JButton("��¼");
		login_button.setBounds(225, 120, 60, 30);
		panel.add(reg_buton);
		panel.add(login_button);
		// ******************ע���¼� *************************
		class MyListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��Ҫ��װ�Ķ���
				Admin admin = new Admin();
				// ��ȡ�¼�Դ
				String actionCommand = e.getActionCommand();
				// ��ȡ����
				String userName = filed_userName.getText();
				String password = new String(filed_pwd.getPassword());
				// ��װ����
				admin.setUserName(userName);
				admin.setPassword(password);
				// �ж�
				if ("��¼".equals(actionCommand)) {
					Admin a = dao.login(admin);
					if (a != null) {
						JOptionPane.showMessageDialog(panel, "��¼�ɹ�", "��¼", JOptionPane.OK_OPTION);
					} else {
						JOptionPane.showMessageDialog(panel, "�˺�������󣬵�¼ʧ�ܣ�", "����", JOptionPane.OK_OPTION);
					}

				} else if ("ע��".equals(actionCommand)) {
					dao.register(admin);
					JOptionPane.showMessageDialog(panel, "ע��ɹ�", "ע��", JOptionPane.OK_OPTION);
				}

			}

		}
		// ����¼�
		reg_buton.addActionListener(new MyListener());
		login_button.addActionListener(new MyListener());
		// ��ʼ�����
		FrameUtils.initFrame(frame, 450, 280);

	}

}
