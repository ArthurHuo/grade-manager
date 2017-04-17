package GradeManager.view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GM_About extends JInternalFrame{
	JPanel panel_main = new JPanel();
	Image background = null;
	MediaTracker mt = new MediaTracker(this);
	JLabel lab_ver = new JLabel();
	JLabel lab_aut = new JLabel();
	JLabel lab_qq = new JLabel();
	JLabel lab_web = new JLabel();
	JLabel lab_img = new JLabel();
	
	public GM_About(){
		init_About();
	}

	private void init_About() {
//		this.setIconifiable(false);
		this.setSize(400,300);
        this.setClosable(true);
            
//        try {
//			this.setIcon(true);
//		} catch (PropertyVetoException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//        this.setMaximizable(true);
                
        this.add(panel_main);
        panel_main.setLayout(null);
        
        ImageIcon img_main = new ImageIcon("image/img_login.jpg");
        JLabel lab_img_main = new JLabel(img_main);
        lab_img_main.setBounds(0,0,img_main.getIconWidth(),img_main.getIconHeight());
        panel_main.add(lab_img_main,new Integer(Integer.MIN_VALUE));
        
        lab_ver.setText("成绩管理系统 beta");
		lab_ver.setBounds(40, 110, 200, 20);
		panel_main.add(lab_ver,0);
		
		lab_aut.setText("作者：霍佳彤 20091312015");
		lab_aut.setBounds(40, 135, 200, 20);
		panel_main.add(lab_aut,0);
		
		lab_qq.setText("qq:931991979");
		lab_qq.setBounds(40, 160, 200, 20);
		panel_main.add(lab_qq,0);
		
//		try {
//			URL url = new URL("http://www.tnzyq.com");
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		lab_web.setText("<html><a href =url>http://www.tnzyq.com</html>");
		lab_web.setBounds(40, 180, 125, 20);
		panel_main.add(lab_web,0);
		lab_web.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lab_web.addMouseListener(new MouseAdapter(){  
		      public void mouseClicked(MouseEvent e){
		    	  Desktop desktop = Desktop.getDesktop();
		    	  URI uri = null;
				try {
					uri = new URI("http://www.tnzyq.com");
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	  try {
					desktop.browse(uri);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
		});
		
		lab_img.setText("美术协力：邵佳琦  qq：419113305");
		lab_img.setBounds(40, 210, 200, 20);
		panel_main.add(lab_img,0);
        
//        String imagefile = "image/img_login.jpg";
//		URL resource = ClassLoader.getSystemResource(imagefile);
//		if (resource != null)
//	    {
//	      this.background = Toolkit.getDefaultToolkit().getImage(resource);
//	    }
//	    else
//	    {
//	      this.background = Toolkit.getDefaultToolkit().getImage("./" + imagefile);
//	    }
//		
//		mt.addImage(this.background, 0);
//	    try
//	    {
//	      mt.waitForAll();
//	    }
//	    catch (InterruptedException e)
//	    {
//	      e.printStackTrace();
//	      System.out.println("Exception While Loading Background Image.");
//	      System.exit(0);
//	    }
		
	}
	
//	protected void paintComponent(Graphics g)
//	  {
//	    super.paintComponent(g);
//
//	    g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);
//	  }
}
