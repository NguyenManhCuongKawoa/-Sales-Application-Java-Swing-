package createdbyNguyenManhCuong.test;

import createdbyNguyenManhCuong.ui.LoginUI;

public class TestLoginUI {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginUI ui = new LoginUI("Login");
	/*	try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ui.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ui.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ui.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ui.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}*/
		ui.showWindow();
	}

}

 