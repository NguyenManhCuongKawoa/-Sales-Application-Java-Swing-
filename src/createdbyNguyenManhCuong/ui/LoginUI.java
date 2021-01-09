package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import createdbyNguyenManhCuong.ClickNotUseALT.ClickNotUseALT;
import createdbyNguyenManhCuong.connect.LoginService;
import createdbyNguyenManhCuong.limit.JTextFieldLimit;
import createdbyNguyenManhCuong.model.AccountInformation;

class Login implements Runnable {

	LoginUI ui;

	public Login() {
		this.ui = GetUI.loginUI;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (NextUI.flag == true) {
				if (NextUI.phanTram == 100) {
					if (ui.account.getId() == 1) { // bằng 1 là Account Admin
						AccountAdminUI uiAdmin = new AccountAdminUI("Account Admin");
						uiAdmin.showWindow();
					} else {
						AccountUserUI uiUser = new AccountUserUI("Account User");
						uiUser.showWindow();
					}
				}
				break;
			}
		}
		// System.out.println("Cũng thoát");
		if (NextUI.phanTram != 100)
			ui.setVisible(true);
	}
}

public class LoginUI extends JFrame {

	JPanel pnMain; // Main Gồm: Account Và Password
	JPanel pnErrorMain = null;

	String errorMain = "\"Account does NOT exist!\" or \"User account or password incorrect!\"";

	JTextField txtAccountName;
	JPasswordField txtPassword;

	JButton btnLogin, btnExit;
	JButton btnCreateNewAccount, btnHelp;

	public static AccountInformation account = null;

	public LoginUI(String title) {
		
		super(title);
		try {
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
		}
		ImageIcon icon = new ImageIcon("img/icon.png"); 
		this.setIconImage(icon.getImage()); 
		GetUI.setLoginUI(this);
		addControls();
		addEvents();
	}

