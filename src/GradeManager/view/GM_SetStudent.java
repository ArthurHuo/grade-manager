package GradeManager.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import GradeManager.system.GM_ConnectAccess;
import GradeManager.system.GM_System;

public class GM_SetStudent extends JInternalFrame {
	public JFrame _main_frame = null;
	GM_ConnectAccess cona = new GM_ConnectAccess();
	JPanel panel_main = new JPanel();
	JTable table_result = new JTable();
	JScrollPane spanel_result = new JScrollPane();
	JButton bt_add = new JButton("添加");
	JButton bt_modify = new JButton("修改");
	JButton bt_del = new JButton("删除");
	JButton bt_quit = new JButton("退出");
	int select_classid = 0;
	
	public GM_SetStudent() {
		
		init_SetUser();
		
	}
	
	public void setFrame(JFrame _main_frame){
    	this._main_frame = _main_frame;
    }

	public void init_Classid(){
		final JDialog dag_stu = new JDialog(_main_frame);
    	JPanel pan_stu = new JPanel();
    	JLabel lab_grade = new JLabel("请输入年级：");
    	JLabel lab_class = new JLabel("请输入班级：");
    	final JTextField stu_grade = new JTextField();
    	final JTextField stu_class = new JTextField();
    	JButton btn_ok = new JButton("管理");
    	JButton btn_canel = new JButton("取消");
    	
    	dag_stu.add(pan_stu);
    	pan_stu.setLayout(null);
    	
    	pan_stu.add(lab_grade);
    	lab_grade.setBounds(40, 20, 80, 20);
    	
    	pan_stu.add(lab_class);
    	lab_class.setBounds(40, 60, 80, 20);
    	
    	pan_stu.add(stu_grade);
    	stu_grade.setBounds(120, 20, 100, 20);
    	
    	pan_stu.add(stu_class);
    	stu_class.setBounds(120, 60, 100, 20);
    	
    	pan_stu.add(btn_ok);
    	btn_ok.setBounds(50, 110, 80, 30);
    	
    	pan_stu.add(btn_canel);
    	btn_canel.setBounds(160, 110, 80, 30);
    	
    	btn_ok.addMouseListener(new MouseAdapter(){
    			public void mouseClicked(MouseEvent e) {
    			String in_grade = String.valueOf(stu_grade.getText());
    			String in_class = String.valueOf(stu_class.getText());
    			Statement stmt = null;
    			ResultSet rs = null;
    			String sql_select_classid = "select classid from class where grade = '"+in_grade+"' and class = '"+in_class+"'";
    			cona.getCon();
    			
    			try {
    				stmt = cona.conection.createStatement();
    				rs = stmt.executeQuery(sql_select_classid);
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			
    			try {
    				while(rs.next()){
    					select_classid = Integer.valueOf(rs.getString(1));
    					}
    				if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					cona.closeCon();
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			if(select_classid ==0){
    				JOptionPane.showMessageDialog(null, "此班级不存在，请重新输入！","提示",JOptionPane.OK_OPTION);
    				stu_grade.setText("");
    				stu_class.setText("");
    				stu_grade.requestFocus();
    			}else{
    				dag_stu.dispose();
    			}
    		}
    	});
    	
    	btn_canel.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    		dag_stu.dispose();
    		}
    	});
    	
    	dag_stu.setResizable(false);
    	dag_stu.setTitle("学生管理");
    	dag_stu.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2,_main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2,300,200);
    	dag_stu.setModal(true);
    	dag_stu.setVisible(true);
	}
	
	public void showFrame(){
		if(select_classid != 0){
			this.show();
			buildTable();
		}
	}
	
	public void init_SetUser() {

		this.setSize(500,400);
        this.setClosable(true);
        
        this.add(panel_main);
        panel_main.setLayout(null);
        panel_main.add(spanel_result);
        spanel_result.getViewport().add(table_result);
        spanel_result.setBounds(0, 0, this.getWidth(),this.getHeight()*4/5);
        table_result.setBounds(0, 0,spanel_result.getWidth(), spanel_result.getHeight());
        
        panel_main.add(bt_add);
        bt_add.setBounds(25, 325, 80, 30);
        
        panel_main.add(bt_modify);
        bt_modify.setBounds(140, 325, 80, 30);
        
        panel_main.add(bt_del);
        bt_del.setBounds(255, 325, 80, 30);
        
        panel_main.add(bt_quit);
        bt_quit.setBounds(370, 325, 80, 30);
        
        bt_del.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
//        		act_Del(e);
        	}
        });
        
        bt_modify.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
//        		act_Modify(e);
        	}
        });
        
        bt_add.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
//        		act_Add(e);
        	}
        });
        
        bt_quit.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		act_Quit(e);
        	}
        });
        
        
      
//        buildTable();
		
	}
	
	public void buildTable(){
		String[] name = {"学号","密码","姓名","性别"};
        String sql_select_class = "select sid,spwd,sname,ssex from student_"+select_classid;
        Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		Vector vname = new Vector();
        for (int i = 0 ; i < name.length ; i++){
            vname.addElement(name[i]);
        }
        DefaultTableModel tableModel = new DefaultTableModel(vname,0);
		
		cona.getCon();
		try {
			stmt = cona.conection.createStatement();
			rs = stmt.executeQuery(sql_select_class);
			rsmd = rs.getMetaData();
			while(rs.next()){
				Vector vdata = new Vector();
				for ( int i = 1 ; i <= rsmd.getColumnCount() ; i ++){
					vdata.addElement(rs.getObject(i));
				}
				tableModel.addRow(vdata);
			}
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			cona.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table_result.setModel(tableModel);
		table_result.setRowHeight(24);
	}
	
	public void act_Quit(MouseEvent e) {
        DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null,"是否退出用户管理?","系统提示",
                                   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                   null,new String[]  {"是","否"},"否");
        if (result == JOptionPane.YES_OPTION) {
           manger.closeFrame(this);
        }
    }

}
