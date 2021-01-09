package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroduceUI extends JDialog {
	public IntroduceUI(String title) {
		setTitle(title);
		ImageIcon icon = new ImageIcon("img/icon.png"); 
		this.setIconImage(icon.getImage()); 
		//GetUI.setHelpUI(this);
		addControls();
		addEvents();
	}

	private void addEvents() {

	}

	private void addControls() {
		
		Font fontTitle = new Font("Serif", Font.BOLD, 20);
		Font font = new Font("Serif", Font.BOLD, 15);
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		Image img = new ImageIcon("img/imgGift/mua.gif").getImage();
		JPanel pn = new BackGroundImage(img);
		pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
		con.add(pn, BorderLayout.CENTER);
		
		JLabel lblTop1 = new JLabel("  ");
		pn.add(lblTop1);
		JLabel lblTop2 = new JLabel("  ");
		pn.add(lblTop2);
		JLabel lblTop3 = new JLabel("  ");
		pn.add(lblTop3);
		
		JLabel lblAuthorInformation = new JLabel("       AUTHOR INFORMATION");
		lblAuthorInformation.setForeground(Color.pink);
		lblAuthorInformation.setFont(fontTitle);
		JLabel lblAuthor = new JLabel("   _Author: Nguyễn Mạnh Cường");
		lblAuthor.setForeground(Color.red);
		lblAuthor.setFont(font);
		JLabel lblDate = new JLabel("   _Date of birth: 22/11/2001");
		lblDate.setForeground(Color.red);
		lblDate.setFont(font);
		JLabel lblAddress = new JLabel("   _Address: Sơn Lôi - Bình Xuyên - Vinh Phúc");
		lblAddress.setForeground(Color.red);
		lblAddress.setFont(font);
		
		pn.add(lblAuthorInformation);
		pn.add(lblAuthor);
		pn.add(lblDate);
		pn.add(lblAddress);
		
		JLabel lblTop4 = new JLabel("  ");
		pn.add(lblTop4);
		JLabel lblTop5 = new JLabel("  ");
		pn.add(lblTop5);
	
		
		JLabel lblDeviceInformation = new JLabel("       DEVICE INFORMATION", JLabel.CENTER);
		lblDeviceInformation.setForeground(Color.pink);
		lblDeviceInformation.setFont(fontTitle);
		JLabel lblNameDevice = new JLabel("   _Name: Basketball Shop");
		lblNameDevice.setForeground(Color.red);
		lblNameDevice.setFont(font);
		JLabel lblVersion = new JLabel("   _Version: 1.00");
		lblVersion.setForeground(Color.red);
		lblVersion.setFont(font);
		JLabel lblCreatedByLanguage = new JLabel("   _Created by language: Java");
		lblCreatedByLanguage.setForeground(Color.red);
		lblCreatedByLanguage.setFont(font);
		JLabel lblDateCreated = new JLabel("   _Date created: 23/11/2020");
		lblDateCreated.setForeground(Color.red);
		lblDateCreated.setFont(font);
		
		pn.add(lblDeviceInformation);
		pn.add(lblNameDevice);
		pn.add(lblVersion);
		pn.add(lblCreatedByLanguage);
		pn.add(lblDateCreated);
		
		String str = "Created by \"Nguyễn Mạnh Cường\"";
		JPanel pnButtom = new JPanelWordRun(30, str, 0, 15, 200, 25);
		pnButtom.setBackground(new Color(0, 0, 0));
		pnButtom.setPreferredSize(new Dimension(0, 60));
		con.add(pnButtom, BorderLayout.SOUTH);
		
	}

	public void showWindow() {
		this.setSize(356, 550);
		this.setResizable(false); // không cho phóng to màn hình
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntroduceUI ui = new IntroduceUI("INTRODUCE");
		ui.showWindow();
	}
}
