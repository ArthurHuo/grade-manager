package GradeManager.view;

import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import GradeManager.system.GM_ConnectAccess;
import GradeManager.system.GM_System;

public class GM_Login_Panel extends JPanel{
	GM_ConnectAccess cona = new GM_ConnectAccess();
	JLabel login_id = new JLabel();
	JLabel login_pw = new JLabel();
	JButton login_ok = new JButton();
	JTextField login_idinput = new JTextField();
	JPasswordField login_pwinput = new JPasswordField();
	JFrame _login_frame;
	ButtonGroup buttongroup = new ButtonGroup();
	JRadioButton button_stu = new JRadioButton("学生");
	JRadioButton button_tch = new JRadioButton("教师",true);
	Image background = null;
	MediaTracker mt = new MediaTracker(this);
	
	
	public GM_Login_Panel(JFrame login_frame){
		_login_frame = login_frame;
		init_Login_Panel();
	}

	private void init_Login_Panel() {
		this.setLayout(null);
		
				
		String imagefile = "image/img_login.jpg";
		URL resource = ClassLoader.getSystemResource(imagefile);
		if (resource != null)
	    {
	      this.background = Toolkit.getDefaultToolkit().getImage(resource);
	    }
	    else
	    {
	      this.background = Toolkit.getDefaultToolkit().getImage("./" + imagefile);
	    }
		
		mt.addImage(this.background, 0);
	    try
	    {
	      mt.waitForAll();
	    }
	    catch (InterruptedException e)
	    {
	      e.printStackTrace();
	      System.out.println("Exception While Loading Background Image.");
	      System.exit(0);
	    }
	    
	    
		
//		ImageIcon img_login = new ImageIcon("image\\img_login.jpg");
//		JLabel lab_img_login = new JLabel(img_login);
//		lab_img_login.setIcon(img_login);
//		lab_img_login.setBounds(0, 0, img_login.getIconWidth(),
//				img_login.getIconHeight());
//		this.add(lab_img_login);
		
		login_id.setText("用户名：");
		login_id.setBounds(60, 135, 100, 20);
		this.add(login_id);
		
		login_pw.setText("密    码：");
		login_pw.setBounds(60, 165, 100, 20);
		this.add(login_pw);
		
		login_ok.setText("确认");
		login_ok.setBounds(270, 210, 80, 30);
		this.add(login_ok);
		
		login_idinput.setBounds(130, 137, 180, 20);
		this.add(login_idinput);
		
		login_pwinput.setBounds(130, 167, 180, 20);
		this.add(login_pwinput);
		
		buttongroup.add(button_stu);
		buttongroup.add(button_tch);
		
		button_stu.setBounds(160, 215, 80, 20);
		this.add(button_stu);
		button_stu.setOpaque(false);
		button_tch.setBounds(80, 215, 80, 20);
		this.add(button_tch);
		button_tch.setOpaque(false);
		
//		_login_frame.addKeyListener(new KeyAdapter(){
//	    	public void keyPressed(KeyEvent e){
//	    		
//	    		if(e.getKeyCode()==e.VK_ENTER){
//	    			System.out.println("agrtgsredg");
//	    			Statement stmt = null;
//					ResultSet rs = null;
//					boolean isexist = false;
//					String id = String.valueOf(login_idinput.getText());
//					String pwd = String.valueOf(login_pwinput.getPassword());
//					String sql_select_tch = null;
//					String sql_select_stu = null;
//					sql_select_tch = "select * from user_tch where userid = '" + id + "' and userpwd = '" + pwd + "'";
//					sql_select_stu = "select * from user_stu where userid = '" + id + "' and userpwd = '" + pwd + "'";
//					try {
//						cona.getCon();
//						stmt = cona.conection.createStatement();
//						if(button_tch.isSelected()){
//							rs = stmt.executeQuery(sql_select_tch);
//							GM_System.authority = "tch";
//						}
//						if(button_stu.isSelected()){
//							rs = stmt.executeQuery(sql_select_stu);
//							GM_System.authority = "stu";
//						}
//						while(rs.next()){
//							if(id.equals(rs.getString("userid")) && pwd.equals(rs.getString("userpwd"))){
//								new GM_Main().showFrame();
//								_login_frame.dispose();
//								isexist = true;
//								if(rs!=null) rs.close();
//								if(stmt!=null) stmt.close();
//								cona.closeCon();
//								break;
//							}
//						}
//						if(!isexist){
//							JOptionPane.showMessageDialog(null, "用户名密码错误，请重新输入！","提示",JOptionPane.OK_OPTION);
//							login_idinput.setText("");
//							login_pwinput.setText("");
//							login_idinput.requestFocus();
//						}
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//	    		}  			   		
//	      }
//	    });
		login_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				boolean isexist = false;
				String id = String.valueOf(login_idinput.getText());
				String pwd = String.valueOf(login_pwinput.getPassword());
				String sql_select_tch = null;
				String sql_select_stu = null;
				sql_select_tch = "select * from user_tch where userid = '" + id + "' and userpwd = '" + pwd + "'";
				sql_select_stu = "select * from user_stu where userid = '" + id + "' and userpwd = '" + pwd + "'";
				try {
					cona.getCon();
					stmt = cona.conection.createStatement();
					if(button_tch.isSelected()){
						rs = stmt.executeQuery(sql_select_tch);
						GM_System.authority = "tch";
					}
					if(button_stu.isSelected()){
						rs = stmt.executeQuery(sql_select_stu);
						GM_System.authority = "stu";
					}
					while(rs.next()){
						if(id.equals(rs.getString("userid")) && pwd.equals(rs.getString("userpwd"))){
							if(GM_System.authority.equals("tch")){
								if(rs.getBoolean("admin")){
									GM_System.authority = "admin";
								}
							}
							new GM_Main().showFrame();
							_login_frame.dispose();
							isexist = true;
							if(rs!=null) rs.close();
							if(stmt!=null) stmt.close();
							cona.closeCon();
							break;
						}
					}
					if(!isexist){
						JOptionPane.showMessageDialog(null, "用户名密码错误，请重新输入！","提示",JOptionPane.OK_OPTION);
						login_idinput.setText("");
						login_pwinput.setText("");
						login_idinput.requestFocus();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
	}
	
	
	public String getIdInput(){
		return login_idinput.getText();
	}
	
	public String getPwInput(){
		return login_pwinput.getText();
	}
	
	protected void paintComponent(Graphics g)
	  {
	    super.paintComponent(g);

	    g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);
	  }
	
	
}
