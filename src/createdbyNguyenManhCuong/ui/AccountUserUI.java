package createdbyNguyenManhCuong.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;

import createdbyNguyenManhCuong.connect.AccountAdminService;


public class AccountUserUI extends JFrame {

	
	int width = 820;
	int height = 569;

	JMenuBar menuBar;
	JMenu menuMenu, menuSetting, menuHelp;
	JMenuItem menuMenuTrangChu, menuMenuProduct, menuMenuAccount, menuMenuIntroduce, menuMenuChangePassword, menuMenuLogOut;
	JMenuItem menuSettingBackground, menuSettingReset;
	JMenuItem menuHelpWelcome;
	
	String imgMenu = "img/imgMenu/menu.png";
	String imgMenuSetting = "img/imgMenu/setting.png";
	String imgMenuHelp = "img/imgMenu/help.png";
	String imgMenuHome = "img/imgMenu/home.png";
	String imgMenuProduct = "img/imgMenu/ball.png";
	String imgMenuAccount = "img/imgMenu/account.png";
	String imgMenuIntroduce = "img/imgMenu/tim.png";
	String imgMenuChangePassword = "img/imgMenu/keyNew.png";
	String imgMenuLogOut = "img/imgMenu/logOut.png";
	String imgMenuSettingBackground = "img/imgMenu/background.png";
	String imgMenuSettingReset = "img/imgMenu/reset.gif";

	JPanel pnHeadRightAvatarMain;
	JPanel pnHeadRightAvatar;
	JLabel lblAvatar;

	byte[] accountImg = null;
	String urlAvatarDefault = "img/imgAccountAdmin/avatarDefault.png";
	String urlAvatar = "img/imgAccountAdmin/avatarKuroko.jpg";

	JFileChooser fileChooserImages;

	JPanel pnFace, pnYoutube, pnInstagram;
	
	String backgroundMain = "img/imgAccountAdmin/Background.png";
	String urlImgFacebook = "img/imgAccountAdmin/facebook.png";
	String urlImgYoutube = "img/imgAccountAdmin/youtube.jpg";
	String urlImgInstagram = "img/imgAccountAdmin/instagram.jpg";
	
	String urlImgKurokoGift = "img/imgGift/kuroko8.gif";
	

	public AccountUserUI(String title){
		// this.setTitle(title);
		super(title);
		/*try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(this.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(this.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(this.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(this.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}*/
		ImageIcon icon = new ImageIcon("img/icon.png"); 
		this.setIconImage(icon.getImage()); 
		GetUI.setAccountUserUI(this);
		addControls();
		addEvents();
	}

