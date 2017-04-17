package GradeManager.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GradeManager.view.GM_About;
import GradeManager.view.GM_SetGrade;
import GradeManager.view.GM_SetStudent;
import GradeManager.view.GM_SetUser;


public class GM_MenuEvent implements ActionListener{
	public JDesktopPane JDeskTop = null;
	JFrame _main_frame = null;
	
	public void setDeskTop(JDesktopPane deskTop){
        this.JDeskTop = deskTop;
       // JDeskTop.setDesktopManager(desktopManager);
    }
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("班级设置")){
			act_class();
            return;
		}
		
		if(e.getActionCommand().equals("学生管理")){
			act_student();
			return;
		}
		
		if(e.getActionCommand().equals("用户管理")){
			act_user();
			return;
		}
		
		if(e.getActionCommand().equals("退出程序")){
			int result = JOptionPane.showOptionDialog(null,"是否退出成绩管理系统?","系统提示",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
					null,new String[]  {"是","否"},"否");
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		
		if(e.getActionCommand().equals("学生查询")){
			
		}
		
		if(e.getActionCommand().equals("关于作者")){
//			GM_SetGrade jfInternalFrame = new GM_SetGrade();
			GM_About gm_about = new GM_About();
            gm_about.setLocation(this.JDeskTop.getWidth()/2-400/2,this.JDeskTop.getHeight()/2-300/2);
            JDeskTop.add(gm_about);
            gm_about.show();
            gm_about.setTitle("关于作者");
            JDeskTop.getDesktopManager().activateFrame(gm_about);
//			JOptionPane.showMessageDialog(null, "用户名密码错误，请重新输入！","提示",JOptionPane.OK_OPTION);
            return;
		}
		
		
	}
	
	public void act_class(){
		if(GM_System.authority.equals("stu")){
			JOptionPane.showMessageDialog(null, "您无权更改此项设置，请联系管理员！","没有权限",JOptionPane.OK_OPTION);
			return;
		}
		GM_SetGrade gm_setgrade = new GM_SetGrade();
		gm_setgrade.setFrame(_main_frame);
        gm_setgrade.setLocation(this.JDeskTop.getWidth()/2-500/2,this.JDeskTop.getHeight()/2-400/2);
        JDeskTop.add(gm_setgrade);
        gm_setgrade.show();
        gm_setgrade.setTitle("班级信息设置");
        JDeskTop.getDesktopManager().activateFrame(gm_setgrade);
	}
	
	public void act_student(){
		if(!GM_System.authority.equals("admin")){
			JOptionPane.showMessageDialog(null, "您无权更改此项设置，请联系管理员！","没有权限",JOptionPane.OK_OPTION);
			return;
		}
//		final JDialog dag_stu = new JDialog(_main_frame);
//    	JPanel pan_stu = new JPanel();
//    	JLabel lab_grade = new JLabel("请输入年级：");
//    	JLabel lab_class = new JLabel("请输入班级：");
//    	final JTextField stu_grade = new JTextField();
//    	final JTextField stu_class = new JTextField();
//    	JButton btn_ok = new JButton("管理");
//    	JButton btn_canel = new JButton("取消");
//    	
//    	dag_stu.add(pan_stu);
//    	pan_stu.setLayout(null);
//    	
//    	pan_stu.add(lab_grade);
//    	lab_grade.setBounds(40, 20, 80, 20);
//    	
//    	pan_stu.add(lab_class);
//    	lab_class.setBounds(40, 60, 80, 20);
//    	
//    	pan_stu.add(stu_grade);
//    	stu_grade.setBounds(120, 20, 100, 20);
//    	
//    	pan_stu.add(stu_class);
//    	stu_class.setBounds(120, 60, 100, 20);
//    	
//    	pan_stu.add(btn_ok);
//    	btn_ok.setBounds(50, 110, 80, 30);
//    	
//    	pan_stu.add(btn_canel);
//    	btn_canel.setBounds(160, 110, 80, 30);
//    	
//    	btn_ok.addMouseListener(new MouseAdapter(){
//    			public void mouseClicked(MouseEvent e) {
//    			String in_grade = String.valueOf(stu_grade.getText());
//    			String in_class = String.valueOf(stu_class.getText());
//    			GM_SetStudent gm_setstudent = new GM_SetStudent(in_grade,in_class);
//    			gm_setstudent.setFrame(_main_frame);
//    			gm_setstudent.setLocation(1024/2-500/2,768/2-400/2);
//    			GM_MenuEvent.JDeskTop.add(gm_setstudent);
//    			gm_setstudent.show();
//    			gm_setstudent.setTitle("学生管理");
//    			GM_MenuEvent.JDeskTop.getDesktopManager().activateFrame(gm_setstudent);
//				dag_stu.dispose();
//				
//    		}
//    	});
//    	
//    	btn_canel.addMouseListener(new MouseAdapter(){
//    		public void mouseClicked(MouseEvent e) {
//    		dag_stu.dispose();
//    		}
//    	});
//    	
//    	dag_stu.setResizable(false);
//    	dag_stu.setTitle("学生管理");
//    	dag_stu.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2, _main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2, 300, 200);
//    	dag_stu.setModal(true);
//    	dag_stu.setVisible(true);
		GM_SetStudent gm_setstudent = new GM_SetStudent();
		gm_setstudent.setFrame(_main_frame);
		gm_setstudent.init_Classid();
		gm_setstudent.showFrame();
		gm_setstudent.setLocation(this.JDeskTop.getWidth()/2-500/2,this.JDeskTop.getHeight()/2-400/2);
		JDeskTop.add(gm_setstudent);
//		gm_setstudent.show();
		gm_setstudent.setTitle("学生管理");
		JDeskTop.getDesktopManager().activateFrame(gm_setstudent);
	}
	
	public void act_user(){
		if(!GM_System.authority.equals("admin")){
			JOptionPane.showMessageDialog(null, "您无权更改此项设置，请联系管理员！","没有权限",JOptionPane.OK_OPTION);
			return;
		}
		GM_SetUser gm_setuser = new GM_SetUser();
		gm_setuser.setFrame(_main_frame);
		gm_setuser.setLocation(this.JDeskTop.getWidth()/2-500/2,this.JDeskTop.getHeight()/2-400/2);
		JDeskTop.add(gm_setuser);
		gm_setuser.show();
		gm_setuser.setTitle("用户管理");
		JDeskTop.getDesktopManager().activateFrame(gm_setuser);
	}
	
	public void setFrame(JFrame _main_frame) {
		this._main_frame = _main_frame;
		
	}

}
