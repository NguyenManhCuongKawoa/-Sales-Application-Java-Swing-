package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import createdbyNguyenManhCuong.connect.LoginService;
import createdbyNguyenManhCuong.limit.JTextFieldLimit;

public class ChangePasswordUI extends JDialog{
	
	JButton btnChangePassword, btnCancel, btnLayMa;
    JButton btnLook1, btnLook2, btnLook3;
    
    JLabel lblOldPassword, lblNewPassword, lblConfirmPassword, lblCodeConfirm;
	
	JPasswordField txtOldPassword, txtNewPassword, txtcomfirmPassword;
	
	JTextField txtMa;
	
	int confirmCode;
	
	String patternAccount = "[\\w]{8,}";
	
	
	public ChangePasswordUI(String title) {
		this.setTitle(title);
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
		addControls();
		addEvents();
		
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int choose  = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
				if(choose == JOptionPane.YES_OPTION) setVisible(false);
			}
		});
		txtOldPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtNewPassword.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtMa.requestFocus();
				}
			}
		});
		txtNewPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtcomfirmPassword.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtOldPassword.requestFocus();
				}
			}
		});
		txtcomfirmPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtNewPassword.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtMa.requestFocus();
				}
			}
		});
		txtMa.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtcomfirmPassword.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtOldPassword.requestFocus();
				}
			}
		});
		btnLook1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtOldPassword.getText().length() > 0) {
					if(txtOldPassword.getEchoChar() == '♥')
						txtOldPassword.setEchoChar((char)0);
					else txtOldPassword.setEchoChar('♥');
						
				} 
				else JOptionPane.showMessageDialog(null, "Bạn Chưa nhận gì hết!");
			}
		});
		btnLook2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtNewPassword.getText().length() > 0) {
					if(txtNewPassword.getEchoChar() == '♥')
						txtNewPassword.setEchoChar((char)0);
					else txtNewPassword.setEchoChar('♥');
						
				} 
				else JOptionPane.showMessageDialog(null, "Bạn Chưa nhận gì hết!");
			}
		});
		btnLook3.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtcomfirmPassword.getText().length() > 0) {
					if(txtcomfirmPassword.getEchoChar() == '♥')
						txtcomfirmPassword.setEchoChar((char)0);
					else txtcomfirmPassword.setEchoChar('♥');
					
				} 
				else JOptionPane.showMessageDialog(null, "Bạn Chưa nhận gì hết!");
			}
		});
		btnLayMa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Random r = new Random();
				confirmCode = 100000 + r.nextInt(900000);
				JOptionPane.showMessageDialog(null, "Mã Xác Nhận Của Bạn Là: \"" + confirmCode + "\" !");
			}
		});
		btnChangePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyDoiMatKhau();
			}
		});
	}

	protected void xuLyDoiMatKhau() {
		// TODO Auto-generated method stub
		if(kiemtra() == true) {
			if(LoginService.changePassword(GetUI.loginUI.account.getId(), txtNewPassword.getText()) == true) {
				JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
				GetUI.loginUI.account.setPassword(txtNewPassword.getText());
			}
			else JOptionPane.showMessageDialog(null, "Gặp sự cố gì đó, mời bạn kiểm tra lại!");
		}
	}

	private boolean kiemtra() {
		// TODO Auto-generated method stub
		boolean flag = false;

		if (txtOldPassword.getText().length() == 0 || GetUI.loginUI.account.getPassword().compareTo(txtOldPassword.getText()) != 0) {
			JOptionPane.showMessageDialog(null, "Mật Khẩu Cũ Không Đúng");
			lblOldPassword.setForeground(Color.RED);
			flag = true;
		} else
			lblOldPassword.setForeground(Color.BLACK);

		if (txtNewPassword.getText().length() == 0 || txtNewPassword.getText().matches(patternAccount) == false || txtNewPassword.getText().compareTo(txtOldPassword.getText()) == 0) { // Mất Khẩu không đúng định dạng
			JOptionPane.showMessageDialog(null, "Mật Khẩu Mới Không Đúng Định Dạng hoặc Giống MK Cũ");
			lblNewPassword.setForeground(Color.RED);
			flag = true;
		} else {
			lblNewPassword.setForeground(Color.BLACK);
		}
			
	    if (txtcomfirmPassword.getText().length() == 0 || txtNewPassword.getText().compareTo(txtcomfirmPassword.getText()) != 0) { // Xác nhận mật khẩu sai
			JOptionPane.showMessageDialog(null, "Xác Nhận Mật Khẩu Không Đúng");
			lblConfirmPassword.setForeground(Color.RED);
			flag = true;
		} else {
			lblConfirmPassword.setForeground(Color.black);
		}

		if (txtMa.getText().length() == 0 || confirmCode != Integer.parseInt(txtMa.getText())) {
			JOptionPane.showMessageDialog(null, "Mã Xác Nhận Không đúng");
			lblCodeConfirm.setForeground(Color.RED);
			flag = true;
		} else
			lblCodeConfirm.setForeground(Color.black);

		if (flag == false)
			return true;
		else
			return false;
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		Border borderTop = BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK);
		Border borderButtom = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black);
		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
		
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new BoxLayout(pnTitle, BoxLayout.Y_AXIS));
		pnTitle.setPreferredSize(new Dimension(0, 75));
		pnTitle.setBorder(borderButtom);
		JPanel pnTrang = new JPanel();
		pnTitle.add(pnTrang);
		JLabel lblTitle = new JLabel(" Change Password");
		Font fontlblTitle = new Font("ravie", Font.BOLD, 27);
		lblTitle.setFont(fontlblTitle);
		lblTitle.setForeground(Color.red);
		pnTitle.add(lblTitle);
		JPanel pnTrang1 = new JPanel();
		pnTitle.add(pnTrang1);
		con.add(pnTitle, BorderLayout.NORTH);
		
		JPanel pnMain = new JPanel();
		//pnMain.setLayout(new GridLayout(5, 1));
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain, BorderLayout.CENTER);
		
		JPanel pnKhoangTrang2 = new JPanel();
		pnKhoangTrang2.setPreferredSize(new Dimension(0, 2));
		pnMain.add(pnKhoangTrang2);
		
		JPanel pnAccountName = new JPanel();
		//	pnOldPassword.setBorder(border);
		pnAccountName.setLayout(new FlowLayout());
		JLabel lblAccountName = new JLabel("Tên TK: ");
		Image iconAccountName = new ImageIcon("img/changePassword/accountName.png").getImage();
		lblAccountName.setIcon(new ImageIcon(iconAccountName));
		pnAccountName.add(lblAccountName);
		JTextField txtAccountName = new JTextField(20);
		txtAccountName.setFocusable(false);
		txtAccountName.setPreferredSize(new Dimension(100, 26));
		txtAccountName.setEditable(false);
		if(GetUI.loginUI != null) txtAccountName.setText(GetUI.loginUI.account.getAccountName());
		else txtAccountName.setText("Nguyễn Mạnh Cường");
		pnAccountName.add(txtAccountName);
		pnMain.add(pnAccountName);
		
		JPanel pnOldPassword = new JPanel();
	//	pnOldPassword.setBorder(border);
		pnOldPassword.setLayout(new BoxLayout(pnOldPassword, BoxLayout.Y_AXIS));
		JPanel pnOldPasswordMain = new JPanel();
		pnOldPasswordMain.setLayout(new FlowLayout());
		lblOldPassword = new JLabel("Old PW: ");
		//lblOldPassword.setForeground(Color.RED);
		Image iconKeyOldPassword = new ImageIcon("img/changePassword/keyOld.jpg").getImage();
		lblOldPassword.setIcon(new ImageIcon(iconKeyOldPassword));
		pnOldPasswordMain.add(lblOldPassword);
		txtOldPassword = new JPasswordField(17);
		txtOldPassword.setFocusable(true);
		txtOldPassword.setDocument(new JTextFieldLimit(20));
		txtOldPassword.setPreferredSize(new Dimension(100, 26));
		pnOldPasswordMain.add(txtOldPassword);
		btnLook1 = new JButton();
		Image iconLook = new ImageIcon("img/changePassword/eye.png").getImage();
		btnLook1.setIcon(new ImageIcon(iconLook));
		btnLook1.setPreferredSize(new Dimension(26, 26));
		pnOldPasswordMain.add(btnLook1);
		pnOldPassword.add(pnOldPasswordMain);
		pnMain.add(pnOldPassword);
	
		JPanel pnNewPassword = new JPanel();
	//	pnNewPassword.setBorder(border);
		pnNewPassword.setLayout(new BoxLayout(pnNewPassword, BoxLayout.Y_AXIS));
		JPanel pnNewPasswordMain = new JPanel();
		pnNewPasswordMain.setLayout(new FlowLayout());
		lblNewPassword = new JLabel("New PW: ");
		Image iconKeyPassword = new ImageIcon("img/changePassword/keyNew.png").getImage();
		lblNewPassword.setIcon(new ImageIcon(iconKeyPassword));
		pnNewPasswordMain.add(lblNewPassword);
		txtNewPassword = new JPasswordField(17);
		txtNewPassword.setDocument(new JTextFieldLimit(20));
		txtNewPassword.setPreferredSize(new Dimension(100, 26));
		pnNewPasswordMain.add(txtNewPassword);
		btnLook2 = new JButton();
		btnLook2.setIcon(new ImageIcon(iconLook));
		btnLook2.setPreferredSize(new Dimension(26, 26));
		pnNewPasswordMain.add(btnLook2);
		pnNewPassword.add(pnNewPasswordMain);
		pnMain.add(pnNewPassword);
		
		JPanel pnConfirmPassword = new JPanel();
	//	pnConfirmPassword.setBorder(border);
		pnConfirmPassword.setLayout(new BoxLayout(pnConfirmPassword, BoxLayout.Y_AXIS));
		JPanel pnConfirmPasswordMain = new JPanel();
		pnConfirmPasswordMain.setLayout(new FlowLayout());
		lblConfirmPassword = new JLabel("Confirm PW: ");
		lblConfirmPassword.setIcon(new ImageIcon(iconKeyPassword));
		pnConfirmPasswordMain.add(lblConfirmPassword);
		txtcomfirmPassword = new JPasswordField(17);
		txtcomfirmPassword.setDocument(new JTextFieldLimit(20));
		txtcomfirmPassword.setPreferredSize(new Dimension(100, 26));
		pnConfirmPasswordMain.add(txtcomfirmPassword);
		btnLook3 = new JButton();
		btnLook3.setIcon(new ImageIcon(iconLook));
		btnLook3.setPreferredSize(new Dimension(26, 26));
		pnConfirmPasswordMain.add(btnLook3);
		pnConfirmPassword.add(pnConfirmPasswordMain);
		pnMain.add(pnConfirmPassword);
		
		JPanel pnCodeConfirm = new JPanel();
	//	pnCodeConfirm.setBorder(border);lock.png
		pnCodeConfirm.setLayout(new BoxLayout(pnCodeConfirm, BoxLayout.Y_AXIS));
		JPanel pnCodeConfirmMain = new JPanel();
		pnCodeConfirmMain.setLayout(new FlowLayout());
		lblCodeConfirm = new JLabel("Confirm: ");
		Image iconCodeConfirm = new ImageIcon("img/changePassword/lock.png").getImage();
		lblCodeConfirm.setIcon(new ImageIcon(iconCodeConfirm));
		pnCodeConfirmMain.add(lblCodeConfirm);
		txtMa = new JTextField(9);
		txtMa.setPreferredSize(new Dimension(100, 24));
		pnCodeConfirmMain.add(txtMa);
		btnLayMa = new JButton("Get Code");
		Image iconGetCode = new ImageIcon("img/changePassword/mail1.png").getImage();
		btnLayMa.setIcon(new ImageIcon(iconGetCode));
		btnLayMa.setPreferredSize(new Dimension(108, 24));
		pnCodeConfirmMain.add(btnLayMa);
		pnCodeConfirm.add(pnCodeConfirmMain);
		pnMain.add(pnCodeConfirm);
		
		
		txtOldPassword.setToolTipText("Mật Khẩu phải có ít nhất 8 kí tự, không có kí tự đặc biệt!");
		txtNewPassword.setToolTipText("Mật Khẩu phải có ít nhất 8 kí tự, không có kí tự đặc biệt!");
		txtcomfirmPassword.setToolTipText("Mật Khẩu phải có ít nhất 8 kí tự, không có kí tự đặc biệt!");
		txtMa.setToolTipText("Mã Gồm 11 kí tự sẽ được gửi đến bạn!");
		lblNewPassword.setPreferredSize(lblConfirmPassword.getPreferredSize());
		lblOldPassword.setPreferredSize(lblConfirmPassword.getPreferredSize());
		lblCodeConfirm.setPreferredSize(lblConfirmPassword.getPreferredSize());
		lblAccountName.setPreferredSize(lblConfirmPassword.getPreferredSize());
		txtOldPassword.setEchoChar('♥');
		txtNewPassword.setEchoChar('♥');
		txtcomfirmPassword.setEchoChar('♥');
		
		
		JPanel pnTail = new JPanel();
		pnTail.setPreferredSize(new Dimension(0, 47));
		pnTail.setBorder(borderTop);
		con.add(pnTail, BorderLayout.SOUTH);
		pnTail.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setPreferredSize(new Dimension(165, 30));
		Image iconChangePassword = new ImageIcon("img/changePassword/change1.png").getImage();
		btnChangePassword.setIcon(new ImageIcon(iconChangePassword));
		pnTail.add(btnChangePassword);
		
		JPanel pnKhoangTrang1 = new JPanel();
		pnKhoangTrang1.setPreferredSize(new Dimension(5, 0));
		pnTail.add(pnKhoangTrang1);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setPreferredSize(new Dimension(110, 30));
		Image iconCancel= new ImageIcon("img/changePassword/cancel.png").getImage();
		btnCancel.setIcon(new ImageIcon(iconCancel));
		pnTail.add(btnCancel);
		
		JPanel pnKhoangTrang = new JPanel();
		pnKhoangTrang.setPreferredSize(new Dimension(10, 0));
		pnTail.add(pnKhoangTrang);
		
	}
	public void showWindow() {
		this.setSize(370, 450);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		ChangePasswordUI ui = new ChangePasswordUI("CHANGE PASSWORD");
		ui.showWindow();
	}
}
