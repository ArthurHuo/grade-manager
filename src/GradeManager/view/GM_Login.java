package GradeManager.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GM_Login extends JFrame{
	private JFrame login_frame;
	private GM_Login_Panel login_panel;
	
	public GM_Login(){
		login_frame = new JFrame("用户登录--学生成绩管理系统 preview");
		login_panel = new GM_Login_Panel(login_frame);
		init_GM_Login();
	}
	
	private void init_GM_Login(){
		login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		int screensize_x = screensize.width;
		int screensize_y = screensize.height;
		login_frame.setLocation(screensize_x/2-400/2, screensize_y/2-300/2);
		login_frame.setSize(400, 300);
		login_frame.setResizable(false);
		
		login_frame.add(login_panel);
	}
	
	public void showFrame(){
		login_frame.setVisible(true);
	}

}