	private void addEvents() {
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
		
		btnHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Account Name và Password Chỉ gồm các kí tự Chữ và Số, max là 30 kí tự, không có kí tự đặc biệt");
			}
		});
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn Thoát không!", "Xác nhận thoát",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyLogin();
			}
		});
		btnCreateNewAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyCreateNewAccount();
			}
		});
	}

	protected void formWindowClosing(WindowEvent evt) {
		// TODO Auto-generated method stub
		int lick=JOptionPane.showConfirmDialog(null,"Bạn Có Muốn Thoát Khỏi Chương Trình Hay Không?","Thông Báo",JOptionPane.OK_CANCEL_OPTION);
        if(lick == JOptionPane.OK_OPTION){
            System.exit(0);
        }
	}

	protected void xuLyCreateNewAccount() {
		// TODO Auto-generated method stub
		CreateNewAccountUI ui = new CreateNewAccountUI("Create New Account");
		ui.showWindow();
	}

	protected void xuLyLogin() {
		// TODO Auto-generated method stub
		if (txtAccountName.getText().length() != 0 || txtPassword.getText().length() != 0) {
			account = LoginService.login(txtAccountName.getText(), txtPassword.getText());
			if (account != null) {
				if (pnErrorMain != null) {
					this.setSize(this.getSize().width, this.getSize().height - pnErrorMain.getPreferredSize().height);
					pnMain.remove(pnErrorMain);
					pnErrorMain = null;
				}
				this.setVisible(false);
				NextUI ui = new NextUI("loading..");
				Login login = new Login();
			} else {
				if (pnErrorMain == null) {
					pnErrorMain = new JPanel();
					pnErrorMain.setBackground(new Color(0, 0, 0, 0));
					pnErrorMain.setLayout(new FlowLayout(FlowLayout.CENTER));
					JLabel lblErrorMain = new JLabel(errorMain);
					lblErrorMain.setForeground(Color.RED);
					pnErrorMain.add(lblErrorMain);
					pnMain.add(pnErrorMain);
					this.setSize(this.getSize().width, this.getSize().height + pnErrorMain.getPreferredSize().height);
					this.setVisible(true);
				}

				JOptionPane.showMessageDialog(null, errorMain);
			}
		} else {
			if (pnErrorMain != null) {
				this.setSize(this.getSize().width, this.getSize().height - pnErrorMain.getPreferredSize().height);
				pnMain.remove(pnErrorMain);
				pnErrorMain = null;
			}
			JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Gì Cả!");
		}
	}

	private void addControls() {
		JPanel pnBackGround = BubbleWallpaper.showSurface(100); // Bubble Wallpaper : hình nền bong bóng
		pnBackGround.setLayout(new BorderLayout());
		Container con = getContentPane();
		con.add(pnBackGround);

		JPanel pnHeadApp = new JPanel();
		pnHeadApp.setBackground(new Color(0, 0, 0, 0));
		pnHeadApp.setLayout(new BoxLayout(pnHeadApp, BoxLayout.Y_AXIS));
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 0, 0, 0));
		pnTitle.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblKhoangTrang1 = new JLabel(" ");
		pnHeadApp.add(lblKhoangTrang1);

		JLabel lblTitleLogin = new JLabel("    Login");
		Font font2 = new Font("Serif", Font.BOLD, 26);
		lblTitleLogin.setFont(font2);
		lblTitleLogin.setForeground(Color.RED);
		pnTitle.add(lblTitleLogin);
		pnHeadApp.add(pnTitle);
		pnBackGround.add(pnHeadApp, BorderLayout.NORTH);

		pnMain = new JPanel();
		pnMain.setBackground(new Color(0, 0, 0, 0));
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		pnBackGround.add(pnMain, BorderLayout.CENTER);

		JPanel pnAccountName = new JPanel();
		pnAccountName.setBackground(new Color(0, 0, 0, 0));
		pnAccountName.setLayout(new FlowLayout());
		JLabel lblAccountName = new JLabel("Account Name: ");
		Image iconAccountName = new ImageIcon("img//imgLogin//accountName.png").getImage();// getScaledInstance(16, 16,
																							// Image.SCALE_DEFAULT);
		lblAccountName.setIcon(new ImageIcon(iconAccountName));
		txtAccountName = new JTextField(20);
		txtAccountName.requestFocus();
		txtAccountName.setToolTipText("Vui lòng nhập tên tài khoản.");
		txtAccountName.setDocument(new JTextFieldLimit(30));
		txtAccountName.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtPassword.requestFocus();
				}
			}
		});
		pnAccountName.add(lblAccountName);
		pnAccountName.add(txtAccountName);
		pnMain.add(pnAccountName);

		JPanel pnPassword = new JPanel();
		pnPassword.setBackground(new Color(0, 0, 0, 0));
		pnPassword.setLayout(new FlowLayout());
		JLabel lblPassword = new JLabel(" Password: ");
		Image iconPassword = new ImageIcon("img//imgLogin//key.png").getImage();// getScaledInstance(16, 16,
																				// Image.SCALE_DEFAULT);
		lblPassword.setIcon(new ImageIcon(iconPassword));
		txtPassword = new JPasswordField(20);
		txtPassword.setToolTipText("Vui lòng nhập mật khẩu.");
		txtPassword.setDocument(new JTextFieldLimit(30));
		txtPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtAccountName.requestFocus();
				}
			}
		});
		pnPassword.add(lblPassword);
		pnPassword.add(txtPassword);
		pnMain.add(pnPassword);

		lblPassword.setPreferredSize(lblAccountName.getPreferredSize());

		JPanel pnTailApp = new JPanel();
		pnTailApp.setBackground(new Color(0, 0, 0, 0));
		pnTailApp.setLayout(new BoxLayout(pnTailApp, BoxLayout.Y_AXIS));

		// JLabel lblKhoangTrang2 = new JLabel(" ");
		// pnTailApp.add(lblKhoangTrang2);

		JPanel pnButton = new JPanel();
		pnButton.setBackground(new Color(0, 0, 0, 0));
		pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnTailApp.add(pnButton);

		btnLogin = new JButton("Login");
		//btnLogin.setFont(new java.awt.Font("Arial", 0, 12));
		btnLogin.setToolTipText("Đăng Nhập(ENTER)");
		ClickNotUseALT.clickOnKey(btnLogin, "login", KeyEvent.VK_ENTER);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // khi đẩy con trỏ vào thì sẽ hiện hình bàn
																			// tay
		Image imgLogin = new ImageIcon("img/imgLogin/login.png").getImage().getScaledInstance(16, 16,
				Image.SCALE_DEFAULT);
		ImageIcon imgIconLogin = new ImageIcon(imgLogin);
		btnLogin.setIcon(imgIconLogin);
		pnButton.add(btnLogin);

		btnHelp = new JButton("Help");
		btnHelp.setToolTipText("Trợ Giúp(ALT + H)");
		btnHelp.setMnemonic(KeyEvent.VK_H);
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgHelp = new ImageIcon("img/imgLogin/help.png").getImage();
		btnHelp.setIcon(new ImageIcon(imgHelp));
		pnButton.add(btnHelp);
		
		
		//	HAI DÒNG NÀY ĐỂ THIẾT LẶP VỊ TRÍ CỦA ICON VÀ TEXT
		//btnHelp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		//btnHelp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

		btnExit = new JButton("Exit");
		btnExit.setToolTipText("Thoát(ALT + E");
		btnExit.setMnemonic(KeyEvent.VK_E);
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgExit = new ImageIcon("img/imgLogin/stock_exit.png").getImage().getScaledInstance(16, 16,
				Image.SCALE_DEFAULT);
		ImageIcon imgIconExit = new ImageIcon(imgExit);
		btnExit.setIcon(imgIconExit);
		pnButton.add(btnExit);

		JLabel lblKhoangTrang5 = new JLabel("    ");
		pnButton.add(lblKhoangTrang5);

		JPanel pnCreateNewAccount = new JPanel();
		pnCreateNewAccount.setBackground(new Color(0, 0, 0, 0));
		pnCreateNewAccount.setLayout(new FlowLayout());
		btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.setToolTipText("Click to create a new account.");
		btnCreateNewAccount.setPreferredSize(new Dimension(250, 18));
		btnCreateNewAccount.setForeground(new Color(29, 119, 170));
		Font font3 = new Font("Serif", Font.HANGING_BASELINE, 20);
		btnCreateNewAccount.setFont(font3);
		btnCreateNewAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// hai dòng này để bỏ khung và màu nên của button
		btnCreateNewAccount.setBorderPainted(false);
		btnCreateNewAccount.setContentAreaFilled(false);
		btnCreateNewAccount.setToolTipText("ALT + N");
		btnCreateNewAccount.setMnemonic(KeyEvent.VK_N);
		pnCreateNewAccount.add(btnCreateNewAccount);
		pnTailApp.add(pnCreateNewAccount);
		JLabel lblKhoangTrang2 = new JLabel(" ");
		pnTailApp.add(lblKhoangTrang2);

		pnBackGround.add(pnTailApp, BorderLayout.SOUTH);
		
	}

	public void showWindow() {
		this.setSize(430, 250);
		this.setResizable(false); // không cho phóng to màn hình
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
