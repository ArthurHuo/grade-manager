package GradeManager.system;

import GradeManager.view.GM_Login;

public class GM_System {
	public static String authority = null;
	public GM_System(){
		init_System();
	}

	private void init_System() {
		GM_ConnectAccess cona = new GM_ConnectAccess();
		cona.getCon();
		if(cona.conection != null){
			GM_Login gm_login = new GM_Login();
			gm_login.showFrame();
			cona.closeCon();
		}
		
	}
}
