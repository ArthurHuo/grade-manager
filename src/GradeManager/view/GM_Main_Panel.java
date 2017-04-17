package GradeManager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GradeManager.system.GM_MenuEvent;
import GradeManager.system.GM_System;

public class GM_Main_Panel extends JPanel{
	JFrame _main_frame = new JFrame();
	JMenuBar menubar = new JMenuBar();
	JDesktopPane gm_desktop = new JDesktopPane();
	GM_MenuEvent menuevent = new GM_MenuEvent();
	
	public GM_Main_Panel(JFrame main_frame){
		_main_frame = main_frame;
		init_Main_Panel();
	}

	private void init_Main_Panel() {
		this.setLayout(null);
		menuevent.setDeskTop(gm_desktop);
		menuevent.setFrame(_main_frame);
		buildMenuBar();
		buildJDesktop();
		loadBackgroundImage();
		
//		JTabbedPane aaa = new JTabbedPane();
//		this.add(aaa);
//		aaa.setBounds(30, 30, 300, 300);
		
//		JTabbedPane tabbedPane = new JTabbedPane();
//		tabbedPane.addTab("Tab 1", null, null,
//		                  "Does nothing");
//		this.add(tabbedPane);
//		tabbedPane.setBounds(30, 30, 300, 300);
//		tabbedPane.addTab("Tab 2", null, null,
//		                  "Does twice as much nothing");

	}
	
	private void buildJDesktop() {
		this.add(gm_desktop);
		gm_desktop.setBounds(0, 0, 1024, 768);
		}

	public void buildMenuBar(){
		JMenu[] menu = {new JMenu("基本设置"), new JMenu("录入"),new JMenu("查询"), new JMenu("帮助")};
		JMenuItem[] menu_item0 = {new JMenuItem("班级设置"),new JMenuItem("学生管理"),new JMenuItem("科目设置"),new JMenuItem("类别设置"),new JMenuItem("用户管理"),new JMenuItem("退出程序")};
        String[] menu_item0_name = {"sys_grade", "sys_class","sys_subject","sys_examkinds"};
        JMenuItem[] menu_item1 = {new JMenuItem("成绩录入"), new JMenuItem("学生录入"),new JMenuItem("教师录入")};
        String[] menu_item1_name = {"JF_view_student", "JF_view_teacher","JF_view_gradesub"};
        JMenuItem[] menu_item2 = {new JMenuItem("学生查询"),new JMenuItem("成绩查询")};
        String[] menu_item2_name = {"JF_view_query_jbqk", "JF_view_query_grade_mx","JF_view_query_grade_hz"};
        JMenuItem[] menu_item3 = {new JMenuItem("关于作者")};
        String[] menu_item3_name = {"sys_user_modify", "JB_EXIT"};
        
        for (int i = 0; i < menu.length; i++) {
        	menubar.add(menu[i]);
        }
        
//        if(GM_System.authority.equals("stu")){
//        	menu_item0[0].setEnabled(false);
//        }
        
        for (int j = 0; j < menu_item0.length; j++) {
//        	final String EventName1 = menu_item0_name[j];
        	menu_item0[j].addActionListener(menuevent);
//        	menu_item0[j].addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    _MenuBarEvent.setEventName(EventName1);
//                }
//            });
            menu[0].add(menu_item0[j]);
//            if (j == 1) {
//                menu[0].addSeparator();
//            }
        }
        
        for (int j = 0; j < menu_item1.length; j++) {
//        	final String EventName1 = menu_item1_name[j];
        	menu_item1[j].addActionListener(menuevent);
//        	menu_item0[j].addActionListener(_MenuBarEvent);
//        	menu_item0[j].addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    _MenuBarEvent.setEventName(EventName1);
//                }
//            });
            menu[1].add(menu_item1[j]);
//            if (j == 1) {
//                menu[1].addSeparator();
//            }
        }
        for (int j = 0; j < menu_item2.length; j++) {
//        	final String EventName1 = menu_item1_name[j];
        	menu_item2[j].addActionListener(menuevent);
//        	menu_item0[j].addActionListener(_MenuBarEvent);
//        	menu_item0[j].addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    _MenuBarEvent.setEventName(EventName1);
//                }
//            });
            menu[2].add(menu_item2[j]);
//            if (j == 1) {
//                menu[2].addSeparator();
//            }
        }
        for (int j = 0; j < menu_item3.length; j++) {
//        	final String EventName1 = menu_item1_name[j];
        	menu_item3[j].addActionListener(menuevent);
//        	menu_item0[j].addActionListener(_MenuBarEvent);
//        	menu_item0[j].addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    _MenuBarEvent.setEventName(EventName1);
//                }
//            });
            menu[3].add(menu_item3[j]);
//            if (j == 1) {
//                menu[2].addSeparator();
//            }
        }
	}
	
	public JMenuBar getMenuBar(){
		return menubar;
	}
	
	protected void loadBackgroundImage(){
        ImageIcon img_main = new ImageIcon("image/img_main.jpg");
        JLabel lab_img_main = new JLabel(img_main);
        lab_img_main.setBounds(0,0,img_main.getIconWidth(),img_main.getIconHeight());
        gm_desktop.add(lab_img_main,new Integer(Integer.MIN_VALUE));

}

}
