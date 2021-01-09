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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import createdbyNguyenManhCuong.ClickNotUseALT.ClickNotUseALT;
import createdbyNguyenManhCuong.connect.LoginService;
import createdbyNguyenManhCuong.connect.SignUpService;
import createdbyNguyenManhCuong.limit.JTextFieldLimit;
import createdbyNguyenManhCuong.model.AccountInformation;

public class CreateNewAccountUI extends JDialog {

	String urlAvatarDefault = "img/imgAccountAdmin/avatarDefault.png";
	
	JPanel pnAccountNameMain;
	JPanel pnAccountPasswordMain;
	JPanel pnUserName;
	JPanel pnGender;
	JPanel pnPhone;

	JPanel pnErrorAccountName = null;
	JPanel pnErrorAccountPassword = null;
	JPanel pnErrorUserName = null;
	JPanel pnErrorGender = null;
	JPanel pnErrorPhone = null;

	JTextField txtAccountName, txtHo, txtTen, txtPhone;
	JTextArea txtAddress;
	JPasswordField txtPassword1, txtPassword2;
	JComboBox<String> cboNgay, cboThang, cboNam;
	JRadioButton radNam, radNu;
	ButtonGroup groupGender;

	JButton btnSignUP, btnCancel, btnReset;

	String charTail = "-------------------------";
	String charHead = "    -------";

	// Image imgTrue = new ImageIcon("img/true.png").getImage();
	// ImageIcon imgIcontrue = new ImageIcon(imgTrue);

	// Tạo các regex để so sánh
	String patternAccount = "[\\w]{8,}"; // là chữ cái or số và ít nhất 8 kí tự
	String patternName = "[[A-z]+\\s]+"; // chỉ có chữ kí tự
	String patternPhone = "[\\d]{10}"; // chỉ gồm các kí tự số và phải là 10 số

	// các lỗi khi so sánh với regex
	String errorAccountName = "Tên TK phải có ít nhất 8 kí tự, không có kí tự đặc biệt!";
	String errorAccountNameIsExist = "Tên TK đã tồn tại!";
	String errorAccountPassword = "Mật Khẩu phải có ít nhất 8 kí tự, không có kí tự đặc biệt!";
	String errorAccountConfirmPassword = "Mật Khẩu không trùng với Mật khẩu ở trên!";
	String errorName = "User Name chỉ có kí tự chữ!";
	String errorPhone = "Phone gồm 10 kí tự số!";
	String errorGender = "Gender bắt buộc phải chọn!";

