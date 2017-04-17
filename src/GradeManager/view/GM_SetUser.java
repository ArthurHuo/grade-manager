package GradeManager.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import GradeManager.system.GM_ConnectAccess;

public class GM_SetUser extends JInternalFrame {
	JFrame _main_frame = null;
	GM_ConnectAccess cona = new GM_ConnectAccess();
	JPanel panel_main = new JPanel();
	JTable table_result = new JTable();
	JScrollPane spanel_result = new JScrollPane();
	JButton bt_add = new JButton("添加");
	JButton bt_modify = new JButton("修改");
	JButton bt_del = new JButton("删除");
	JButton bt_quit = new JButton("退出");
	
	public GM_SetUser(){
		init_SetUser();
	}
	
	public void setFrame(JFrame _main_frame){
    	this._main_frame = _main_frame;
    }
	
	public void init_SetUser(){
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
        		act_Del(e);
        	}
        });
        
        bt_modify.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		act_Modify(e);
        	}
        });
        
        bt_add.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		act_Add(e);
        	}
        });
        
        bt_quit.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		act_Quit(e);
        	}
        });
      
        buildTable();
	
	}
	
	public void buildTable(){
		String[] name = {"用户名","密码","管理员"};
        String sql_select_class = "select * from user_tch";
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
	
	public void act_Add(MouseEvent e){
    	final JDialog dag_add = new JDialog(_main_frame);
    	JPanel pan_add = new JPanel();
    	JLabel lab_userid = new JLabel("请输入用户名：");
    	JLabel lab_userpwd = new JLabel("请输入密码：");
    	final JTextField add_userid = new JTextField();
    	final JTextField add_userpwd = new JTextField();
    	JButton btn_ok = new JButton("添加");
    	JButton btn_canel = new JButton("取消");
    	ButtonGroup buttongroup = new ButtonGroup();
    	final JRadioButton button_admin = new JRadioButton("管理员");
    	JRadioButton button_tch = new JRadioButton("教师",true);
    	
    	buttongroup.add(button_tch);
		buttongroup.add(button_admin);
		pan_add.add(button_tch,0);
		button_tch.setBounds(60, 70, 80, 20);
		pan_add.add(button_admin,0);
		button_admin.setBounds(140, 70, 80, 20);
    	
    	dag_add.add(pan_add);
    	pan_add.setLayout(null);
    	
    	pan_add.add(lab_userid);
    	lab_userid.setBounds(40, 5, 110, 20);
    	
    	pan_add.add(lab_userpwd);
    	lab_userpwd.setBounds(40, 35, 100, 20);
    	
    	pan_add.add(add_userid);
    	add_userid.setBounds(150, 5, 100, 20);
    	
    	pan_add.add(add_userpwd);
    	add_userpwd.setBounds(150, 35, 100, 20);
    	
    	pan_add.add(btn_ok);
    	btn_ok.setBounds(50, 110, 80, 30);
    	
    	pan_add.add(btn_canel);
    	btn_canel.setBounds(160, 110, 80, 30);
    	
    	btn_ok.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			boolean isadmin = false;
    			if(button_admin.isSelected()){
    				isadmin = true;
    			}
    			String in_userid = String.valueOf(add_userid.getText());
    			String in_userpwd = String.valueOf(add_userpwd.getText());
    			String sql_insert = "insert into user_tch (userid,userpwd,admin) values ('"+in_userid+"','"+in_userpwd+"',"+isadmin+");";

    			cona.getCon();
				try {
					cona.conection.createStatement().execute(sql_insert);
					cona.closeCon();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				buildTable();
				dag_add.dispose();
				
    		}
    	});
    	
    	btn_canel.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    		dag_add.dispose();
    		}
    	});
    	
    	dag_add.setResizable(false);
    	dag_add.setTitle("添加新用户");
    	dag_add.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2, _main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2, 300, 200);
    	dag_add.setModal(true);
    	dag_add.setVisible(true);
    }
	
	public void act_Modify(MouseEvent e){
		final JDialog dag_modify = new JDialog(_main_frame);
    	JPanel pan_modify = new JPanel();
    	JLabel lab_userid = new JLabel("要修改的用户名：");
    	JLabel lab_userpwd = new JLabel("请输入新密码：");
    	final JTextField mdy_userid = new JTextField();
    	final JTextField mdy_userpwd = new JTextField();
    	JButton btn_ok = new JButton("修改");
    	JButton btn_canel = new JButton("取消");
    	ButtonGroup buttongroup = new ButtonGroup();
    	final JRadioButton button_admin = new JRadioButton("管理员");
    	JRadioButton button_tch = new JRadioButton("教师",true);
    	
    	buttongroup.add(button_tch);
		buttongroup.add(button_admin);
		pan_modify.add(button_tch,0);
		button_tch.setBounds(60, 70, 80, 20);
		pan_modify.add(button_admin,0);
		button_admin.setBounds(140, 70, 80, 20);
    	
    	dag_modify.add(pan_modify);
    	pan_modify.setLayout(null);
    	
    	pan_modify.add(lab_userid);
    	lab_userid.setBounds(40, 5, 110, 20);
    	
    	pan_modify.add(lab_userpwd);
    	lab_userpwd.setBounds(40, 35, 100, 20);
    	
    	pan_modify.add(mdy_userid);
    	mdy_userid.setBounds(150, 5, 100, 20);
    	
    	pan_modify.add(mdy_userpwd);
    	mdy_userpwd.setBounds(150, 35, 100, 20);
    	
    	pan_modify.add(btn_ok);
    	btn_ok.setBounds(50, 110, 80, 30);
    	
    	pan_modify.add(btn_canel);
    	btn_canel.setBounds(160, 110, 80, 30);
    	
    	btn_ok.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			boolean isadmin = false;
    			if(button_admin.isSelected()){
    				isadmin = true;
    			}
    			String in_userid = String.valueOf(mdy_userid.getText());
    			String in_userpwd = String.valueOf(mdy_userpwd.getText());
    			String sql_modify = "update user_tch set userpwd = '"+in_userpwd+"',admin = "+isadmin+" where userid = '"+in_userid+"';";
    			System.out.println(sql_modify);

				cona.getCon();
				try {
					cona.conection.createStatement().execute(sql_modify);
					cona.closeCon();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				buildTable();
				dag_modify.dispose();
				
    		}
    	});
    	
    	btn_canel.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    		dag_modify.dispose();
    		}
    	});
    	
    	dag_modify.setResizable(false);
    	dag_modify.setTitle("修改用户信息");
    	dag_modify.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2, _main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2, 300, 200);
    	dag_modify.setModal(true);
    	dag_modify.setVisible(true);
    }
	
	public void act_Del(MouseEvent e){
    	final JDialog dag_del = new JDialog(_main_frame);
    	JPanel pan_del = new JPanel();
    	JLabel lab_userid = new JLabel("请输入要删除的用户名：");
    	final JTextField del_userid = new JTextField();
    	JButton btn_ok = new JButton("删除");
    	JButton btn_canel = new JButton("取消");
    	
    	dag_del.add(pan_del);
    	pan_del.setLayout(null);
    	
    	pan_del.add(lab_userid);
    	lab_userid.setBounds(70, 20, 200, 20);
    	
    	pan_del.add(del_userid);
    	del_userid.setBounds(90, 60, 100, 20);
    	
    	pan_del.add(btn_ok);
    	btn_ok.setBounds(50, 110, 80, 30);
    	
    	pan_del.add(btn_canel);
    	btn_canel.setBounds(160, 110, 80, 30);
    	
    	btn_ok.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			String in_classid = String.valueOf(del_userid.getText());
    			String sql_modify = "delete from user_tch where userid = '"+in_classid+"';";
    			
				cona.getCon();
				try {
					
					int result = JOptionPane.showOptionDialog(null,"是否删除此用户","系统提示",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
							null,new String[]  {"是","否"},"否");
					if (result == JOptionPane.YES_OPTION) {
						cona.conection.createStatement().execute(sql_modify);
					}
					
					
					cona.closeCon();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				buildTable();
				dag_del.dispose();
				
    		}
    	});
    	
    	btn_canel.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    		dag_del.dispose();
    		}
    	});
    	
    	dag_del.setResizable(false);
    	dag_del.setTitle("删除用户");
    	dag_del.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2, _main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2, 300, 200);
    	dag_del.setModal(true);
    	dag_del.setVisible(true);
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