	public void addEvents() {
		// TODO Auto-generated method stub
		menuMenuAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountUI ui = new AccountUI("User", GetUI.loginUI.account);
				ui.showWindow();
			}
		});
		menuMenuProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyMenuProductUser();
			}
		});
		menuMenuChangePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChangePasswordUI ui = new ChangePasswordUI("Change Password");
				ui.showWindow();
			}
		});
		menuMenuLogOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyLogOut();
			}
		});
		menuHelpWelcome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HelpUI ui = new HelpUI("Welcome");
				ui.showWindow();
			}
		});
		menuMenuIntroduce.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IntroduceUI ui = new IntroduceUI("INTRODUCE");
				ui.showWindow();
			}
		});
		lblAvatar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Đổi Ảnh Đại Điện Hay Không?", "Xác Nhận",
						JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION) {
					handleChangeAvatar(e);
				}
			}
		});
		pnFace.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Chức năng chưa được sử dụng");
			}
		});
		pnInstagram.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Chức năng chưa được sử dụng");
			}
		});
		pnYoutube.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Chức năng chưa được sử dụng");
			}
		});
	}

	protected void xuLyMenuProductUser() {
		// TODO Auto-generated method stub
		GetUI.sanPhamUI = new SanPhamUI("Sản Phẩm");
		GetUI.sanPhamUI.showWindow();

		this.setVisible(false);
	}

	protected void handleChangeAvatar(MouseEvent e) {
		// TODO Auto-generated method stub
		int choose2 = JOptionPane.showConfirmDialog(null, "Bạn Có muốn để ảnh mặc định không?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (choose2 == JOptionPane.YES_OPTION) {
			pnHeadRightAvatarMain.remove(pnHeadRightAvatar);
			Image img = new ImageIcon(urlAvatarDefault).getImage();
			pnHeadRightAvatar = new BackGroundImage(img);
			pnHeadRightAvatar.setToolTipText("Avatar<124x124>");
			pnHeadRightAvatarMain.add(pnHeadRightAvatar);
			setVisible(true);
			
			if(xuLyLuuAnh(urlAvatarDefault) == true) JOptionPane.showMessageDialog(null, "Bạn Đã Lưu Avatar Thành Công");
			else JOptionPane.showMessageDialog(null, "Bạn Đã Lưu Avatar Thất Bại");
		} else {
			getURLImage(e);
		}
	}

	private boolean xuLyLuuAnh(String urlImage) {
		// TODO Auto-generated method stub
		try {
			File f = new File(urlImage);
			FileInputStream fis = new FileInputStream(f);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for(int readNum;(readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			accountImg = bos.toByteArray();
			GetUI.loginUI.account.setAccountImage(accountImg);
			
			if(AccountAdminService.updateAccountAvatar(GetUI.loginUI.account.getId(), accountImg) == true) 
				return true;
			else 
				return false;
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	private void getURLImage(MouseEvent e) {
		// TODO Auto-generated method stub
		urlAvatar = getURLImageFromFile();
		if (urlAvatar != null) {
			pnHeadRightAvatarMain.remove(pnHeadRightAvatar);
			Image img = new ImageIcon(urlAvatar).getImage();
			pnHeadRightAvatar = new BackGroundImage(img);
			pnHeadRightAvatar.setToolTipText("Avatar<124x124>");
			pnHeadRightAvatarMain.add(pnHeadRightAvatar);
			setVisible(true);
			
			if(xuLyLuuAnh(urlAvatar) == true) JOptionPane.showMessageDialog(null, "Bạn Đã Lưu Avatar Thành Công");
			else JOptionPane.showMessageDialog(null, "Bạn Đã Lưu Avatar Thất Bại");
		} else {
			int choose1 = JOptionPane.showConfirmDialog(null,
					"File này không hợp lệ or Bạn chưa chọn File! - Bạn Có muốn chọn lại không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (choose1 == JOptionPane.YES_OPTION) {
				getURLImage(e);
			}
		}
	}

	protected String getURLImageFromFile() {
		// TODO Auto-generated method stub
		if (fileChooserImages.showOpenDialog(null) == fileChooserImages.APPROVE_OPTION) {
			File file = fileChooserImages.getSelectedFile();
			String path = file.getPath();
			String tail[] = path.split("\\.");
			if (tail[1].compareToIgnoreCase("png") == 0 || tail[1].compareToIgnoreCase("jpg") == 0
					|| tail[1].compareToIgnoreCase("jpeg") == 0)
				return path;
		}
		return null;
	}

	protected void xuLyLogOut() {
		// TODO Auto-generated method stub
		int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn Log Out không!", "Xác Nhận",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			dispose();
			GetUI.loginUI.setVisible(true);
		}
	}

	public void addControls() {

		createMenu();
		initFileChooser();

		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		JPanel pnMain = new JPanel();
		//pnMain.setBackground(new Color(0, 0, 0, 0));
		pnMain.setLayout(new BorderLayout());
		con.add(pnMain, BorderLayout.CENTER);

		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		Border borderButtom = BorderFactory.createMatteBorder(0, 0, 5, 0, Color.pink);
		Border borderTop = BorderFactory.createMatteBorder(5, 0, 0, 0, Color.pink);
		
		JPanel pnHead = new JPanel();
		pnHead.setBorder(borderButtom);
		pnHead.setLayout(new BoxLayout(pnHead, BoxLayout.X_AXIS));
		pnMain.add(pnHead, BorderLayout.NORTH);

		JPanel pnHeadLeft = new JPanel();
		pnHeadLeft.setLayout(new BorderLayout());
		pnHeadLeft.setPreferredSize(new Dimension(400, 170));
		pnHead.add(pnHeadLeft);

		JPanel pnHeadLeftTitle = new JPanel();
		// pnHeadLeftMain.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblHeadLeftTitle = new JLabel("  Wellcome to my Basketball Shop");
		lblHeadLeftTitle.setForeground(Color.PINK);
		Font fontLBLHeadLeftTitle = new Font("Serif", Font.BOLD, 35);
		lblHeadLeftTitle.setFont(fontLBLHeadLeftTitle);
		pnHeadLeftTitle.add(lblHeadLeftTitle);
		pnHeadLeft.add(pnHeadLeftTitle, BorderLayout.NORTH);

		JPanel pnHeadLeftMain = new JPanel();
		JLabel lblHeadLeftMain = new JLabel("ACCOUNT USER");
		Border borderHeadLeftMain = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK);
		lblHeadLeftMain.setBorder(borderHeadLeftMain);
		lblHeadLeftMain.setForeground(Color.RED);
		Font fontLBLHeadLeftMain = new Font("ravie", Font.BOLD, 34);
		lblHeadLeftMain.setFont(fontLBLHeadLeftMain);
		pnHeadLeftMain.add(lblHeadLeftMain);
		pnHeadLeft.add(pnHeadLeftMain, BorderLayout.CENTER);

		JPanel pnHeadRight = new JPanel();
		pnHeadRight.setLayout(new BorderLayout());
		pnHead.add(pnHeadRight);

		pnHeadRightAvatarMain = new JPanel();
		Border borderHeadRightAvatarMain = BorderFactory.createLineBorder(Color.BLACK, 3);
		pnHeadRightAvatarMain.setBorder(borderHeadRightAvatarMain);
		pnHeadRightAvatarMain.setLayout(new BorderLayout());
		pnHeadRight.add(pnHeadRightAvatarMain, BorderLayout.CENTER);

		Image img = new ImageIcon(GetUI.loginUI.account.getAccountImage()).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
		//Image img = new ImageIcon(urlAvatar).getImage();
		pnHeadRightAvatar = new BackGroundImage(img);
		pnHeadRightAvatar.setToolTipText("Avatar<124x124>");
		pnHeadRightAvatarMain.add(pnHeadRightAvatar);
		
		JPanel pnlblAvatar = new JPanel();
		lblAvatar = new JLabel("AVATAR");
		lblAvatar.setForeground(Color.BLUE);
		Border borderlblAvatar = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
		lblAvatar.setBorder(borderlblAvatar);
		lblAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlblAvatar.add(lblAvatar);
		pnHeadRight.add(pnlblAvatar, BorderLayout.SOUTH);

		JPanel pnpnHeadRightKhoangTrangNorth = new JPanel();
		JPanel pnpnHeadRightKhoangTrangEast = new JPanel();
		JPanel pnpnHeadRightKhoangTrangWest = new JPanel();

		pnHeadRight.add(pnpnHeadRightKhoangTrangNorth, BorderLayout.NORTH);
		pnHeadRight.add(pnpnHeadRightKhoangTrangEast, BorderLayout.EAST);
		pnHeadRight.add(pnpnHeadRightKhoangTrangWest, BorderLayout.WEST);

		pnpnHeadRightKhoangTrangWest.setPreferredSize(new Dimension(77, 0));
		pnpnHeadRightKhoangTrangEast.setPreferredSize(new Dimension(80, 0));

		Image imgKurokoGift = new ImageIcon(urlImgKurokoGift).getImage();//.getScaledInstance(385, 210, DISPOSE_ON_CLOSE);
		JPanel pnCenter = new BackGroundImage(imgKurokoGift);
		pnCenter.setLayout(new BorderLayout());
		pnMain.add(pnCenter, BorderLayout.CENTER);
		
		
		
		JPanel pnLeft = new JPanel();
		 pnLeft.setBackground(Color.BLACK);
		pnLeft.setPreferredSize(new Dimension(38, 0));
		pnMain.add(pnLeft, BorderLayout.WEST);
		JPanel pnRight = new JPanel();
		pnRight.setBackground(Color.BLACK);
		pnRight.setPreferredSize(new Dimension(270, 0));
		pnMain.add(pnRight, BorderLayout.EAST);
		

		JPanel pnTail = new JPanel();
		pnTail.setBorder(borderTop);
		pnTail.setBackground(new Color(0, 0, 0, 0));
		pnTail.setPreferredSize(new Dimension(0, 60));
		pnTail.setLayout(new BoxLayout(pnTail, BoxLayout.X_AXIS));
		pnMain.add(pnTail, BorderLayout.SOUTH);

		String str = "Created by \"Nguyễn Mạnh Cường\"";
		JPanel pnIntroduceLeft = new JPanelWordRun(30, str, 0, 15, width - 200, 25);
		pnIntroduceLeft.setPreferredSize(new Dimension(width - 300, 0));
		pnTail.add(pnIntroduceLeft);

		JPanel pnIntroduceCenter = new JPanel();
		pnTail.add(pnIntroduceCenter);

		JPanel pnIntroduceRight = new JPanel();
		pnIntroduceRight.setPreferredSize(new Dimension(160, 0));
		pnTail.add(pnIntroduceRight);
		pnIntroduceRight.setLayout(new GridLayout(1, 3, 8, 8));

		Image imgFace = new ImageIcon(urlImgFacebook).getImage();
		Image imgYoutube = new ImageIcon(urlImgYoutube).getImage();
		Image imgInstagram = new ImageIcon(urlImgInstagram).getImage();
		
		pnFace = new BackGroundImage(imgFace);
		pnYoutube = new BackGroundImage(imgYoutube);
		pnInstagram = new BackGroundImage(imgInstagram);

		pnFace.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnYoutube.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnInstagram.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		pnIntroduceRight.add(pnFace);
		pnIntroduceRight.add(pnYoutube);
		pnIntroduceRight.add(pnInstagram);

	}

	private void initFileChooser() {
		// TODO Auto-generated method stub
		fileChooserImages = new JFileChooser();
		fileChooserImages.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return ".png";
			}

			@Override
			public boolean accept(File f) {
				return f.getAbsolutePath().endsWith(".png");
			}
		});
		fileChooserImages.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".jpg";
			}

			@Override
			public boolean accept(File f) {
				return f.getAbsolutePath().endsWith(".jpg");
			}
		});
		fileChooserImages.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return ".jpeg";
			}

			@Override
			public boolean accept(File f) {
				return f.getAbsolutePath().endsWith(".jpeg");
			}
		});

	}

	private void createMenu() {

		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setJMenuBar(menuBar);

		menuMenu = new JMenu("Menu");
		menuMenu.setIcon(new ImageIcon(imgMenu));
		//menuMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		//menuMenu.setHorizontalTextPosition(SwingConstants.LEFT);
		menuBar.add(menuMenu);
		menuSetting = new JMenu("Setting");
		menuSetting.setIcon(new ImageIcon(imgMenuSetting));
		//menuSetting.setHorizontalAlignment(SwingConstants.RIGHT);
		//menuSetting.setHorizontalTextPosition(SwingConstants.LEFT);
		menuBar.add(menuSetting);
		menuHelp = new JMenu("Help");
		menuHelp.setIcon(new ImageIcon(imgMenuHelp));
		//menuHelp.setHorizontalAlignment(SwingConstants.RIGHT);
		//menuHelp.setHorizontalTextPosition(SwingConstants.LEFT);
		menuBar.add(menuHelp);
		
		
		menuMenuTrangChu = new JMenuItem("Home Page");
		menuMenuTrangChu.setIcon(new ImageIcon(imgMenuHome));
		menuMenu.add(menuMenuTrangChu);
		menuMenuProduct = new JMenuItem("Product");
		menuMenuProduct.setIcon(new ImageIcon(imgMenuProduct));
		menuMenu.add(menuMenuProduct);
		menuMenuAccount = new JMenuItem("Account");
		menuMenuAccount.setIcon(new ImageIcon(imgMenuAccount));
		menuMenu.add(menuMenuAccount);
		menuMenuIntroduce = new JMenuItem("Introduce");
		menuMenuIntroduce.setIcon(new ImageIcon(imgMenuIntroduce));
		menuMenu.add(menuMenuIntroduce);
		menuMenu.addSeparator(); // line phân cách
		menuMenuChangePassword = new JMenuItem("Change PW");
		menuMenuChangePassword.setIcon(new ImageIcon(imgMenuChangePassword));
		menuMenu.add(menuMenuChangePassword);
		menuMenuLogOut = new JMenuItem("Log Out");
		menuMenuLogOut.setIcon(new ImageIcon(imgMenuLogOut));
		menuMenu.add(menuMenuLogOut);

		menuSettingBackground = new JMenuItem("Background");
		menuSettingBackground.setIcon(new ImageIcon(imgMenuSettingBackground));
		menuSetting.add(menuSettingBackground);
		menuSettingReset = new JMenuItem("Reset");
		menuSettingReset.setIcon(new ImageIcon(imgMenuSettingReset));
		menuSetting.add(menuSettingReset);

		menuHelpWelcome = new JMenuItem("WelCome");
		menuHelpWelcome.setIcon(new ImageIcon(imgMenuIntroduce));
		menuHelp.add(menuHelpWelcome);

		menuMenuProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuMenuAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuMenuIntroduce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuMenuLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuSettingBackground.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuSettingReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuHelpWelcome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void showWindow() {
		this.setSize(width, height);
		this.setResizable(false); // không cho phóng to màn hình
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		AccountUserUI ui = new AccountUserUI("User");
		ui.showWindow();
	}
}

