package GradeManager.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GM_Main {
	private JFrame main_frame;
	private GM_Main_Panel main_panel;
	
	public GM_Main(){
		main_frame = new JFrame("学生成绩管理系统 preview by 霍佳彤");
		main_panel = new GM_Main_Panel(main_frame);
		init_GM_Login();
	}

	private void init_GM_Login() {
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		int screensize_x = screensize.width;
		int screensize_y = screensize.height;
		main_frame.setLocation(screensize_x/2-1024/2, screensize_y/2-768/2);
		main_frame.setSize(1024, 768);
		main_frame.setResizable(false);
		
		main_frame.add(main_panel);
		main_frame.setJMenuBar(main_panel.getMenuBar());
	}
	
	public void showFrame(){
		main_frame.setVisible(true);
	}
}
