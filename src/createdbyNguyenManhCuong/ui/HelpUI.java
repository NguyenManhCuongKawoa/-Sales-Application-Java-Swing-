package createdbyNguyenManhCuong.ui;

import java.awt.Container;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class HelpUI extends JDialog {

	public HelpUI(String title) {
			setTitle(title);
			ImageIcon icon = new ImageIcon("img/icon.png"); 
			this.setIconImage(icon.getImage()); 
		    GetUI.setHelpUI(this);
			addControls();
			addEvents();
		}

	private void addEvents() {

	}

	private void addControls() {
		Container con = getContentPane();

		Image img = new ImageIcon("img/imgGift/kuroko1.gif").getImage();
		JPanel pn = new BackGroundImage(img);
		con.add(pn);
	}

	public void showWindow() {
		this.setSize(514, 318);
		this.setResizable(false); // không cho phóng to màn hình
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       HelpUI ui = new HelpUI("HelpUI");
       ui.showWindow();
	}
}
