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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import createdbyNguyenManhCuong.connect.LoginService;
import createdbyNguyenManhCuong.limit.JTextFieldLimit;
import createdbyNguyenManhCuong.model.AccountInformation;

public class AccountUI extends JDialog{

	AccountInformation account;
	 
	JTextField txtAccountName, txtUserName, txtPhone;
	JTextArea txtAddress;
	JPasswordField txtPassword1;
	JComboBox<String> cboNgay, cboThang, cboNam;
	JRadioButton radNam, radNu;
	ButtonGroup groupGender;
	 
	JLabel lblAvatar;
	String urlAvatar;
	JPanel pnCenterTopRightCenter;
	JPanel pnAvatar;
	
	JButton btnSua, btnLuu, btnCancel;
	JButton btnLook;
	
	JButton btnChangePassword, btnReset;
	
	JFileChooser fileChooserImages;
	
	boolean trangThaiSua;
	JLabel lblTrangThai;
	

	public AccountUI(String title, AccountInformation account){
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
		GetUI.setAccountUI(this);
		this.account = account;
		addControls();
		addEvents();
	}

	public void addEvents() {
		btnLook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtPassword1.getText().length() > 0) {
					if(txtPassword1.getEchoChar() == '♥')
						txtPassword1.setEchoChar((char)0);
					else txtPassword1.setEchoChar('♥');
						
				} 
				else JOptionPane.showMessageDialog(null, "Bạn Chưa nhận gì hết!");
			}
		});
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (trangThaiSua == false) {
					trangThaiSua = true;
					lblTrangThai.setText("true                     ");
					
					txtUserName.setEnabled(true);
					radNam.setEnabled(true);
					radNu.setEnabled(true);
					txtPhone.setEnabled(true);
					cboNgay.setEnabled(true);
					cboThang.setEnabled(true);
					cboNam.setEnabled(true);
					txtAddress.setEnabled(true);

					btnChangePassword.setEnabled(true);
					btnReset.setEnabled(true);

					lblAvatar.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							JOptionPane.showMessageDialog(null, "Avatar");
							xuLyDoiAnh();
						}
					});
					
					btnLuu.setEnabled(true);
				}
				else {
					trangThaiSua = false;
					lblTrangThai.setText("false                    ");
					
					txtUserName.setEnabled(false);
					radNam.setEnabled(false);
					radNu.setEnabled(false);
					txtPhone.setEnabled(false);
					cboNgay.setEnabled(false);
					cboThang.setEnabled(false);
					cboNam.setEnabled(false);
					txtAddress.setEnabled(false);

					btnChangePassword.setEnabled(false);
					btnReset.setEnabled(false);

					lblAvatar.removeMouseListener(lblAvatar.getMouseListeners()[0]);
					
					btnLuu.setEnabled(false);
				}
				setVisible(true);
			}
		});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				trangThaiSua = false;
				lblTrangThai.setText("false                    ");
				
				txtUserName.setEnabled(false);
				radNam.setEnabled(false);
				radNu.setEnabled(false);
				txtPhone.setEnabled(false);
				cboNgay.setEnabled(false);
				cboThang.setEnabled(false);
				cboNam.setEnabled(false);
				txtAddress.setEnabled(false);

				btnChangePassword.setEnabled(false);
				btnReset.setEnabled(false);

				lblAvatar.removeMouseListener(lblAvatar.getMouseListeners()[0]);
				
				btnLuu.setEnabled(false);

				account.setUserName(txtUserName.getText());
				account.setAddress(txtAddress.getText());
				account.setPhone(txtPhone.getText());
				
				String gender = (radNam.isSelected()) ? radNam.getText() : radNu.getText();
				account.setGender(gender);

				String day = (String) cboNgay.getSelectedItem();
				String month = (String) cboThang.getSelectedItem();
				String year = (String) cboNam.getSelectedItem();
				Date date = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1, Integer.parseInt(day));
				account.setDateOfBirth(date);

				if (urlAvatar != null) {
					try {
						File f = new File(urlAvatar);
						FileInputStream fis = new FileInputStream(f);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readNum);
						}
						account.setAccountImage(bos.toByteArray());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if(LoginService.changeInformationAccount(account.getId(), account) == true) {
					JOptionPane.showMessageDialog(null, "Đổi Thông Tin Thành Công");
					xuLyReset();
				}
				else JOptionPane.showMessageDialog(null, "Đổi Thông Tin Thất Bại");
			}
		});
		btnChangePassword.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChangePasswordUI ui = new ChangePasswordUI("CHANGE PASSWORD");
				ui.showWindow();
			}
		});
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyReset();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
	protected void xuLyReset() {
		// TODO Auto-generated method stub
		account = LoginService.getAccount(account.getId());
		
		txtAccountName.setText(account.getAccountName());
		txtPassword1.setText(account.getPassword());
		txtUserName.setText(account.getUserName());
		if(account.getGender().compareTo("Nam") == 0) radNam.setSelected(true);
		else radNu.setSelected(true);
		txtPhone.setText(account.getPhone());
		Date date = (Date) account.getDateOfBirth();
		int ngay = date.getDate(), thang = date.getMonth() + 1, nam = date.getYear() + 1900;
		cboNgay.setSelectedItem(String.valueOf(ngay));
		cboThang.setSelectedItem(String.valueOf(thang));
		cboNam.setSelectedItem(String.valueOf(nam));
		txtAddress.setText(account.getAddress());
		
		pnCenterTopRightCenter.remove(pnAvatar);
		Image img = new ImageIcon(account.getAccountImage()).getImage();
		pnAvatar = new BackGroundImage(img);
		pnAvatar.setToolTipText("Avatar<124x124>");
		pnCenterTopRightCenter.add(pnAvatar);
		setVisible(true);
	}

	public void xuLyDoiAnh() {
		// TODO Auto-generated method stub
		int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Đổi Ảnh Đại Điện Hay Không?", "Xác Nhận",
				JOptionPane.YES_NO_OPTION);
		if (choose == JOptionPane.YES_OPTION) {
			getURLImage();
		}
	}
	private void getURLImage() {
		// TODO Auto-generated method stub
		urlAvatar = getURLImageFromFile();
		if (urlAvatar != null) {
			pnCenterTopRightCenter.remove(pnAvatar);
			Image img = new ImageIcon(urlAvatar).getImage();
			pnAvatar = new BackGroundImage(img);
			pnAvatar.setToolTipText("Avatar<124x124>");
			pnCenterTopRightCenter.add(pnAvatar);
			setVisible(true);
		} else {
			int choose1 = JOptionPane.showConfirmDialog(null,
					"File này không hợp lệ or Bạn chưa chọn File! - Bạn Có muốn chọn lại không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (choose1 == JOptionPane.YES_OPTION) {
				getURLImage();
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
	public void addControls() {

		initFileChooser();
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		JPanel pnTop = new JPanel();
		con.add(pnTop, BorderLayout.NORTH);
		pnTop.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
		pnTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTopTitle = new JLabel("Account Information");
		lblTopTitle.setFont(new Font("serif", Font.ITALIC, 27));
		pnTop.add(lblTopTitle);
		
		JPanel pnCenter = new JPanel();
		JScrollPane scCenter = new JScrollPane(pnCenter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		con.add(scCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		
		JPanel pnCentertop = new JPanel();
		pnCenter.add(pnCentertop);
		pnCentertop.setLayout(new BorderLayout());
		pnCentertop.setPreferredSize(new Dimension(0, 250));
		pnCentertop.setBackground(Color.black);
		
		JPanel pnCenterTopLeft = new JPanel();
		pnCentertop.add(pnCenterTopLeft, BorderLayout.WEST);
	//	pnCenterTopLeft.setBackground(Color.red);
		pnCenterTopLeft.setPreferredSize(new Dimension(380, 0));
		pnCenterTopLeft.setLayout(new BoxLayout(pnCenterTopLeft, BoxLayout.Y_AXIS));
		
		JPanel pnCenterTopLeftTrang = new JPanel();
		pnCenterTopLeftTrang.setPreferredSize(new Dimension(0, 10));
		pnCenterTopLeft.add(pnCenterTopLeftTrang);
		
		Font fontInformation = new Font("serif", Font.ITALIC, 15);
		
		JPanel pnAccountName = new JPanel();
		pnCenterTopLeft.add(pnAccountName);
		pnAccountName.setLayout(new FlowLayout());
		JLabel lblAccountName = new JLabel("Account Name: ");
		lblAccountName.setIcon(new ImageIcon("img/imgAccount/accountName.png"));
		lblAccountName.setFont(fontInformation);
		pnAccountName.add(lblAccountName);
		txtAccountName = new JTextField(20);
		txtAccountName.setEnabled(false);
		txtAccountName.setText(account.getAccountName());
		pnAccountName.add(txtAccountName);
		
		JPanel pnPassword = new JPanel();
		pnCenterTopLeft.add(pnPassword);
		pnPassword.setLayout(new FlowLayout());
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setIcon(new ImageIcon("img/imgAccount/password.png"));
		lblPassword.setFont(fontInformation);
		pnPassword.add(lblPassword);
		txtPassword1 = new JPasswordField(18);
		txtPassword1.setEnabled(false);
		txtPassword1.setText(account.getPassword());
		txtPassword1.setEchoChar('♥');
		pnPassword.add(txtPassword1);
		btnLook = new JButton();
		Image iconLook = new ImageIcon("img/changePassword/eye.png").getImage();
		btnLook.setIcon(new ImageIcon(iconLook));
		//btnLook.setPreferredSize(new Dimension(18, 18));
		btnLook.setPreferredSize(new Dimension(23, 23));
		btnLook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//btnLook.setHorizontalAlignment(JButton.CENTER);
		pnPassword.add(btnLook);
		
		JPanel pnUserName = new JPanel();
		pnCenterTopLeft.add(pnUserName);
		pnUserName.setLayout(new FlowLayout());
		JLabel lblUserName = new JLabel("User Name: ");
		lblUserName.setIcon(new ImageIcon("img/imgAccount/userName.png"));
		lblUserName.setFont(fontInformation);
		pnUserName.add(lblUserName);
		txtUserName = new JTextField(20);
		txtUserName.setEnabled(false);
		txtUserName.setDocument(new JTextFieldLimit(30));
		txtUserName.setText(account.getUserName());
		pnUserName.add(txtUserName);
		
		
		JPanel pnGender_Phone = new JPanel();
		pnCenterTopLeft.add(pnGender_Phone);
		pnGender_Phone.setLayout(new FlowLayout());
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setIcon(new ImageIcon("img/imgAccount/gender.png"));
		lblGender.setFont(fontInformation);
		pnGender_Phone.add(lblGender);
		radNam = new JRadioButton("Nam");
		radNam.setFont(fontInformation);
		radNu = new JRadioButton("Nữ");
		radNu.setFont(fontInformation);
		groupGender = new ButtonGroup();
		groupGender.add(radNam);
		groupGender.add(radNu);
		radNam.setEnabled(false);
		radNu.setEnabled(false);
		radNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radNu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnGender_Phone.add(radNam);
		pnGender_Phone.add(radNu);
		
		if(account.getGender().compareTo("Nam") == 0) radNam.setSelected(true);
		else radNu.setSelected(true);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setIcon(new ImageIcon("img/imgAccount/phone.png"));
		lblPhone.setFont(fontInformation);
		pnGender_Phone.add(lblPhone);
		txtPhone = new JTextField(7);
		txtPhone.setEnabled(false);
		txtPhone.setDocument(new JTextFieldLimit(10));
		txtPhone.setText(account.getPhone());
		pnGender_Phone.add(	txtPhone);
		
		JPanel pnDateOfBirth = new JPanel();
		pnDateOfBirth.setLayout(new FlowLayout());
		pnCenterTopLeft.add(pnDateOfBirth);

		JLabel lblDateOfBirthTitle = new JLabel("Date Of Birth:  ");
		lblDateOfBirthTitle.setIcon(new ImageIcon("img/imgAccount/dateOfBirth.png"));
		lblDateOfBirthTitle.setFont(fontInformation);
		pnDateOfBirth.add(lblDateOfBirthTitle);
		cboNgay = new JComboBox();
		cboNgay.setPreferredSize(new Dimension(58, 25));
		cboThang = new JComboBox();
		cboThang.setPreferredSize(new Dimension(58, 25));
		cboNam = new JComboBox();
		cboNam.setPreferredSize(new Dimension(75, 25));
		pnDateOfBirth.add(cboNgay);
		pnDateOfBirth.add(cboThang);
		pnDateOfBirth.add(cboNam);
		cboNgay.setEnabled(false);
		cboThang.setEnabled(false);
		cboNam.setEnabled(false);
		cboNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboThang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		thietLapTG();
		
		Date date = (Date) account.getDateOfBirth();
		int ngay = date.getDate(), thang = date.getMonth() + 1, nam = date.getYear() + 1900;
		cboNgay.setSelectedItem(String.valueOf(ngay));
		cboThang.setSelectedItem(String.valueOf(thang));
		cboNam.setSelectedItem(String.valueOf(nam));
		
		
		lblPassword.setPreferredSize(lblAccountName.getPreferredSize());
		lblUserName.setPreferredSize(lblAccountName.getPreferredSize());
		lblDateOfBirthTitle.setPreferredSize(lblAccountName.getPreferredSize());
		
		JPanel pnAddress = new JPanel();
		pnCenterTopLeft.add(pnAddress);
		pnAddress.setLayout(new BorderLayout());
		JLabel lblAddress = new JLabel("      --------Address--------------------------------------------------");
		//lblAddress.setPreferredSize(new Dimension(100, 22));
		//lblAddress.setBounds(150, 0, 15, 15);
		//lblAddress.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		lblAddress.setIcon(new ImageIcon("img/imgAccount/address.png"));
		lblAddress.setFont(fontInformation);
		pnAddress.add(lblAddress, BorderLayout.NORTH);
		txtAddress = new JTextArea();
		txtAddress.setEnabled(false);
		txtAddress.setText(account.getAddress());
		JScrollPane scAddress = new JScrollPane(txtAddress, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnAddress.add(scAddress, BorderLayout.CENTER);
		
		JPanel pnAddressLeft = new JPanel();
		pnAddressLeft.setPreferredSize(new  Dimension(25, 0));
		pnAddress.add(pnAddressLeft, BorderLayout.WEST);
		JPanel pnAddressRight = new JPanel();
		pnAddressRight.setPreferredSize(new  Dimension(25, 0));
		pnAddress.add(pnAddressRight, BorderLayout.EAST);
		
		
		JPanel pnCenterTopRight = new JPanel();
		pnCentertop.add(pnCenterTopRight, BorderLayout.CENTER);
		pnCenterTopRight.setBackground(Color.pink);
		pnCenterTopRight.setLayout(new BorderLayout());
		
		JPanel pnCenterTopRighttop = new JPanel();
		pnCenterTopRighttop.setPreferredSize(new Dimension(0, 43));
		JPanel pnCenterTopRightBottom = new JPanel();
		pnCenterTopRightBottom.setPreferredSize(new Dimension(0, 77));
		JPanel pnCenterTopRightLeft = new JPanel();
		pnCenterTopRightLeft.setPreferredSize(new Dimension(24, 0));
		JPanel pnCenterTopRightRight = new JPanel();
		pnCenterTopRightRight.setPreferredSize(new Dimension(34, 0));
		pnCenterTopRight.add(pnCenterTopRighttop, BorderLayout.NORTH);
		pnCenterTopRight.add(pnCenterTopRightBottom, BorderLayout.SOUTH);
		pnCenterTopRight.add(pnCenterTopRightLeft, BorderLayout.WEST);
		pnCenterTopRight.add(pnCenterTopRightRight, BorderLayout.EAST);
		
		pnCenterTopRightCenter = new JPanel();
		pnCenterTopRight.add(pnCenterTopRightCenter, BorderLayout.CENTER);
		pnCenterTopRightCenter.setBackground(Color.yellow);
		pnCenterTopRightCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pnCenterTopRightCenter.setLayout(new BorderLayout());
		
		Image img = new ImageIcon(account.getAccountImage()).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
		//Image img = new ImageIcon(GetUI.loginUI.account.getAccountImage()).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
		pnAvatar = new BackGroundImage(img);
		pnAvatar.setToolTipText("Avatar<124x124>");
		pnCenterTopRightCenter.add(pnAvatar);
		
		pnCenterTopRightBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -1));
		lblAvatar = new JLabel("Avatar   ");
		lblAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAvatar.setFont(new Font("serif", Font.ITALIC, 20));
		lblAvatar.addMouseListener(null);
		//lblAvatar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		pnCenterTopRightBottom.add(lblAvatar);
		
		
		JPanel pnCenterMain = new JPanel();
		pnCenter.add(pnCenterMain);
		pnCenterMain.setLayout(new BorderLayout());
		pnCenterMain.setPreferredSize(new Dimension(0, 350));
		pnCenterMain.setBackground(Color.green);
		
		JPanel pnCenterMainButton = new JPanel();
		pnCenterMain.add(pnCenterMainButton, BorderLayout.CENTER);
		pnCenterMainButton.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setEnabled(false);
		btnChangePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangePassword.setIcon(new ImageIcon("img/imgAccount/changePassword.png"));
		pnCenterMainButton.add(btnChangePassword);
		btnReset = new JButton("Reset");
		btnReset.setEnabled(false);
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReset.setIcon(new ImageIcon("img/imgAccount/reset.png"));
		pnCenterMainButton.add(btnReset);
		
		Image img0 = new ImageIcon("img/imgGift/nba1.gif").getImage().getScaledInstance(570, 300, Image.SCALE_DEFAULT);
		JPanel pnTemp0 = new BackGroundImage(img0);
		pnTemp0.setPreferredSize(new Dimension(570, 300));
		pnCenterMain.add(pnTemp0, BorderLayout.SOUTH);
		
		
		JPanel pnBottom = new JPanel();
		con.add(pnBottom, BorderLayout.SOUTH);
		pnBottom.setPreferredSize(new Dimension(0, 50));
		pnBottom.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.black));
		pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		
		JLabel lblTrangThaiSua = new JLabel();
		lblTrangThaiSua.setText("Trạng thái Sửa:");
		lblTrangThaiSua.setFont(new Font("serif", Font.ITALIC, 17));
		pnBottom.add(lblTrangThaiSua);
		
		lblTrangThai = new JLabel();
		lblTrangThai.setText("false                    ");
		trangThaiSua = false;
		lblTrangThai.setFont(new Font("serif", Font.ITALIC, 17));
		lblTrangThai.setForeground(Color.red);
		pnBottom.add(lblTrangThai);
		
		
		btnSua = new JButton("Sửa");
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setIcon(new ImageIcon("img/imgAccount/tool.png"));
		pnBottom.add(btnSua);
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.setIcon(new ImageIcon("img/imgAccount/save.png"));
		pnBottom.add(btnLuu);
		btnCancel = new JButton("Cancel");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setIcon(new ImageIcon("img/imgAccount/cancel.png"));
		pnBottom.add(btnCancel);
	}
	private void thietLapTG() {
		// TODO Auto-generated method stub
		Calendar timeNow = Calendar.getInstance();
		int yearNow = timeNow.get(Calendar.YEAR);

		for (int i = 1; i <= 31; i++)
			cboNgay.addItem(i + "");
		for (int i = 1; i <= 12; i++)
			cboThang.addItem(i + "");
		for (int i = yearNow; i > yearNow - 100; i--)
			cboNam.addItem(i + "");
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
	public void showWindow() {
		this.setSize(600, 420);
		this.setResizable(false); // không cho phóng to màn hình
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		AccountInformation account = LoginService.getAccount(1);
		AccountUI ui = new AccountUI("User", account);
		ui.showWindow();
	}
}
