package GradeManager.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class GM_ConnectAccess {
	public Connection conection = null;
//	public static Connection conection = null;
//    public GM_ConnectAccess(){
//        getCon();
//
//    }
//    private Connection getCon(){
//    	String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=data\\GM_DB.mdb";
//
//		try {
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//			conection = DriverManager.getConnection(url);
//
//		} catch (java.lang.ClassNotFoundException classnotfound) {
//			classnotfound.printStackTrace();
//		} catch (java.sql.SQLException sql) {
//			JOptionPane.showMessageDialog(null, "���ݿ����ӳ����밲װ����ϵͳλ����ͬ��Microsoft Access","���ݿ����",JOptionPane.OK_OPTION);
//			System.exit(0);
//			sql.printStackTrace();
//		}
//		return conection;
//    }
    
    public Connection getCon(){
    	//64λ�Ž���--������64λϵͳ
    	String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=data\\GM_DB.mdb";
    	
    	//32λ�Ž���--������32λϵͳ
//    	String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=data\\GM_DB.mdb";

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conection = DriverManager.getConnection(url);

		} catch (java.lang.ClassNotFoundException classnotfound) {
			classnotfound.printStackTrace();
		} catch (java.sql.SQLException sql) {
			JOptionPane.showMessageDialog(null, "���ݿ����ӳ����밲װ����ϵͳλ����ͬ��Microsoft Access","���ݿ����",JOptionPane.OK_OPTION);
			System.exit(0);
			sql.printStackTrace();
		}
		return conection;
    }
    
    public void closeCon(){
    	if(conection != null){
    		try {
				conection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
}
