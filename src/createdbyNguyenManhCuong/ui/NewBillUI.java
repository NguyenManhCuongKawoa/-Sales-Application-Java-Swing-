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
import java.sql.Date;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import createdbyNguyenManhCuong.connect.BillService;
import createdbyNguyenManhCuong.limit.JTextFieldLimit;
import createdbyNguyenManhCuong.model.Bill;


public class NewBillUI extends JDialog{
	
	JButton btnCreateNewInvoie, btnCancel;
	
	JTextField txtAccountName, txtBillName, txtPhone;
	JTextArea txtAddress, txtDescribe;
	
	JComboBox<String> cboNgay, cboThang, cboNam;
	
	String patternPhone = "[\\d]{10}"; // chỉ gồm các kí tự số và phải là 10 số
	
	public NewBillUI(String title) {
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
				setVisible(false);
			}
		});
		btnCreateNewInvoie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyTaoHoaDon();
			}
		});
	}

	protected void xuLyTaoHoaDon() {
		// TODO Auto-generated method stub
		boolean flag = true;
		if(txtBillName.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập ô BillName!");
			flag = false;
		}
		if(txtPhone.getText().length() != 10 || txtPhone.getText().matches(patternPhone) == false) {
			JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập ô Phone Or Không Đúng Định Dạng!");
			flag = false;
		}
		if(txtAddress.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập ô Address!");
			flag = false;
		}
		
		if(flag == true) {
			Bill bill = new Bill();
			bill.setAccountID(GetUI.loginUI.account.getId());
			bill.setBillName(txtBillName.getText());
			bill.setPhoneBill(txtPhone.getText());
			bill.setAddressNhanHang(txtAddress.getText());
			bill.setDescribeBill(txtDescribe.getText());
			
			String day = (String) cboNgay.getSelectedItem();
			String month = (String) cboThang.getSelectedItem();
			String year = (String) cboNam.getSelectedItem();
			Date date = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1, Integer.parseInt(day));
			bill.setNgayNhanHang(date);
			
			if(BillService.createNewBill(bill) == true) {
				JOptionPane.showMessageDialog(null, "Tạo Hóa Đơn Thành Công");
				GetUI.sanPhamUI.initDanhSachDonHang();
			}
			else JOptionPane.showMessageDialog(null, "Đã Gặp Sự Cố Khi Tạo Hóa Đợn");
		}
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(20, 0));
		con.add(pnLeft, BorderLayout.WEST);
		JPanel pnRight = new JPanel();
		pnRight.setPreferredSize(new Dimension(20, 0));
		con.add(pnRight, BorderLayout.EAST);

		// TITLE
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new BoxLayout(pnTitle, BoxLayout.Y_AXIS));
		JLabel lblTitleMain = new JLabel("Create New Invoie");
		Font font1 = new Font("Serif", Font.BOLD, 27);
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
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain, BorderLayout.CENTER);
		Font font2 = new Font("Serif", Font.ITALIC, 16);
		
		JPanel pnAccountName = new JPanel();
		pnMain.add(pnAccountName);
		pnAccountName.setLayout(new FlowLayout());
		JLabel lblAccountName = new JLabel("Account Name: ");
		lblAccountName.setIcon(new ImageIcon("img/imgNewBill/accountName.png"));
		lblAccountName.setFont(font2);
		pnAccountName.add(lblAccountName);
		txtAccountName = new JTextField(14);
		txtAccountName.setEditable(false);
		txtAccountName.setText(GetUI.loginUI.account.getAccountName());
		pnAccountName.add(txtAccountName);
		
		JPanel pnBillName = new JPanel();
		pnMain.add(pnBillName);
		pnBillName.setLayout(new FlowLayout());
		JLabel lblBillName = new JLabel("Bill Name: ");
		lblBillName.setIcon(new ImageIcon("img/imgNewBill/billName.png"));
		lblBillName.setFont(font2);
		pnBillName.add(lblBillName);
		txtBillName = new JTextField(14);
		txtBillName.setDocument(new JTextFieldLimit(20));
		pnBillName.add(txtBillName);
		
		JPanel pnPhone = new JPanel();
		pnMain.add(pnPhone);
		pnPhone.setLayout(new FlowLayout());
		JLabel lblPhone= new JLabel("Phone: ");
		lblPhone.setIcon(new ImageIcon("img/imgNewBill/phone.png"));
		lblPhone.setFont(font2);
		pnPhone.add(lblPhone);
		txtPhone = new JTextField(14);
		txtPhone.setDocument(new JTextFieldLimit(10));
		pnPhone.add(txtPhone);
		
		lblPhone.setPreferredSize(lblAccountName.getPreferredSize());
		lblBillName.setPreferredSize(lblAccountName.getPreferredSize());
		
		// Ngày Nhận Hàng
		JPanel pnNgayNhanHang = new JPanel();
		pnNgayNhanHang.setLayout(new BoxLayout(pnNgayNhanHang, BoxLayout.Y_AXIS));
		pnMain.add(pnNgayNhanHang);

		JPanel pnNgayNhanHangTitle = new JPanel();
		pnNgayNhanHangTitle.setLayout(new FlowLayout(FlowLayout.LEADING));
		JLabel lblNgayNhanHangTitle = new JLabel("Ngày Nhận Hàng-------------------------");
		lblNgayNhanHangTitle.setIcon(new ImageIcon("img/imgNewBill/date.png"));
		lblNgayNhanHangTitle.setFont(font2);
		pnNgayNhanHangTitle.add(lblNgayNhanHangTitle);
		pnNgayNhanHang.add(pnNgayNhanHangTitle);
		JPanel pnNgayNhanHangInFo = new JPanel();
		pnNgayNhanHangInFo.setLayout(new FlowLayout());
		cboNgay = new JComboBox();
		cboNgay.setPreferredSize(new Dimension(90, 24));
		cboThang = new JComboBox();
		cboThang.setPreferredSize(new Dimension(90, 24));
		cboNam = new JComboBox();
		cboNam.setPreferredSize(new Dimension(90, 24));
		pnNgayNhanHangInFo.add(cboNgay);
		pnNgayNhanHangInFo.add(cboThang);
		pnNgayNhanHangInFo.add(cboNam);
		pnNgayNhanHang.add(pnNgayNhanHangInFo);
		thietLapTG();
		// Thay đổi con trỏ của Combox
		cboNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboThang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		

		JPanel pnAddress = new JPanel();
		pnMain.add(pnAddress);
		pnAddress.setLayout(new BoxLayout(pnAddress, BoxLayout.Y_AXIS));
		JLabel lblAddress= new JLabel("   ----Address------------");
		lblAddress.setFont(font2);
		pnAddress.add(lblAddress);
		txtAddress = new JTextArea("Address", 4, 3);
		txtAddress.setWrapStyleWord(true); // xuống dòng cả cái từ bị tràn đó
		txtAddress.setLineWrap(true); // Xuống dòng
		txtAddress.setDocument(new JTextFieldLimit(250)); // khí thêm cái này ta cần settext lại
		txtAddress.setText("Address");
		JScrollPane scAddress = new JScrollPane(txtAddress, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnAddress.add(scAddress);

		JPanel pnDescribe = new JPanel();
		pnMain.add(pnDescribe);
		pnDescribe.setLayout(new BoxLayout(pnDescribe, BoxLayout.Y_AXIS));
		JLabel lblDescribe= new JLabel("                      -----Describe------------");
		lblDescribe.setFont(font2);
		pnDescribe.add(lblDescribe);
		txtDescribe = new JTextArea("Describe", 4, 3);
		txtDescribe.setWrapStyleWord(true); // xuống dòng cả cái từ bị tràn đó
		txtDescribe.setLineWrap(true); // Xuống dòng
		txtDescribe.setDocument(new JTextFieldLimit(250)); // khí thêm cái này ta cần settext lại
		txtDescribe.setText("Describe");
		JScrollPane scDescribe = new JScrollPane(txtDescribe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnDescribe.add(scDescribe);
		
		JPanel pnKhoangTrang4 = new JPanel();
		pnMain.add(pnKhoangTrang4);

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
		btnCreateNewInvoie = new JButton("Create");
		btnCancel = new JButton("Cancel");
		pnButton.add(btnCreateNewInvoie);
		pnButton.add(btnCancel);
		pnFooter.add(pnButton);

		// thêm icon cho button
		Image imgSignUP = new ImageIcon("img/imgNewBill/create.png").getImage();
		ImageIcon imgIconSignUp = new ImageIcon(imgSignUP);
		btnCreateNewInvoie.setIcon(imgIconSignUp);
		Image imgReset = new ImageIcon("img/imgNewBill/close.png").getImage();
		ImageIcon imgIconCancel = new ImageIcon(imgReset);
		btnCancel.setIcon(imgIconCancel);

		// Thay đổi con trỏ của button
		btnCreateNewInvoie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
	}
	private void thietLapTG() {
		// TODO Auto-generated method stub
		Calendar timeNow = Calendar.getInstance();
		int yearNow = timeNow.get(Calendar.YEAR);

		for (int i = 1; i <= 31; i++)
			cboNgay.addItem(i + "");
		for (int i = 1; i <= 12; i++)
			cboThang.addItem(i + "");
		for (int i = yearNow; i < yearNow + 3; i++)
			cboNam.addItem(i + "");
	}
	public void showWindow() {
		this.setSize(340, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		NewBillUI ui = new NewBillUI("New Bill");
		ui.showWindow();
	}
}
