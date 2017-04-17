package GradeManager.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import GradeManager.system.GM_ConnectAccess;

public class GM_SetGrade extends JInternalFrame {
//	JDesktopPane jDeskTop = null;
	JFrame _main_frame = null;
	GM_ConnectAccess cona = new GM_ConnectAccess();
	JPanel panel_main = new JPanel();
	JTable table_result = new JTable();
	JScrollPane spanel_result = new JScrollPane();
	JButton bt_add = new JButton("添加");
	JButton bt_modify = new JButton("修改");
	JButton bt_del = new JButton("删除");
	JButton bt_quit = new JButton("退出");

    public GM_SetGrade() {
        try {
            init_SetGrade();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setFrame(JFrame _main_frame){
    	this._main_frame = _main_frame;
    }


	private void init_SetGrade() throws Exception {
        
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
        String[] name = {"班级编号","年级名称","班级名称"};
        String sql_select_class = "select * from class";
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

    
    public void act_Del(MouseEvent e){
    	final JDialog dag_del = new JDialog(_main_frame);
    	JPanel pan_del = new JPanel();
    	JLabel lab_classid = new JLabel("请输入要删除的班级编号：");
    	final JTextField del_classid = new JTextField();
    	JButton btn_ok = new JButton("删除");
    	JButton btn_canel = new JButton("取消");
    	
    	dag_del.add(pan_del);
    	pan_del.setLayout(null);
    	
    	pan_del.add(lab_classid);
    	lab_classid.setBounds(70, 20, 200, 20);
    	
    	pan_del.add(del_classid);
    	del_classid.setBounds(90, 60, 100, 20);
    	
    	pan_del.add(btn_ok);
    	btn_ok.setBounds(50, 110, 80, 30);
    	
    	pan_del.add(btn_canel);
    	btn_canel.setBounds(160, 110, 80, 30);
    	
    	btn_ok.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			int in_classid = Integer.valueOf(del_classid.getText());
    			String sql_modify = "delete from class where classid = "+in_classid+";";
    			
				cona.getCon();
				try {
					
					int result = JOptionPane.showOptionDialog(null,"是否删除此班级  (注意：该班级所有学生会被同时删除，请谨慎操作！)","系统提示",
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
    	dag_del.setTitle("删除班级信息");
    	dag_del.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2, _main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2, 300, 200);
    	dag_del.setModal(true);
    	dag_del.setVisible(true);
    }
    
    
    public void act_Modify(MouseEvent e){
    	final JDialog dag_modify = new JDialog(_main_frame);
    	JPanel pan_modify = new JPanel();
    	JLabel lab_classid = new JLabel("请输入班级编号：");
    	JLabel lab_grade = new JLabel("请输入新年级：");
    	JLabel lab_class = new JLabel("请输入新班级：");
    	final JTextField mdy_classid = new JTextField();
    	final JTextField mdy_grade = new JTextField();
    	final JTextField mdy_class = new JTextField();
    	JButton btn_ok = new JButton("修改");
    	JButton btn_canel = new JButton("取消");
    	
    	dag_modify.add(pan_modify);
    	pan_modify.setLayout(null);
    	
    	pan_modify.add(lab_classid);
    	lab_classid.setBounds(40, 5, 110, 20);
    	
    	pan_modify.add(lab_grade);
    	lab_grade.setBounds(40, 35, 100, 20);
    	
    	pan_modify.add(lab_class);
    	lab_class.setBounds(40, 65, 100, 20);
    	
    	pan_modify.add(mdy_classid);
    	mdy_classid.setBounds(150, 5, 100, 20);
    	
    	pan_modify.add(mdy_grade);
    	mdy_grade.setBounds(150, 35, 100, 20);
    	
    	pan_modify.add(mdy_class);
    	mdy_class.setBounds(150, 65, 100, 20);
    	
    	pan_modify.add(btn_ok);
    	btn_ok.setBounds(50, 110, 80, 30);
    	
    	pan_modify.add(btn_canel);
    	btn_canel.setBounds(160, 110, 80, 30);
    	
    	btn_ok.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			int in_classid = Integer.valueOf(mdy_classid.getText());
    			String in_grade = String.valueOf(mdy_grade.getText());
    			String in_class = String.valueOf(mdy_class.getText());
    			String sql_modify = "update class set grade = '"+in_grade+"',class = '"+in_class+"' where classid = "+in_classid+";";


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
    	dag_modify.setTitle("修改班级信息");
    	dag_modify.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2, _main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2, 300, 200);
    	dag_modify.setModal(true);
    	dag_modify.setVisible(true);
    }
    
    
    public void act_Add(MouseEvent e){
    	final JDialog dag_add = new JDialog(_main_frame);
    	JPanel pan_add = new JPanel();
    	JLabel lab_grade = new JLabel("请输入年级：");
    	JLabel lab_class = new JLabel("请输入班级：");
    	final JTextField add_grade = new JTextField();
    	final JTextField add_class = new JTextField();
    	JButton btn_ok = new JButton("添加");
    	JButton btn_canel = new JButton("取消");
    	
    	dag_add.add(pan_add);
    	pan_add.setLayout(null);
    	
    	pan_add.add(lab_grade);
    	lab_grade.setBounds(40, 20, 80, 20);
    	
    	pan_add.add(lab_class);
    	lab_class.setBounds(40, 60, 80, 20);
    	
    	pan_add.add(add_grade);
    	add_grade.setBounds(120, 20, 100, 20);
    	
    	pan_add.add(add_class);
    	add_class.setBounds(120, 60, 100, 20);
    	
    	pan_add.add(btn_ok);
    	btn_ok.setBounds(50, 110, 80, 30);
    	
    	pan_add.add(btn_canel);
    	btn_canel.setBounds(160, 110, 80, 30);
    	
    	btn_ok.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			String in_grade = String.valueOf(add_grade.getText());
    			String in_class = String.valueOf(add_class.getText());
    			String sql_insert = "insert into class (grade,class) values ('"+in_grade+"','"+in_class+"');";

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
    	dag_add.setTitle("添加新班级");
    	dag_add.setBounds(_main_frame.getBounds().x+_main_frame.getBounds().width/2-300/2, _main_frame.getBounds().y+_main_frame.getBounds().height/2-200/2, 300, 200);
    	dag_add.setModal(true);
    	dag_add.setVisible(true);
    }
    
    public void act_Quit(MouseEvent e) {
        DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null,"是否退出年级信息设置?","系统提示",
                                   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                   null,new String[]  {"是","否"},"否");
        if (result == JOptionPane.YES_OPTION) {
           manger.closeFrame(this);
        }
    }
}