	public CreateNewAccountUI(String title) {
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
		GetUI.setCreateNewAccountUI(this);
		addControls();
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancelSignUP();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyReset();
			}
		});
		txtPassword1.addMouseListener(new MouseListener() {

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
				if (txtPassword1.getEchoChar() != '♥')
					txtPassword1.setText("");
				txtPassword1.setEchoChar('♥'); // thay thế password bằng kí tự trong dấu ''
			}
		});
		txtPassword2.addMouseListener(new MouseListener() {

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
				if (txtPassword2.getEchoChar() != '♥')
					txtPassword2.setText("");
				txtPassword2.setEchoChar('♥'); // thay thế password bằng kí tự trong dấu ''
			}
		});
		btnSignUP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLySignUp();
			}
		});
	}

	protected void xuLySignUp() {
		// TODO Auto-generated method stub
		boolean flag = true;
		if (pnErrorAccountName != null) {
			this.setSize(getSize().width, getSize().height - pnErrorAccountName.getPreferredSize().height);
			pnAccountNameMain.remove(pnErrorAccountName);
			pnErrorAccountName = null;
		}
		if (txtAccountName.getText().matches(patternAccount) == false) { // không đúng định dạng
			flag = false;
			if (pnErrorAccountName == null) {
				pnErrorAccountName = new JPanel();
				pnErrorAccountName.setLayout(new FlowLayout(FlowLayout.LEADING));
				JLabel lblErrorAccountName = new JLabel(errorAccountName);
				lblErrorAccountName.setForeground(Color.RED);
				pnErrorAccountName.add(lblErrorAccountName);
				pnAccountNameMain.add(pnErrorAccountName);

				this.setSize(getSize().width, getSize().height + pnErrorAccountName.getPreferredSize().height);
			}
			JOptionPane.showMessageDialog(null, errorAccountName);
		} else {
			if (isExist(txtAccountName.getText())) { // Tên tài khoản đã nhập đúng nhưng bị trùng
				pnErrorAccountName = new JPanel();
				pnErrorAccountName.setLayout(new FlowLayout(FlowLayout.LEADING));
				JLabel lblErrorAccountName = new JLabel(errorAccountNameIsExist);
				lblErrorAccountName.setForeground(Color.RED);
				pnErrorAccountName.add(lblErrorAccountName);
				pnAccountNameMain.add(pnErrorAccountName);

				this.setSize(getSize().width, getSize().height + pnErrorAccountName.getPreferredSize().height);
				JOptionPane.showMessageDialog(null, errorAccountNameIsExist);
				flag = false;
			}
		}
		if (pnErrorAccountPassword != null) {
			this.setSize(getSize().width, getSize().height - pnErrorAccountPassword.getPreferredSize().height);
			pnAccountPasswordMain.remove(pnErrorAccountPassword);
			pnErrorAccountPassword = null;
		}
		if (txtPassword1.getText().matches(patternAccount) == false) { // lỗi nhập sai
			flag = false;
			if (pnErrorAccountPassword == null) {
				pnErrorAccountPassword = new JPanel();
				pnErrorAccountPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel lblErrorAccountPassword = new JLabel(errorAccountPassword);
				lblErrorAccountPassword.setForeground(Color.RED);
				pnErrorAccountPassword.add(lblErrorAccountPassword);
				pnAccountPasswordMain.add(pnErrorAccountPassword);

				this.setSize(getSize().width, getSize().height + pnErrorAccountPassword.getPreferredSize().height);
			}
			JOptionPane.showMessageDialog(null, "MK: {" + txtPassword1.getText() + "} " + errorAccountPassword);
		} else {
			if (txtPassword1.getText().compareTo(txtPassword2.getText()) != 0) { // lỗi xác nhận sai
				pnErrorAccountPassword = new JPanel();
				pnErrorAccountPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel lblErrorAccountPassword = new JLabel(errorAccountConfirmPassword);
				lblErrorAccountPassword.setForeground(Color.RED);
				pnErrorAccountPassword.add(lblErrorAccountPassword);
				pnAccountPasswordMain.add(pnErrorAccountPassword);

				this.setSize(getSize().width, getSize().height + pnErrorAccountPassword.getPreferredSize().height);

				flag = false;
				JOptionPane.showMessageDialog(null, "MK: {" + txtPassword1.getText() + "} \n" + "MK confirm: {"
						+ txtPassword2.getText() + "} \n" + errorAccountConfirmPassword);
			}
		}
		if ((txtHo.getText().matches(patternName) == false || txtTen.getText().matches(patternName) == false)) {
			flag = false;
			if (pnErrorUserName == null) {
				pnErrorUserName = new JPanel();
				pnErrorUserName.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel lblErrorUserName = new JLabel(errorName);
				lblErrorUserName.setForeground(Color.RED);
				pnErrorUserName.add(lblErrorUserName);
				pnUserName.add(pnErrorUserName);

				this.setSize(getSize().width, getSize().height + pnErrorUserName.getPreferredSize().height);
			}
			JOptionPane.showMessageDialog(null, errorName);
		} else if (pnErrorUserName != null) {
			this.setSize(getSize().width, getSize().height - pnErrorUserName.getPreferredSize().height);
			pnUserName.remove(pnErrorUserName);
			pnErrorUserName = null;
		}
		if (txtPhone.getText().matches(patternPhone) == false) {
			if (pnErrorPhone == null) {
				pnErrorPhone = new JPanel();
				pnErrorPhone.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lblErrorPhone = new JLabel(errorPhone);
				lblErrorPhone.setForeground(Color.RED);
				pnErrorPhone.add(lblErrorPhone);
				pnPhone.add(pnErrorPhone);

				this.setSize(getSize().width, getSize().height + pnErrorPhone.getPreferredSize().height);
			}
			JOptionPane.showMessageDialog(null, errorPhone);
			flag = false;
		} else if (pnErrorPhone != null) {
			this.setSize(getSize().width, getSize().height - pnErrorPhone.getPreferredSize().height);
			pnPhone.remove(pnErrorPhone);
			pnErrorPhone = null;
		}
		if (groupGender.getSelection() == null) {
			flag = false;
			if (pnErrorGender == null) {
				pnErrorGender = new JPanel();
				pnErrorGender.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lblErrorGender = new JLabel(errorGender);
				lblErrorGender.setForeground(Color.RED);
				pnErrorGender.add(lblErrorGender);
				pnGender.add(pnErrorGender);

				this.setSize(getSize().width, getSize().height + pnErrorGender.getPreferredSize().height);
			}
			JOptionPane.showMessageDialog(null, errorGender);
		} else if (pnErrorGender != null) {
			this.setSize(getSize().width, getSize().height - pnErrorGender.getPreferredSize().height);
			pnGender.remove(pnErrorGender);
			pnErrorGender = null;
		}

		if (flag == true) {
			AccountInformation account = new AccountInformation();

			// account.setId();
			account.setAccountName(txtAccountName.getText());
			account.setPassword(txtPassword1.getText());

			account.setUserName(txtHo.getText() + " " + txtTen.getText());

			String gender = (radNam.isSelected()) ? radNam.getText() : radNu.getText();
			account.setGender(gender);

			account.setPhone(txtPhone.getText());

			String day = (String) cboNgay.getSelectedItem();
			String month = (String) cboThang.getSelectedItem();
			String year = (String) cboNam.getSelectedItem();
			Date date = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1, Integer.parseInt(day));
			account.setDateOfBirth(date);

			account.setAddress(txtAddress.getText());
			// Để ảnh mặc Định
			try {
				File f = new File(urlAvatarDefault);
				FileInputStream fis = new FileInputStream(f);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				for(int readNum;(readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
				account.setAccountImage(bos.toByteArray());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			SignUpService.signUp(account);
		}
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private boolean isExist(String text) {
		// TODO Auto-generated method stub
		ArrayList<String> arr = LoginService.layTatCaCacAccount();
		for (String s : arr) {
			if (s.compareTo(text) == 0) { // đã tồn tại tên tài khoản rồi
				return true;
			}
		}
		return false;
	}

	protected void xuLyReset() {
		// Xử lý Text
		txtAccountName.requestFocus();
		txtAccountName.setText("Account Name");
		txtPassword1.setText("Password");
		txtPassword1.setEchoChar((char) 0);
		txtPassword2.setText("Confirm password");
		txtPassword2.setEchoChar((char) 0);
		txtHo.setText("First Name");
		txtTen.setText("Last Name");

		cboNgay.setSelectedIndex(0);
		cboThang.setSelectedIndex(0);
		cboNam.setSelectedIndex(0);

		txtPhone.setText("Phone");

		groupGender.clearSelection();

		txtAddress.setText("Address");

		// Xử lý jPanel lỗi
		if (pnErrorAccountName != null) {
			pnAccountNameMain.remove(pnErrorAccountName);
			pnErrorAccountName = null;
		}
		if (pnErrorAccountPassword != null) {
			pnAccountPasswordMain.remove(pnErrorAccountPassword);
			pnErrorAccountPassword = null;
		}
		if (pnErrorUserName != null) {
			pnUserName.remove(pnErrorUserName);
			pnErrorUserName = null;
		}
		if (pnErrorPhone != null) {
			pnPhone.remove(pnErrorPhone);
			pnErrorPhone = null;
		}
		if (pnErrorGender != null) {
			pnGender.remove(pnErrorGender);
			pnErrorGender = null;
		}
		//this.setSize(400, 625);
		this.setSize(400, 658);
		this.setVisible(true);

	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		// TITLE
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new BoxLayout(pnTitle, BoxLayout.Y_AXIS));
		JLabel lblTitleMain = new JLabel("Đăng Ký");
		Font font1 = new Font("Serif", Font.BOLD, 26);
		lblTitleMain.setFont(font1);
		JLabel tblTitle2 = new JLabel("Nhanh chóng và dễ dàng");
		pnTitle.add(lblTitleMain);
		pnTitle.add(tblTitle2);

		Border border1 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		JPanel pnKhoangTrang = new JPanel();
		pnKhoangTrang.setBorder(border1);
		pnKhoangTrang.setPreferredSize(new Dimension(0, 2));
		JPanel pnKhoangTrang1 = new JPanel();
		pnTitle.add(pnKhoangTrang1);
		pnTitle.add(pnKhoangTrang);

		con.add(pnTitle, BorderLayout.NORTH);

		// CONTENT MAIN
		JPanel pnMain = new JPanel();
		//JScrollPane scMain = new JScrollPane(pnMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain, BorderLayout.CENTER);
		Font font2 = new Font("Serif", Font.ITALIC, 16);

		// ACCOUNT
		JPanel pnAccount = new JPanel();
		pnAccount.setLayout(new BoxLayout(pnAccount, BoxLayout.Y_AXIS));

		JPanel pnAccountTitle = new JPanel();
		pnAccountTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblAccount = new JLabel(charHead + "Account" + charTail);
		// lblAccount.setPreferredSize(new Dimension(0, 20));
		lblAccount.setFont(font2);
		pnAccountTitle.add(lblAccount);
		pnAccount.add(pnAccountTitle);

		pnAccountNameMain = new JPanel();
		pnAccountNameMain.setLayout(new BoxLayout(pnAccountNameMain, BoxLayout.Y_AXIS));
		JPanel pnAccountName = new JPanel();
		pnAccountName.setLayout(new FlowLayout());
		txtAccountName = new JTextField("Account Name", 35);
		txtAccountName.setDocument(new JTextFieldLimit(30));
		txtAccountName.setText("Account Name");
		pnAccountName.add(txtAccountName);
		pnAccountNameMain.add(pnAccountName);
		pnAccount.add(pnAccountNameMain);

		pnAccountPasswordMain = new JPanel();
		pnAccountPasswordMain.setLayout(new BoxLayout(pnAccountPasswordMain, BoxLayout.Y_AXIS));
		JPanel pnAccountPassword1 = new JPanel();
		pnAccountPassword1.setLayout(new FlowLayout());
		txtPassword1 = new JPasswordField("Password", 35);
		txtPassword1.setEchoChar((char) 0); // cho password hiện lên
		txtPassword1.setDocument(new JTextFieldLimit(20));
		txtPassword1.setText("Password");
		pnAccountPassword1.add(txtPassword1);
		// password2 để xác nhận xem có giống với password1 không!
		JPanel pnAccountPassword2 = new JPanel();
		pnAccountPassword2.setLayout(new FlowLayout());
		txtPassword2 = new JPasswordField("Confirm password", 35);
		txtPassword2.setEchoChar((char) 0); // cho password hiện lên
		txtPassword2.setDocument(new JTextFieldLimit(20));
		txtPassword2.setText("Confirm password");
		pnAccountPassword2.add(txtPassword2);

		pnAccountPasswordMain.add(pnAccountPassword1);
		pnAccountPasswordMain.add(pnAccountPassword2);
		pnAccount.add(pnAccountPasswordMain);

		pnMain.add(pnAccount);

		// Infomation
		// NAME
		pnUserName = new JPanel();
		pnUserName.setLayout(new BoxLayout(pnUserName, BoxLayout.Y_AXIS));
		pnMain.add(pnUserName);

		JPanel pnUserNameTitle = new JPanel();
		pnUserNameTitle.setLayout(new FlowLayout(FlowLayout.LEADING));
		JLabel lblUserNameTitle = new JLabel(charHead + "User Name" + charTail);
		lblUserNameTitle.setFont(font2);
		pnUserNameTitle.add(lblUserNameTitle);
		pnUserName.add(pnUserNameTitle);

		JPanel pnUserNameInFo = new JPanel();
		pnUserNameInFo.setLayout(new FlowLayout());
		txtHo = new JTextField("First Name", 17);
		txtHo.setDocument(new JTextFieldLimit(15));
		txtHo.setText("First Name");
		txtTen = new JTextField("Last Name", 17);
		txtTen.setDocument(new JTextFieldLimit(15));
		txtTen.setText("Last Name");
		pnUserNameInFo.add(txtHo);
		pnUserNameInFo.add(txtTen);
		pnUserName.add(pnUserNameInFo);

		// DATE OF BIRTH
		JPanel pnDateOfBirth = new JPanel();
		pnDateOfBirth.setLayout(new BoxLayout(pnDateOfBirth, BoxLayout.Y_AXIS));
		pnMain.add(pnDateOfBirth);

		JPanel pnDateOfBirthTitle = new JPanel();
		pnDateOfBirthTitle.setLayout(new FlowLayout(FlowLayout.LEADING));
		JLabel lblDateOfBirthTitle = new JLabel(charHead + "Date Of Birth" + charTail);
		lblDateOfBirthTitle.setFont(font2);
		pnDateOfBirthTitle.add(lblDateOfBirthTitle);
		pnDateOfBirth.add(pnDateOfBirthTitle);
		JPanel pnDateOfBirthInFo = new JPanel();
		pnDateOfBirthInFo.setLayout(new FlowLayout());
		cboNgay = new JComboBox();
		cboNgay.setPreferredSize(new Dimension(114, 25));
		cboThang = new JComboBox();
		cboThang.setPreferredSize(new Dimension(114, 25));
		cboNam = new JComboBox();
		cboNam.setPreferredSize(new Dimension(114, 25));
		pnDateOfBirthInFo.add(cboNgay);
		pnDateOfBirthInFo.add(cboThang);
		pnDateOfBirthInFo.add(cboNam);
		pnDateOfBirth.add(pnDateOfBirthInFo);
		thietLapTG();

		// Thay đổi con trỏ của Combox
		cboNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboThang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// GENDER AND PHONE
		JPanel pnGenderAndPhoneMain = new JPanel();
		pnGenderAndPhoneMain.setLayout(new BoxLayout(pnGenderAndPhoneMain, BoxLayout.Y_AXIS));
		pnMain.add(pnGenderAndPhoneMain);
		JPanel pnGenderAndPhone = new JPanel();
		pnGenderAndPhone.setLayout(new BoxLayout(pnGenderAndPhone, BoxLayout.X_AXIS));
		pnGenderAndPhoneMain.add(pnGenderAndPhone);

		// GENDER
		pnGender = new JPanel();
		pnGender.setLayout(new BoxLayout(pnGender, BoxLayout.Y_AXIS));
		pnGenderAndPhone.add(pnGender);

		JPanel pnGenderTitle = new JPanel();
		pnGenderTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblGenderTitle = new JLabel("--Gender----");
		lblGenderTitle.setFont(font2);
		pnGenderTitle.add(lblGenderTitle);
		pnGender.add(pnGenderTitle);
		JPanel pnGenderInfo = new JPanel();
		pnGenderInfo.setLayout(new FlowLayout());
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nu");
		pnGenderInfo.add(radNam);
		pnGenderInfo.add(radNu);
		groupGender = new ButtonGroup();
		groupGender.add(radNam);
		groupGender.add(radNu);
		pnGender.add(pnGenderInfo);

		// Thay đổi con trỏ của RadioButton
		radNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radNu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		// PHONE
		pnPhone = new JPanel();
		pnPhone.setLayout(new BoxLayout(pnPhone, BoxLayout.Y_AXIS));
		pnGenderAndPhone.add(pnPhone);

		JPanel pnPhoneTitle = new JPanel();
		pnPhoneTitle.setLayout(new FlowLayout());
		JLabel lblPhoneTitle = new JLabel("----Phone-----------");
		lblPhoneTitle.setFont(font2);
		pnPhoneTitle.add(lblPhoneTitle);
		pnPhone.add(pnPhoneTitle);
		JPanel pnPhoneInfo = new JPanel();
		pnGenderInfo.setLayout(new FlowLayout());
		txtPhone = new JTextField("Phone", 17);
		txtPhone.setDocument(new JTextFieldLimit(10));
		txtPhone.setText("Phone");
		pnPhoneInfo.add(txtPhone);
		pnPhone.add(lblPhoneTitle);
		pnPhone.add(pnPhoneInfo);

		pnGenderTitle.setPreferredSize(new Dimension(0, 17));
		pnGender.setPreferredSize(pnPhone.getPreferredSize());

		// ADDRESS
		JPanel pnAddress = new JPanel();
		pnAddress.setLayout(new BoxLayout(pnAddress, BoxLayout.Y_AXIS));
		pnMain.add(pnAddress);

		JPanel pnAddressTitle = new JPanel();
		pnAddressTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblAddressTitle = new JLabel("-------Address" + charTail);
		lblAddressTitle.setFont(font2);
		pnAddressTitle.add(lblAddressTitle);
		pnAddress.add(pnAddressTitle);
		JPanel pnAddressInfo = new JPanel();
		pnAddressInfo.setLayout(new FlowLayout());
		txtAddress = new JTextArea("Address", 4, 35);
		txtAddress.setWrapStyleWord(true); // xuống dòng cả cái từ bị tràn đó
		txtAddress.setLineWrap(true); // Xuống dòng
		txtAddress.setDocument(new JTextFieldLimit(250)); // khí thêm cái này ta cần settext lại
		txtAddress.setText("Address");
		JScrollPane sc = new JScrollPane(txtAddress, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnAddressInfo.add(sc);
		pnAddress.add(lblAddressTitle);
		pnAddress.add(pnAddressInfo);

		// =====> FOOTER
		JPanel pnFooter = new JPanel();
		pnFooter.setLayout(new BoxLayout(pnFooter, BoxLayout.Y_AXIS));
		con.add(pnFooter, BorderLayout.SOUTH);

		JPanel pnKhoangTrang2 = new JPanel();
		pnKhoangTrang2.setPreferredSize(new Dimension(0, 1));
		pnKhoangTrang2.setBorder(border1);
		pnFooter.add(pnKhoangTrang2);
		JPanel pnKhoangTrang3 = new JPanel();
		pnFooter.add(pnKhoangTrang3);

		// BUTTON
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnSignUP = new JButton("Sign Up");
		ClickNotUseALT.clickOnKey(btnSignUP, "Sign Up", KeyEvent.VK_ENTER);
		btnReset = new JButton("Reset");
		btnReset.setMnemonic(KeyEvent.VK_R);
		btnReset.setToolTipText("ALT + R");
		btnCancel = new JButton("Cancel");
		pnButton.add(btnSignUP);
		pnButton.add(btnReset);
		pnButton.add(btnCancel);
		pnFooter.add(pnButton);

		// thêm icon cho button
		Image imgSignUP = new ImageIcon("img/imgSignUp/signUp.png").getImage();
		ImageIcon imgIconSignUp = new ImageIcon(imgSignUP);
		btnSignUP.setIcon(imgIconSignUp);
		Image imgReset = new ImageIcon("img/imgSignUp/reset.gif").getImage();
		ImageIcon imgIconReset = new ImageIcon(imgReset);
		btnReset.setIcon(imgIconReset);
		Image imgCancel = new ImageIcon("img/imgSignUp/cancel.png").getImage();
		ImageIcon imgIconCancel = new ImageIcon(imgCancel);
		btnCancel.setIcon(imgIconCancel);

		// Thay đổi con trỏ của button
		btnSignUP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// INTRODUCE
		JPanel pnIntroduce = new JPanel();
		pnIntroduce.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnFooter.add(pnIntroduce);

		JLabel lblIntroduce = new JLabel(" ==> Created By \"Nguyễn Mạnh Cường\"");
		lblIntroduce.setForeground(new Color(29, 119, 170));
		Font font3 = new Font("Serif", Font.HANGING_BASELINE, 18);
		lblIntroduce.setFont(font3);
		pnIntroduce.add(lblIntroduce);

		xuLyKeyMove();
	}

	private void xuLyKeyMove() {
		// TODO Auto-generated method stub
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
					txtPassword1.requestFocus();
				}
			}
		});
		txtPassword1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtPassword2.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtAccountName.requestFocus();
				}
			}
		});
		txtPassword2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtHo.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtPassword1.requestFocus();
				}
			}
		});
		txtHo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					txtTen.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtPhone.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtPassword2.requestFocus();
				}
			}
		});
		txtTen.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					txtHo.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtPhone.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtPassword2.requestFocus();
				}
			}
		});
		txtPhone.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtAddress.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtTen.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				}
			}
		});
		txtAddress.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtPhone.requestFocus();
				}
			}
		});
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

	public void cancelSignUP() {
		int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát Sign Up không!", "Xác Nhận",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION)
			//this.setVisible(false);
			this.dispose(); // tắt luôn cái jDialog này
	}
	
	public void showWindow() {
		//this.setSize(400, 625);
		this.setSize(400, 658);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		CreateNewAccountUI ui = new CreateNewAccountUI("UI");
		ui.showWindow();
	}
}
