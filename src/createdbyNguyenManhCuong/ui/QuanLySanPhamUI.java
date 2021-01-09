package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;


import createdbyNguyenManhCuong.connect.ProductService;
import createdbyNguyenManhCuong.limit.JTextFieldLimit;
import createdbyNguyenManhCuong.model.ProductInformation;

class PopUPDeleteProduct extends JPopupMenu {
	
	JMenuItem popUpDeleteProduct, popUpViewProduct;
	
	public PopUPDeleteProduct () {
		popUpDeleteProduct = new JMenuItem("Delete");
		popUpViewProduct =  new JMenuItem("View");
		add(popUpDeleteProduct);
		add(popUpViewProduct);
		
		popUpDeleteProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeleteProductSurface xoa = new DeleteProductSurface("Xóa Sản Phẩm", QuanLySanPhamUI.idProduct);
			}
		});
		popUpViewProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
class PopClickListener extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		if(e.isPopupTrigger()) {
			doPop(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger()) {
			doPop(e);
		}
	}
	private void doPop(MouseEvent e) {
		PopUPDeleteProduct menu = new PopUPDeleteProduct();
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}

class TinhTongTien implements Runnable {
	long price, amount, tongTien;
	
	public TinhTongTien() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				if (QuanLySanPhamUI.txtPrice != null 
						&& QuanLySanPhamUI.txtSoLuong != null 
						&& QuanLySanPhamUI.txtPrice.getText().length() > 0
						&& QuanLySanPhamUI.txtSoLuong.getText().length() > 0
						&& QuanLySanPhamUI.isNumBers(QuanLySanPhamUI.txtPrice.getText())
						&& QuanLySanPhamUI.isNumBers(QuanLySanPhamUI.txtSoLuong.getText())) {
					price = Long.parseLong(QuanLySanPhamUI.txtPrice.getText());
					amount = Long.parseLong(QuanLySanPhamUI.txtSoLuong.getText());
					tongTien = price * amount;
					if (QuanLySanPhamUI.txtTongTien == null)
						QuanLySanPhamUI.txtTongTien = new JTextField(18);
					QuanLySanPhamUI.txtTongTien.setText(tongTien + "");
				} else {
					if (QuanLySanPhamUI.txtTongTien != null)
						QuanLySanPhamUI.txtTongTien.setText("");
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
}

class DeleteProductSurface extends JDialog {
	
	 int id;
	 long gia, soLuong, tongTien;
	 String ten, nhanHieu, loai, moTa;
	 byte[] imageProduce = null;
	 
	 JButton btnXoa, btnCancel;
	 
	 public DeleteProductSurface(String title, int id) {
		 ProductInformation product = ProductService.getProduct(id);
		 
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
		 
		 init(id, product.getProductName(), product.getProductTrademark(), product.getProductType(), product.getProductPrice(),
				 product.getProductAmount(), product.getProductPrice() * product.getProductAmount(), product.getProductDescribe(), product.getProductImage());
		 addControls();
		 addEvents();

		 this.setSize(680, 400);
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setLocationRelativeTo(null);
		 this.setResizable(false);
		 this.setModal(true);
		 this.setVisible(true);
	 }
	
	public DeleteProductSurface(String title, int id, String ten, String nhanHieu, String loai, long gia, 
								long soLuong, long tongTien, String moTa, byte[] image) {
		this.setTitle(title);
		ImageIcon icon = new ImageIcon("img/icon.png"); 
		this.setIconImage(icon.getImage()); 
		init(id, ten, nhanHieu, loai, gia, soLuong, tongTien, moTa, image);
		addControls();
		addEvents();
		
		this.setSize(680, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Chắn Muốn Xóa Product Này Không?", 
						"Xác Nhận Xóa.", JOptionPane.YES_NO_OPTION);
				if(choose == JOptionPane.YES_OPTION) {
					if(ProductService.deleteProduct(id) == true) {
						JOptionPane.showMessageDialog(null, "Xóa Thành Công");
						GetUI.quanLySanPhamUI.xuLyReset();
					//	QuanLySanPhamUI.isDeletePtoduct = true;
						setVisible(false);
					}
					else { 
						JOptionPane.showMessageDialog(null, "Gặp Sự Cố Trong Khi Xóa");
						//QuanLySanPhamUI.isDeletePtoduct = false;
					}
				}
				//else QuanLySanPhamUI.isDeletePtoduct = false;
				
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

	private void init(int id, String ten, String nhanHieu, String loai, long gia, 
			long soLuong, long tongTien, String moTa, byte[] image) {
		// TODO Auto-generated method stub
		this.id = id;
		this.ten = ten;
		this.nhanHieu = nhanHieu;
		this.loai = loai;
		this.gia = gia;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
		this.moTa = moTa;
		this.imageProduce = image;
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(40, 0));
		JPanel pnRight = new JPanel();
		pnRight.setPreferredSize(new Dimension(40, 0));
		JPanel pnTop = new JPanel();
		pnTop.setPreferredSize(new Dimension(0, 30));
		JPanel pnButtom = new JPanel();
		pnButtom.setPreferredSize(new Dimension(0, 55));
		
		con.add(pnLeft, BorderLayout.WEST);
		con.add(pnRight, BorderLayout.EAST);
		con.add(pnTop, BorderLayout.NORTH);
		con.add(pnButtom, BorderLayout.SOUTH);
		
		JPanel pnTrangChuRight = new JPanel();
		pnTrangChuRight.setLayout(new BorderLayout());
		con.add(pnTrangChuRight, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMain = new JPanel();
		pnTrangChuRightMain.setLayout(new BorderLayout());
		pnTrangChuRight.add(pnTrangChuRightMain, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMainLeft = new JPanel();
		pnTrangChuRightMainLeft.setPreferredSize(new Dimension(260, 0));
		pnTrangChuRightMainLeft.setLayout(new BoxLayout(pnTrangChuRightMainLeft, BoxLayout.Y_AXIS));
		pnTrangChuRightMain.add(pnTrangChuRightMainLeft, BorderLayout.WEST);
		
		JPanel  pnTrangChuRightTop = new JPanel();
		JLabel lblTitle = new JLabel("Xóa Sản Phẩm"); 
		lblTitle.setForeground(Color.RED);
		Font fontTitle = new Font("Serif", Font.BOLD, 35);
		lblTitle.setFont(fontTitle);
		pnTrangChuRightTop.add(lblTitle);
		pnTrangChuRightMainLeft.add(pnTrangChuRightTop);
		
		JPanel pnTrangChuRightTitle = new JPanel();
		pnTrangChuRightTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTrangChuRightTitle = new JLabel("Chi Tiết Sản Phẩm");
		lblTrangChuRightTitle.setForeground(new Color(79, 213, 157));
		Font fontlblTrangChuRightTitle = new Font("Serif", Font.BOLD, 20);
		lblTrangChuRightTitle.setFont(fontlblTrangChuRightTitle);
		pnTrangChuRightTitle.add(lblTrangChuRightTitle);
		pnTrangChuRightMainLeft.add(pnTrangChuRightTitle);
		
		JLabel lblID1 = new JLabel("  < ID: ");
		lblID1.setForeground(Color.RED);
		JLabel lblID2 = new JLabel(" >");
		lblID2.setForeground(Color.RED);
		JTextField txtID = new JTextField(3);
		txtID.setText(String.valueOf(id));
		txtID.setEditable(false);
		pnTrangChuRightTitle.add(lblID1);
		pnTrangChuRightTitle.add(txtID);
		pnTrangChuRightTitle.add(lblID2);
		
		
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new BoxLayout(pnTen, BoxLayout.Y_AXIS));
		JPanel pnTenMain = new JPanel();
		pnTenMain.setLayout(new FlowLayout());
		JLabel lblTen = new JLabel("Name: ");
		pnTenMain.add(lblTen);
		JTextField txtTen = new JTextField(16);
		txtTen.setText(ten);
		txtTen .setEditable(false);
		pnTenMain.add(txtTen);
		pnTen.add(pnTenMain);
		pnTrangChuRightMainLeft.add(pnTen);
		
		JPanel pnNhanHieu = new JPanel();
		pnNhanHieu.setLayout(new BoxLayout(pnNhanHieu, BoxLayout.Y_AXIS));
		JPanel pnNhanHieuMain = new JPanel();
		pnNhanHieuMain.setLayout(new FlowLayout());
		JLabel lblNhanHieu = new JLabel("Nhãn Hiệu: ");
		pnNhanHieuMain.add(lblNhanHieu);
		JTextField txtNhanHieu = new JTextField(6);
		txtNhanHieu.setText(nhanHieu);
		txtNhanHieu.setEditable(false);
		pnNhanHieuMain.add(txtNhanHieu);
		pnNhanHieu.add(pnNhanHieuMain);
		pnTrangChuRightMainLeft.add(pnNhanHieu);
		
		JPanel pnType = new JPanel();
		JComboBox<String> cboLoai = new JComboBox<String>();
		cboLoai.setPreferredSize(new Dimension(99, 27));
		cboLoai.addItem(loai);
		cboLoai.setEnabled(false);
		pnNhanHieuMain.add(cboLoai);
		
		JPanel pnPrice = new JPanel();
		pnPrice.setLayout(new BoxLayout(pnPrice, BoxLayout.Y_AXIS));
		JPanel pnPriceMain = new JPanel();
		pnPriceMain.setLayout(new FlowLayout());
		JLabel lblPrice = new JLabel("Price($): ");
		pnPriceMain.add(lblPrice);
		JTextField txtPrice = new JTextField(16);
		txtPrice.setEditable(false);
		txtPrice.setText(String.valueOf(gia));
		pnPriceMain.add(txtPrice);
		pnPrice.add(pnPriceMain);
		pnTrangChuRightMainLeft.add(pnPrice);
		
		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new BoxLayout(pnSoLuong, BoxLayout.Y_AXIS));
		JPanel pnSoLuongMain = new JPanel();
		pnSoLuongMain.setLayout(new FlowLayout());
		JLabel lblSoLuong = new JLabel("Số Lượng: ");
		pnSoLuongMain.add(lblSoLuong);
		JTextField txtSoLuong = new JTextField(16);
		txtSoLuong.setText(String.valueOf(soLuong));
		txtSoLuong.setEditable(false);
		pnSoLuongMain.add(txtSoLuong);
		pnSoLuong.add(pnSoLuongMain);
		pnTrangChuRightMainLeft.add(pnSoLuong);
		
		JPanel pnTongTien = new JPanel();
		pnTongTien.setLayout(new BoxLayout(pnTongTien, BoxLayout.Y_AXIS));
		JPanel pnTongTienMain = new JPanel();
		pnTongTienMain.setLayout(new FlowLayout());
		JLabel lblTongTien = new JLabel("Tổng Tiền($): ");
		pnTongTienMain.add(lblTongTien);
		JTextField txtTongTien = new JTextField(16);
		txtTongTien.setText(String.valueOf(tongTien));
		txtTongTien.setEditable(false);
		pnTongTienMain.add(txtTongTien);
		pnTongTien.add(pnTongTienMain);
		pnTrangChuRightMainLeft.add(pnTongTien);
		
		lblTen.setPreferredSize(lblTongTien.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTongTien.getPreferredSize());
		lblPrice.setPreferredSize(lblTongTien.getPreferredSize());
		lblNhanHieu.setPreferredSize(lblTongTien.getPreferredSize());
		
		JPanel pnTrangChuRightMainCenter = new JPanel();
		pnTrangChuRightMainCenter.setPreferredSize(new Dimension(1, 0));
		pnTrangChuRightMain.add(pnTrangChuRightMainCenter, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMainRight = new JPanel();
		pnTrangChuRightMainRight.setPreferredSize(new Dimension(297, 0));
		pnTrangChuRightMainRight.setLayout(new BoxLayout(pnTrangChuRightMainRight, BoxLayout.Y_AXIS));
		pnTrangChuRightMain.add(pnTrangChuRightMainRight, BorderLayout.EAST);
		
		JPanel pnImgProduct = new JPanel();
		pnImgProduct.setPreferredSize(new Dimension(120, 101));
		pnImgProduct.setLayout(new BorderLayout());
		pnTrangChuRightMainRight.add(pnImgProduct);
		
		JPanel pnImgProductTop = new JPanel();
		pnImgProductTop.setPreferredSize(new Dimension(0, 10));
		pnImgProduct.add(pnImgProductTop, BorderLayout.NORTH);
		JPanel pnImgProductLeft = new JPanel();
		pnImgProductLeft.setPreferredSize(new Dimension(84, 0));
		pnImgProduct.add(pnImgProductLeft, BorderLayout.WEST);
		JPanel pnImgProductRight = new JPanel();
		pnImgProductRight.setPreferredSize(new Dimension(83, 0));
		pnImgProduct.add(pnImgProductRight, BorderLayout.EAST);
		
		JPanel pnHeadRightAvatarMain = new JPanel();
		Border borderHeadRightAvatarMain = BorderFactory.createLineBorder(Color.BLACK, 3);
		pnHeadRightAvatarMain.setBorder(borderHeadRightAvatarMain);
		pnHeadRightAvatarMain.setLayout(new BorderLayout());
		pnImgProduct.add(pnHeadRightAvatarMain, BorderLayout.CENTER);

		Image img = new ImageIcon(imageProduce).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
		JPanel pnHeadRightAvatar = new BackGroundImage(img);
		pnHeadRightAvatarMain.add(pnHeadRightAvatar);
		
		JPanel pnlblAvatar = new JPanel();
		JLabel lblAvatar = new JLabel("IMAGE");
		Font fontavatar = new Font("Serif", Font.BOLD, 18);
		lblAvatar.setFont(fontavatar);
		lblAvatar.setForeground(Color.BLUE);
		Border borderlblAvatar = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
		lblAvatar.setBorder(borderlblAvatar);
		pnlblAvatar.add(lblAvatar);
		pnImgProduct.add(pnlblAvatar, BorderLayout.SOUTH);
		
		JPanel pnMoTa = new JPanel();
		pnMoTa.setLayout(new BorderLayout());
		pnMoTa.setPreferredSize(new Dimension(0, 16));
		JLabel lblMoTa = new JLabel("   Mô Tả");
		pnMoTa.add(lblMoTa, BorderLayout.NORTH);
		
		JTextArea txtMoTa = new JTextArea();
		txtMoTa.setText(moTa);
		txtMoTa.setEditable(false);
		JScrollPane scMoTa = new JScrollPane(txtMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txtMoTa.setLineWrap(true);
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setEditable(false);
		pnMoTa.add(scMoTa);
		pnTrangChuRightMainRight.add(pnMoTa);
		
		JPanel pnMoTaButtom = new JPanel();
		pnMoTaButtom.setPreferredSize(new Dimension(0, 10));
		pnTrangChuRightMainRight.add(pnMoTaButtom);
		
		
		btnXoa = new JButton("  Xóa");
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setPreferredSize(new Dimension(110, 45));
		Image imgXoa = new ImageIcon("img/imgProduct/ThungRac.jpg").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		btnXoa.setIcon(new ImageIcon(imgXoa));
		pnButtom.add(btnXoa);
		
		btnCancel = new JButton("  Cancel");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgCancel = new ImageIcon("img/imgProduct/delete.png").getImage();
		btnCancel.setIcon(new ImageIcon(imgCancel));
		btnCancel.setPreferredSize(new Dimension(110, 45));
		pnButtom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JPanel pnButtonKhoangTrang1 = new JPanel();
		pnButtonKhoangTrang1.setPreferredSize(new Dimension(20, 0));
		pnButtom.add(pnButtonKhoangTrang1);
		pnButtom.add(btnCancel);
		
		JPanel pnButtonKhoangTrang = new JPanel();
		pnButtonKhoangTrang.setPreferredSize(new Dimension(50, 0));
		pnButtom.add(pnButtonKhoangTrang);
	}
}

public class QuanLySanPhamUI extends JFrame {

	int width = 1000;
	int height = 600;
	
	public JMenuItem popUpDeleteProduct, popUpViewProduct;
	
	JMenuBar menuBar;
	JMenu menuMenu, menuSetting, menuHelp;
	JMenuItem menuMenuTrangChu, menuMenuAccount, menuMenuBill,menuMenuIntroduce, menuMenuChangePassword, menuMenuLogOut;
	JMenuItem menuSettingBackground, menuSettingReset;
	JMenuItem menuHelpWelcome;
	
	JFileChooser fileChooserImages;
	
	JCheckBox cboShoes, cboBalls, cboAccessories;
	
	DefaultTableModel dtmSanPham;
	JTable tbSanPham;
	
	public static int idProduct;
	public static JTextField txtPrice, txtSoLuong, txtTongTien;
	JTextField txtTen, txtNhanHieu;
	JTextField txtID;
	JComboBox<String> cboLoai;
	JTextArea txtMoTa;
	
	String urlProductDefault = "img/imgProduct/productDefault.png";
	String urlImageProduce = null;
	byte[] imgProduct = null;
	
	JButton btnXoa, btnSua, btnThem, btnReset, btnTimKiem, btnTrangChu;
	JTextField txtTimKiem;
	
	JRadioButton radSXTheoTen, radSXTheoPrice, radSXTheoAmount, radDefault;
	ButtonGroup groundSX;
	
	JPanel pnHeadRightAvatarMain;
	JPanel pnHeadRightAvatar;
	JLabel lblAvatar;
	
	
	JPanel pnTen, pnErrorTen = null;
	JPanel pnNhanHieu, pnErrorNhanHieu = null;
	JPanel pnPrice, pnErrorPrice = null;
	JPanel pnSoLuong, pnErrorSoLuong = null;
	
	JLabel lblErrorTen = null, lblErrorNhanHieu = null, lblErrorPrice = null, lblErrorSoLuong = null;
	String strErrorTen =      new String("Vui Lòng Nhập Tên!");
	String strErrorNhanHieu = new String("Vui Lòng Nhập Nhãn Hiệu!");
	String strErrorPrice =    new String("Vui Lòng Nhập Price OR Price Sai!");
	String strErrorSoLuong =  new String("Vui Lòng Nhập Amount OR Amount Sai!");
	
	String imgMenu = "img/imgMenu/menu.png";
	String imgMenuSetting = "img/imgMenu/setting.png";
	String imgMenuHelp = "img/imgMenu/help.png";
	String imgMenuHome = "img/imgMenu/home.png";
	String imgMenuAccount = "img/imgMenu/account.png";
	String imgMenuBill = "img/imgMenu/bill.png";
	String imgMenuIntroduce = "img/imgMenu/tim.png";
	String imgMenuChangePassword = "img/imgMenu/keyNew.png";
	String imgMenuLogOut = "img/imgMenu/logOut.png";
	String imgMenuSettingBackground = "img/imgMenu/background.png";
	String imgMenuSettingReset = "img/imgMenu/reset.gif";
	
	//public static boolean isDeletePtoduct = false;

	public QuanLySanPhamUI(String title) {
		// this.setTitle(title);
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
		GetUI.setQuanLySanPhamUI(this);
		addControls();
		addEvents();
		TinhTongTien  tinh = new TinhTongTien();
	}

	public void addEvents() {
		// TODO Auto-generated method stub
		menuMenuTrangChu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				GetUI.accountAdminUI.setVisible(true);
			}
		});
		btnTrangChu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				GetUI.accountAdminUI.setVisible(true);
			}
		});
		menuMenuAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountUI ui = new AccountUI("User", GetUI.loginUI.account);
				ui.showWindow();
			}
		});
		menuMenuBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				QuanLyDonHangUI ui = new QuanLyDonHangUI("Quản Lý Đơn Hàng");
				ui.showWindow();
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
				// TODO Auto-generated method stub
				setVisible(false);
				GetUI.loginUI.setVisible(true);
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
				int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Đổi Ảnh Sản Phẩm Hay Không?", "Xác Nhận",
						JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION) {
					handleChangeAvatar(e);
				}
			}
		});
		cboShoes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowProduct();
			}
		});
		cboBalls.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowProduct();
			}
		});
		cboAccessories.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowProduct();
			}
		});	
		tbSanPham.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				xuLyChonSanPhamTuBang();
			}
		});
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLySua();
			}
		});
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyThem();
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyXoa();
			}
		});
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyReset();
			}
		});
		txtTen.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtNhanHieu.requestFocus();
				}
			}
		});
		txtNhanHieu.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtPrice.requestFocus();
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtTen.requestFocus();
				}
			}
		});
		txtPrice.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtSoLuong.requestFocus();
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtNhanHieu.requestFocus();
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					txtMoTa.requestFocus();
				}
			}
		});
		txtSoLuong.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtPrice.requestFocus();
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					txtMoTa.requestFocus();
				}
			}
		});
		txtMoTa.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtNhanHieu.requestFocus();
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					txtSoLuong.requestFocus();
				}
			}
		});
		radDefault.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(radDefault.isSelected()) {
					xuLyShowProduct();
				}
			}
		});
		radSXTheoTen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowProduct();
			}
		});
		radSXTheoPrice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowProduct();
			}
		});
		radSXTheoAmount.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowProduct();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyTimKiem();
			}
		});
	}
	protected void xuLyTimKiem() {
		// TODO Auto-generated method stub
		Image imgTimKiem = new ImageIcon("img/imgProduct/timKiem.png").getImage();
		String tenSP = (String) JOptionPane.showInputDialog(null, "Vui lòng Nhập Tên Sản Phẩm Cần tìm kiếm", "Tìm Kiếm", 
				JOptionPane.OK_CANCEL_OPTION, new ImageIcon(imgTimKiem), null, "Nhập Tên Sản Phẩm");
		if (tenSP != null) {
			if (tenSP.length() == 0) {
				int choose = JOptionPane.showConfirmDialog(null, "Bạn chưa Nhập Tên! Bạn có muốn Nhận lại hay không?",
						"Xác Nhận", JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION)
					xuLyTimKiem();
			} else {
				ArrayList<ProductInformation> arrProduct = ProductService.getProductFromName(tenSP);
				if (arrProduct.size() == 0)
					JOptionPane.showMessageDialog(null, "Tên Sản Phẩm Này Không Có Trong Danh Sách!");
				else {
					dtmSanPham.setRowCount(0);
					for (int i = 0; i < arrProduct.size(); i++) {
						Object[] o = new Object[5];
						o[0] = arrProduct.get(i).getId();
						o[1] = arrProduct.get(i).getProductName();
						o[2] = arrProduct.get(i).getProductTrademark();
						o[3] = arrProduct.get(i).getProductPrice();
						o[4] = arrProduct.get(i).getProductAmount();
						dtmSanPham.addRow(o);
					}
				}
			}
		}
	}

	protected void xuLyChonSanPhamTuBang() {
		// TODO Auto-generated method stub
		
		if(pnErrorTen != null) {
			pnTen.remove(pnErrorTen);
			pnErrorTen = null;
		}
		if(pnErrorNhanHieu != null) {
			pnNhanHieu.remove(pnErrorNhanHieu);
			pnErrorNhanHieu = null;
		}

		if(pnErrorPrice != null) {
			pnPrice.remove(pnErrorPrice);
			pnErrorPrice = null;
		}
		if(pnErrorSoLuong != null) {
			pnSoLuong.remove(pnErrorSoLuong);
			pnErrorSoLuong = null;
		}
		this.setVisible(true);
		
		int row = tbSanPham.getSelectedRow();
		if(row != -1) {
			ProductInformation product = ProductService.getProduct((int)tbSanPham.getValueAt(row, 0));
			idProduct = product.getId();
			txtID.setText(String.valueOf(idProduct));
			txtTen.setText(product.getProductName());
			txtNhanHieu.setText(product.getProductTrademark());
			txtPrice.setText(String.valueOf(product.getProductPrice()));
			txtSoLuong.setText(String.valueOf(product.getProductAmount()));
			cboLoai.setSelectedItem(product.getProductType());
			txtMoTa.setText(product.getProductDescribe());
			
			byte[] image = product.getProductImage();
			Image img;
			if(image == null) {
				img = new ImageIcon(urlProductDefault).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
				try {
					File f = new File(urlProductDefault);
					FileInputStream fis = new FileInputStream(f);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readNum;(readNum = fis.read(buf)) != -1;) {
						bos.write(buf, 0, readNum);
					}
					imgProduct = bos.toByteArray();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				img = new ImageIcon(image).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
				imgProduct = image; // biến này để lưu vào cơ sử dữ liệu
			}
			pnHeadRightAvatarMain.remove(pnHeadRightAvatar);
			pnHeadRightAvatar = new BackGroundImage(img);
			pnHeadRightAvatar.setToolTipText("IMAGE<124x124>");
			pnHeadRightAvatarMain.add(pnHeadRightAvatar);
			setVisible(true);
			
			txtTongTien.setText(String.valueOf(product.getProductPrice() * product.getProductAmount()));
			
			
		}
	}

	public void xuLyReset() {
		// TODO Auto-generated method stub
		idProduct = -1;
		
		txtTen.setText(null);
		txtNhanHieu.setText(null);
		cboLoai.setSelectedItem("Shoes");
		txtPrice.setText(null);
		txtSoLuong.setText(null);
		txtTongTien.setText(null);
		txtMoTa.setText(null);
		
		try {
			File f = new File(urlProductDefault);
			FileInputStream fis = new FileInputStream(f);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for(int readNum;(readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			imgProduct = bos.toByteArray();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		pnHeadRightAvatarMain.remove(pnHeadRightAvatar);
		Image img = new ImageIcon(urlProductDefault).getImage();
		pnHeadRightAvatar = new BackGroundImage(img);
		pnHeadRightAvatar.setToolTipText("IMAGE<124x124>");
		pnHeadRightAvatarMain.add(pnHeadRightAvatar);
		
		if(pnErrorTen != null) {
			pnTen.remove(pnErrorTen);
			pnErrorTen = null;
		}
		if(pnErrorNhanHieu != null) {
			pnNhanHieu.remove(pnErrorNhanHieu);
			pnErrorNhanHieu = null;
		}

		if(pnErrorPrice != null) {
			pnPrice.remove(pnErrorPrice);
			pnErrorPrice = null;
		}
		if(pnErrorSoLuong != null) {
			pnSoLuong.remove(pnErrorSoLuong);
			pnErrorSoLuong = null;
		}
		this.setVisible(true);
		
	}

	protected void xuLyXoa() {
		// TODO Auto-generated method stub
		if(isInvalidInformation() == true && idProduct > -1) {
			
			int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Chắn Chọn ID: " + idProduct + " Của Product Này Để Xóa Không?", 
					"Xác Nhận Chọn.", JOptionPane.YES_NO_OPTION);
			if(choose == JOptionPane.YES_OPTION) {
				/*DeleteProductSurface ui = new DeleteProductSurface("Xóa Product", txtID.getText(), txtTen.getText(), txtNhanHieu.getText(),
						cboLoai.getSelectedItem().toString(), txtPrice.getText(), txtSoLuong.getText(), txtTongTien.getText(),
						txtMoTa.getText(), imgProduct);*/
				DeleteProductSurface ui = new DeleteProductSurface("Xóa Product", idProduct);
			}
		}
		else JOptionPane.showMessageDialog(null, "Bạn Đang Trong Quá Trình Thêm(Sửa) Hoặc Bạn Chưa Chọn Product Cần Xóa!");
		xuLyShowProduct();
	}

	protected void xuLyThem() {
		// TODO Auto-generated method stub
		if(isInvalidInformation() == true) {
			int chooseAdd = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm Sản Phẩm này không?", "Xác Nhận Thêm", JOptionPane.YES_NO_OPTION);
			if(chooseAdd == JOptionPane.YES_OPTION) {
				ProductInformation product = new ProductInformation();
				product.setProductName(txtTen.getText());
				product.setProductTrademark(txtNhanHieu.getText());
				product.setProductType(String.valueOf(cboLoai.getSelectedItem()));
				product.setProductPrice(Integer.parseInt(txtPrice.getText()));
				product.setProductAmount(Integer.parseInt(txtSoLuong.getText()));
				product.setProductDescribe(txtMoTa.getText());
				product.setProductImage(imgProduct);
				
				if(ProductService.insertProduct(product) == true) {
					JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
					xuLyShowProduct();
				}
			}
		}
	}

	private boolean isInvalidInformation() {
		
		boolean flag = false;
		if(txtTen.getText().length() == 0) {

			if(pnErrorTen == null) {
				pnErrorTen = new JPanel();
				pnErrorTen.setLayout(new FlowLayout(FlowLayout.LEFT));
				lblErrorTen = new JLabel(strErrorTen);
				lblErrorTen.setForeground(Color.RED);
				pnErrorTen.add(lblErrorTen);
				pnTen.add(pnErrorTen);
				JOptionPane.showMessageDialog(null, strErrorTen);
			}
			flag = true;
		}
		else {
			if(pnErrorTen != null) {
				pnTen.remove(pnErrorTen);
				pnErrorTen = null;
			}
		}
		if(txtNhanHieu.getText().length() == 0) { 

			if(pnErrorNhanHieu == null) {
				pnErrorNhanHieu = new JPanel();
				pnErrorNhanHieu.setLayout(new FlowLayout(FlowLayout.LEFT));
				lblErrorNhanHieu = new JLabel(strErrorNhanHieu);
				lblErrorNhanHieu.setForeground(Color.RED);
				pnErrorNhanHieu.add(lblErrorNhanHieu);
				pnNhanHieu.add(pnErrorNhanHieu);
				JOptionPane.showMessageDialog(null, strErrorNhanHieu);
			}
			flag = true;
		}
		else {
			if(pnErrorNhanHieu != null) {
				pnNhanHieu.remove(pnErrorNhanHieu);
				pnErrorNhanHieu = null;
			}
		}
		if((txtPrice.getText().length() == 0 || isNumBers(txtPrice.getText()) == false)) {

			if(pnErrorPrice == null) {
				pnErrorPrice = new JPanel();
				pnErrorPrice.setLayout(new FlowLayout(FlowLayout.LEFT));
 				lblErrorPrice = new JLabel(strErrorPrice);
 				lblErrorPrice.setForeground(Color.RED);
 				pnErrorPrice.add(lblErrorPrice);
 				pnPrice.add(pnErrorPrice);
			}
			JOptionPane.showMessageDialog(null, strErrorPrice);
			flag = true;
		}
		else {
			if(pnErrorPrice != null) {
				pnPrice.remove(pnErrorPrice);
				pnErrorPrice = null;
			}
		}
		if((txtSoLuong.getText().length() == 0 || isNumBers(txtSoLuong.getText()) == false)) {

			if(pnErrorSoLuong == null) {
				pnErrorSoLuong = new JPanel();
				pnErrorSoLuong.setLayout(new FlowLayout(FlowLayout.LEFT));
				lblErrorSoLuong = new JLabel(strErrorSoLuong);
				lblErrorSoLuong.setForeground(Color.RED);
				pnErrorSoLuong.add(lblErrorSoLuong);
				pnSoLuong.add(pnErrorSoLuong);
			}
			JOptionPane.showMessageDialog(null, strErrorSoLuong);
			flag = true;
		}
		else {
			if(pnErrorSoLuong != null) {
				pnSoLuong.remove(pnErrorSoLuong);
				pnErrorSoLuong = null;
			}
		}
		this.setVisible(true);
		if(flag == false) return true;
		return false;
	}

	public static boolean isNumBers(String xau) {
		// TODO Auto-generated method stub
		if(xau.matches("[\\d]+") == true) return true;
		return false;
	}

	protected void xuLySua() {
		// TODO Auto-generated method stub
		if(isInvalidInformation() == true && idProduct > -1) {
			
			int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Chắn Chọn ID: " + idProduct + " Của Product Này Đế Sửa Không?", 
					"Xác Nhận Chọn.", JOptionPane.YES_NO_OPTION);
			if(choose == JOptionPane.YES_OPTION) {
				ProductInformation product = new ProductInformation();
				product.setId(idProduct);
				product.setProductName(txtTen.getText());
				product.setProductTrademark(txtNhanHieu.getText());
				product.setProductType(String.valueOf(cboLoai.getSelectedItem()));
				String price = txtPrice.getText().split("\\$")[0];
				price = price.strip();
				product.setProductPrice(Integer.parseInt(price));
				product.setProductAmount(Integer.parseInt(txtSoLuong.getText()));
				product.setProductDescribe(txtMoTa.getText());
				product.setProductImage(imgProduct);
				
				if(ProductService.updateProduct(idProduct, product) == true) {
					JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
					xuLyShowProduct();
				}
			}
		}
	}

	protected void xuLyShowProduct() {
		// TODO Auto-generated method stub
		ArrayList<ProductInformation> arrProduct = new ArrayList<ProductInformation>();
		if(cboBalls.isSelected() && cboShoes.isSelected() && cboAccessories.isSelected()) {
			ArrayList<ProductInformation> arrProduct1 = ProductService.getAllProduct();
			arrProduct.addAll(arrProduct1);
		}
		else {
			dtmSanPham.setRowCount(0);
			if(cboShoes.isSelected() == true) {
				ArrayList<ProductInformation> arrProduct1 = ProductService.getAllProductFromType("Shoes");
				arrProduct.addAll(arrProduct1);
			}
			if(cboBalls.isSelected() == true) {
				ArrayList<ProductInformation> arrProduct1 = ProductService.getAllProductFromType("Balls");
				arrProduct.addAll(arrProduct1);
			}
			if(cboAccessories.isSelected() == true) {
				ArrayList<ProductInformation> arrProduct1 = ProductService.getAllProductFromType("Accessories");
				arrProduct.addAll(arrProduct1);
			}
		}
		dtmSanPham.setRowCount(0);
		if(arrProduct.size() != 0) {

			if(radSXTheoTen.isSelected()) {
				ProductInformation.typeCompare = ProductInformation.TypeCompare.name;
			}
			else if(radSXTheoPrice.isSelected()) {
				ProductInformation.typeCompare = ProductInformation.TypeCompare.price;
			}
			else if(radSXTheoAmount.isSelected()) {
				ProductInformation.typeCompare = ProductInformation.TypeCompare.amount;
			}
			else ProductInformation.typeCompare = ProductInformation.TypeCompare.macDinh;
			
			if(ProductInformation.typeCompare != ProductInformation.TypeCompare.macDinh) {
				Collections.sort(arrProduct);
			}
			for(int i = 0;i<arrProduct.size();i++) {
				Object[] o = new Object[5];
				o[0] = arrProduct.get(i).getId();
				o[1] = arrProduct.get(i).getProductName();
				o[2] = arrProduct.get(i).getProductTrademark();
				o[3] = arrProduct.get(i).getProductPrice();
				o[4] = arrProduct.get(i).getProductAmount();
				dtmSanPham.addRow(o);
			}
		}
	}

	protected void handleChangeAvatar(MouseEvent e) {
		// TODO Auto-generated method stub
		urlImageProduce = getURLImageFromFile();
		if (urlImageProduce != null) {
			
			try {
				File f = new File(urlImageProduce);
				FileInputStream fis = new FileInputStream(f);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				for(int readNum;(readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
				imgProduct = bos.toByteArray();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			pnHeadRightAvatarMain.remove(pnHeadRightAvatar);
			Image img = new ImageIcon(urlImageProduce).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
			pnHeadRightAvatar = new BackGroundImage(img);
			pnHeadRightAvatar.setToolTipText("IMAGE<124x124>");
			pnHeadRightAvatarMain.add(pnHeadRightAvatar);
			setVisible(true);
		} else {
			int choose1 = JOptionPane.showConfirmDialog(null,
					"File này không hợp lệ or Bạn chưa chọn File! - Bạn Có muốn chọn lại không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (choose1 == JOptionPane.YES_OPTION) {
				handleChangeAvatar(e);
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

		createMenu();
		initFileChooser();
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnTitle = new JPanel();
		Border borderButtom = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK);
		pnTitle.setBorder(borderButtom);
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitle = new JLabel("Quản Lý Sản Phẩm");
		Font fontlblTitle = new Font("Serif", Font.BOLD, 35);
		lblTitle.setFont(fontlblTitle);
		lblTitle.setForeground(Color.PINK);
		pnTitle.add(lblTitle);
		con.add(pnTitle, BorderLayout.NORTH);
		
		JPanel pnTrangChu = new JPanel();
		pnTrangChu.setLayout(new BorderLayout());
		con.add(pnTrangChu, BorderLayout.CENTER);
		
		JPanel pnProductType = new JPanel();
		//pnProductType.setBorder(borderButtom);
		pnTrangChu.add(pnProductType, BorderLayout.NORTH);
		//pnProductType.setPreferredSize(new Dimension(0, 100));
		pnProductType.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		Font fontProduct = new Font("Serif", Font.BOLD, 20);
		
		JPanel pnShoes = new JPanel();
		pnShoes.setLayout(new FlowLayout());
		pnShoes.setPreferredSize(new Dimension(190, 55));
		pnProductType.add(pnShoes);
		JLabel lblShoes = new JLabel(" <Shoes>");
		lblShoes.setFont(fontProduct);
		Image imgShoes = new ImageIcon("img/imgProduct/iconShoe.png").getImage();
		lblShoes.setIcon(new ImageIcon(imgShoes));
		pnShoes.add(lblShoes);
		cboShoes = new JCheckBox();
		cboShoes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnShoes.add(cboShoes);
		
		JPanel pnBalls = new JPanel();
		pnBalls.setLayout(new FlowLayout());
		pnBalls.setPreferredSize(new Dimension(190, 55));
		//pnBalls.setBackground(Color.red);
		pnProductType.add(pnBalls);
		JLabel lblBalls = new JLabel(" <Balls>");
		lblBalls.setFont(fontProduct);
		Image imgBalls = new ImageIcon("img/imgProduct/iconBasketball.png").getImage();
		lblBalls.setIcon(new ImageIcon(imgBalls));
		pnBalls.add(lblBalls);
		cboBalls = new JCheckBox();
		cboBalls.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnBalls.add(cboBalls);
		
		JPanel pnAccessories = new JPanel();
		pnAccessories.setLayout(new FlowLayout());
		pnAccessories.setPreferredSize(new Dimension(240, 55));
		//pnAccessories.setBackground(Color.BLUE);
		pnProductType.add(pnAccessories);
		JLabel lblAccessories = new JLabel(" <Accessories>");
		lblAccessories.setFont(fontProduct);
		Image imgAccessories = new ImageIcon("img/imgProduct/iconAccessories.jpg").getImage();
		lblAccessories.setIcon(new ImageIcon(imgAccessories));
		pnAccessories.add(lblAccessories);
		cboAccessories = new JCheckBox();
		cboAccessories.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnAccessories.add(cboAccessories);
		
		Border b = BorderFactory.createLineBorder(Color.black);
		
		JPanel pnTrangChuLeft = new JPanel();
		//pnTrangChuLeft.setBorder(b);
		//pnTrangChuLeft.setBackground(Color.white);
		pnTrangChuLeft.setPreferredSize(new Dimension(400, 0));
		pnTrangChuLeft.setLayout(new BorderLayout());
		
		JPanel pnDanhSachSanPham = new JPanel();
		pnDanhSachSanPham.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblDanhSachSanPham = new JLabel("Danh Sách Sản Phẩm");
		Font fontlblTitleDSSanPham = new Font("Serif", Font.BOLD, 25);
		lblDanhSachSanPham.setForeground(new Color(79, 213, 157));
		lblDanhSachSanPham.setFont(fontlblTitleDSSanPham);
		pnDanhSachSanPham.add(lblDanhSachSanPham);
		pnTrangChuLeft.add(pnDanhSachSanPham, BorderLayout.NORTH);
		
		dtmSanPham = new DefaultTableModel();
		tbSanPham = new JTable(dtmSanPham);
		dtmSanPham.addColumn("ID");
		dtmSanPham.addColumn("NAME");
		dtmSanPham.addColumn("TRADEMARK");
		dtmSanPham.addColumn("PRICE");
		dtmSanPham.addColumn("AMOUNT");
		JScrollPane sc = new JScrollPane(tbSanPham, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTrangChuLeft.add(sc, BorderLayout.CENTER);
		pnTrangChu.add(pnTrangChuLeft, BorderLayout.WEST);
		
		initTableWithAllProduct();
		
		tbSanPham.getColumn("ID").setPreferredWidth(10);
		tbSanPham.getColumn("NAME").setPreferredWidth(120);
		tbSanPham.getColumn("TRADEMARK").setPreferredWidth(80);
		tbSanPham.getColumn("PRICE").setPreferredWidth(50);
		tbSanPham.getColumn("AMOUNT").setPreferredWidth(50);
		tbSanPham.addMouseListener(new PopClickListener());
		tbSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JPanel pnTrangChuRight = new JPanel();
		//pnTrangChuRight.setBorder(b);
		pnTrangChuRight.setLayout(new BorderLayout());
		pnTrangChu.add(pnTrangChuRight, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMain = new JPanel();
		pnTrangChuRightMain.setLayout(new BorderLayout());
		pnTrangChuRight.add(pnTrangChuRightMain, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMainLeft = new JPanel();
		//pnTrangChuRightMainLeft.setBorder(b);
		pnTrangChuRightMainLeft.setPreferredSize(new Dimension(280, 0));
		pnTrangChuRightMainLeft.setLayout(new BoxLayout(pnTrangChuRightMainLeft, BoxLayout.Y_AXIS));
		pnTrangChuRightMain.add(pnTrangChuRightMainLeft, BorderLayout.WEST);
		
		/*JPanel pnTrangChuRightMainLeftTop1 = new JPanel();
		pnTrangChuRightMainLeftTop1.setPreferredSize(new Dimension(0, 50));
		pnTrangChuRightMainLeft.add(pnTrangChuRightMainLeftTop1);*/
		
		JPanel pnTrangChuRightTitle = new JPanel();
		pnTrangChuRightTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTrangChuRightTitle = new JLabel("Chi Tiết Sản Phẩm");
		lblTrangChuRightTitle.setForeground(new Color(79, 213, 157));
		Font fontlblTrangChuRightTitle = new Font("Serif", Font.BOLD, 20);
		lblTrangChuRightTitle.setFont(fontlblTrangChuRightTitle);
		pnTrangChuRightTitle.add(lblTrangChuRightTitle);
		pnTrangChuRightMainLeft.add(pnTrangChuRightTitle);
		
		JLabel lblID1 = new JLabel("  < ID: ");
		lblID1.setForeground(Color.RED);
		JLabel lblID2 = new JLabel(" >");
		lblID2.setForeground(Color.RED);
		txtID = new JTextField(3);
		txtID.setEditable(false);
		pnTrangChuRightTitle.add(lblID1);
		pnTrangChuRightTitle.add(txtID);
		pnTrangChuRightTitle.add(lblID2);
		
		
		pnTen = new JPanel();
		pnTen.setLayout(new BoxLayout(pnTen, BoxLayout.Y_AXIS));
		JPanel pnTenMain = new JPanel();
		pnTenMain.setLayout(new FlowLayout());
		JLabel lblTen = new JLabel("Name: ");
		pnTenMain.add(lblTen);
		txtTen = new JTextField(18);
		txtTen.setDocument(new JTextFieldLimit(30));
		//txtTen.requestFocus();
		pnTenMain.add(txtTen);
		pnTen.add(pnTenMain);
		pnTrangChuRightMainLeft.add(pnTen);
		
		pnNhanHieu = new JPanel();
		pnNhanHieu.setLayout(new BoxLayout(pnNhanHieu, BoxLayout.Y_AXIS));
		JPanel pnNhanHieuMain = new JPanel();
		pnNhanHieuMain.setLayout(new FlowLayout());
		JLabel lblNhanHieu = new JLabel("Nhãn Hiệu: ");
		pnNhanHieuMain.add(lblNhanHieu);
		txtNhanHieu = new JTextField(8);
		txtNhanHieu.setDocument(new JTextFieldLimit(30));
		pnNhanHieuMain.add(txtNhanHieu);
		pnNhanHieu.add(pnNhanHieuMain);
		pnTrangChuRightMainLeft.add(pnNhanHieu);
		
		JPanel pnType = new JPanel();
		cboLoai = new JComboBox<String>();
		cboLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboLoai.addItem("Shoes");
		cboLoai.addItem("Balls");
		cboLoai.addItem("Accessories");
		cboLoai.setPreferredSize(new Dimension(99, 27));
		cboLoai.setSelectedIndex(0); // mặc định là loại sản phẩm là giày.
		pnNhanHieuMain.add(cboLoai);
		//pnTrangChuRightMainLeft.add(pnType);
		
		
		
		pnPrice = new JPanel();
		pnPrice.setLayout(new BoxLayout(pnPrice, BoxLayout.Y_AXIS));
		JPanel pnPriceMain = new JPanel();
		pnPriceMain.setLayout(new FlowLayout());
		JLabel lblPrice = new JLabel("Price($): ");
		pnPriceMain.add(lblPrice);
		txtPrice = new JTextField(18);
		txtPrice.setDocument(new JTextFieldLimit(9));
		pnPriceMain.add(txtPrice);
		pnPrice.add(pnPriceMain);
		pnTrangChuRightMainLeft.add(pnPrice);
		
		pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new BoxLayout(pnSoLuong, BoxLayout.Y_AXIS));
		JPanel pnSoLuongMain = new JPanel();
		pnSoLuongMain.setLayout(new FlowLayout());
		JLabel lblSoLuong = new JLabel("Số Lượng: ");
		pnSoLuongMain.add(lblSoLuong);
		txtSoLuong = new JTextField(18);
		txtSoLuong.setDocument(new JTextFieldLimit(9));
		pnSoLuongMain.add(txtSoLuong);
		pnSoLuong.add(pnSoLuongMain);
		pnTrangChuRightMainLeft.add(pnSoLuong);
		
		JPanel pnTongTien = new JPanel();
		pnTongTien.setLayout(new BoxLayout(pnTongTien, BoxLayout.Y_AXIS));
		JPanel pnTongTienMain = new JPanel();
		pnTongTienMain.setLayout(new FlowLayout());
		JLabel lblTongTien = new JLabel("Tổng Tiền($): ");
		pnTongTienMain.add(lblTongTien);
		txtTongTien = new JTextField(18);
		txtTongTien.setPreferredSize(new Dimension(100, 20));
		txtTongTien.setBorder(b);
		pnTongTienMain.add(txtTongTien);
		pnTongTien.add(pnTongTienMain);
		pnTrangChuRightMainLeft.add(pnTongTien);
		
		lblTen.setPreferredSize(lblTongTien.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTongTien.getPreferredSize());
		lblPrice.setPreferredSize(lblTongTien.getPreferredSize());
		lblNhanHieu.setPreferredSize(lblTongTien.getPreferredSize());
		
		JPanel pnTrangChuRightMainCenter = new JPanel();
		//pnTrangChuRightMainCenter.setBorder(b);
		pnTrangChuRightMainCenter.setPreferredSize(new Dimension(1, 0));
		pnTrangChuRightMain.add(pnTrangChuRightMainCenter, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMainRight = new JPanel();
		pnTrangChuRightMainRight.setPreferredSize(new Dimension(297, 0));
		//pnTrangChuRightMainRight.setBorder(b);
		pnTrangChuRightMainRight.setLayout(new BoxLayout(pnTrangChuRightMainRight, BoxLayout.Y_AXIS));
		pnTrangChuRightMain.add(pnTrangChuRightMainRight, BorderLayout.EAST);
		
		JPanel pnImgProduct = new JPanel();
		pnImgProduct.setPreferredSize(new Dimension(120, 91));
		pnImgProduct.setLayout(new BorderLayout());
		pnTrangChuRightMainRight.add(pnImgProduct);
		
		JPanel pnImgProductLeft = new JPanel();
		pnImgProductLeft.setPreferredSize(new Dimension(84, 0));
		pnImgProduct.add(pnImgProductLeft, BorderLayout.WEST);
		JPanel pnImgProductRight = new JPanel();
		pnImgProductRight.setPreferredSize(new Dimension(83, 0));
		pnImgProduct.add(pnImgProductRight, BorderLayout.EAST);
		
		pnHeadRightAvatarMain = new JPanel();
		Border borderHeadRightAvatarMain = BorderFactory.createLineBorder(Color.BLACK, 3);
		pnHeadRightAvatarMain.setBorder(borderHeadRightAvatarMain);
		pnHeadRightAvatarMain.setLayout(new BorderLayout());
		pnImgProduct.add(pnHeadRightAvatarMain, BorderLayout.CENTER);

		Image img = new ImageIcon(urlProductDefault).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
		pnHeadRightAvatar = new BackGroundImage(img);
		pnHeadRightAvatar.setToolTipText("IMAGE<124x124>");
		pnHeadRightAvatarMain.add(pnHeadRightAvatar);
		
		try {
			File f = new File(urlProductDefault);
			FileInputStream fis = new FileInputStream(f);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for(int readNum;(readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			imgProduct = bos.toByteArray();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel pnlblAvatar = new JPanel();
		lblAvatar = new JLabel("IMAGE");
		Font fontavatar = new Font("Serif", Font.BOLD, 18);
		lblAvatar.setFont(fontavatar);
		lblAvatar.setForeground(Color.BLUE);
		Border borderlblAvatar = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
		lblAvatar.setBorder(borderlblAvatar);
		lblAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlblAvatar.add(lblAvatar);
		pnImgProduct.add(pnlblAvatar, BorderLayout.SOUTH);
		
		JPanel pnMoTa = new JPanel();
		pnMoTa.setPreferredSize(new Dimension(0, 35));
		pnMoTa.setLayout(new BorderLayout());
		JLabel lblMoTa = new JLabel("   Mô Tả");
		pnMoTa.add(lblMoTa, BorderLayout.NORTH);
		
		txtMoTa = new JTextArea();
		JScrollPane scMoTa = new JScrollPane(txtMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		txtMoTa.setLineWrap(true);
		txtMoTa.setWrapStyleWord(true);
		pnMoTa.add(scMoTa);
		pnTrangChuRightMainRight.add(pnMoTa, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMainRightbuttom1 = new JPanel();
		pnTrangChuRightMainRightbuttom1.setPreferredSize(new Dimension(0, 20));
		pnTrangChuRightMainRight.add(pnTrangChuRightMainRightbuttom1, BorderLayout.SOUTH);
		
		
		JPanel pnTrangChuRightMainButtom = new JPanel();
		pnTrangChuRightMainButtom.setPreferredSize(new Dimension(0, 50));
		pnTrangChuRightMainButtom.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnTrangChuRightMain.add(pnTrangChuRightMainButtom, BorderLayout.SOUTH);
		
		JPanel pnTrang= new JPanel();
		pnTrang.setPreferredSize(new Dimension(50, 0));
		pnTrangChuRightMainButtom.add(pnTrang);
		
		btnXoa = new JButton("  Xóa"); 
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgXoa = new ImageIcon("img/imgProduct/delete.png").getImage();
		btnXoa.setIcon(new ImageIcon(imgXoa));
		btnXoa.setPreferredSize(new Dimension(100, 40));
		pnTrangChuRightMainButtom.add(btnXoa);
		
		JPanel pnTrang2= new JPanel();
		pnTrangChuRightMainButtom.add(pnTrang2);
		
		btnSua = new JButton("  Sửa"); 
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgSua = new ImageIcon("img/imgProduct/tools2.png").getImage();
		btnSua.setIcon(new ImageIcon(imgSua));
		btnSua.setPreferredSize(new Dimension(100, 40));
		pnTrangChuRightMainButtom.add(btnSua);
		
		JPanel pnTrang1= new JPanel();
		pnTrangChuRightMainButtom.add(pnTrang1);
		
		btnThem = new JButton("  Thêm");
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgAdd = new ImageIcon("img/imgProduct/add.png").getImage();
		btnThem.setIcon(new ImageIcon(imgAdd));
		btnThem.setPreferredSize(new Dimension(100, 40));
		pnTrangChuRightMainButtom.add(btnThem);
		
		JPanel pnTrang3= new JPanel();
		pnTrangChuRightMainButtom.add(pnTrang3);
		
		btnReset = new JButton(" Reset");
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgReset = new ImageIcon("img/imgMenu/reset.gif").getImage();
		btnReset.setIcon(new ImageIcon(imgReset));
		btnReset.setPreferredSize(new Dimension(100, 40));
		pnTrangChuRightMainButtom.add(btnReset);
		
		
		JPanel pnTrangChuTail = new JPanel();
		pnTrangChuTail.setBorder(borderButtom);
		pnTrangChuTail.setPreferredSize(new Dimension(0, 77));
		pnTrangChuTail.setLayout(new BorderLayout());
		pnTrangChu.add(pnTrangChuTail, BorderLayout.SOUTH);
		

		JPanel pnTrangChuTailLeft = new JPanel();
		//pnTrangChuTailLeft.setBorder(b);
		pnTrangChuTailLeft.setPreferredSize(new Dimension(50, 0));
		pnTrangChuTail.add(pnTrangChuTailLeft, BorderLayout.WEST);

		JPanel pnTrangChuTailMain = new JPanel();
		//pnTrangChuTailMain.setBorder(b);
		pnTrangChuTail.add(pnTrangChuTailMain, BorderLayout.CENTER);
		pnTrangChuTailMain.setLayout(new BoxLayout(pnTrangChuTailMain, BoxLayout.X_AXIS));
		
		JPanel pnTimKiem = new JPanel();
		//pnTimKiem.setBorder(b);
		pnTimKiem.setLayout(new BorderLayout());
		btnTimKiem = new JButton("  Tìm Kiếm");
		btnTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgTimKiem = new ImageIcon("img/imgProduct/timKiem.png").getImage();
		btnTimKiem.setIcon(new ImageIcon(imgTimKiem));
	//	btnTimKiem.setPreferredSize(new Dimension(80, 0));
		pnTimKiem.add(btnTimKiem, BorderLayout.CENTER);
		pnTrangChuTailMain.add(pnTimKiem);
		
		JPanel pnTimKiemNorth = new JPanel();
		pnTimKiemNorth.setPreferredSize(new Dimension(0, 15));
		JPanel pnTimKiemSouth = new JPanel();
		pnTimKiemSouth.setPreferredSize(new Dimension(0, 15));
		JPanel pnTimKiemEast = new JPanel();
		JPanel pnTimKiemWest = new JPanel();

		pnTimKiem.add(pnTimKiemNorth, BorderLayout.NORTH);
		pnTimKiem.add(pnTimKiemSouth, BorderLayout.SOUTH);
		pnTimKiem.add(pnTimKiemEast, BorderLayout.EAST);
		pnTimKiem.add(pnTimKiemWest, BorderLayout.WEST);
		
		JPanel pnSX = new JPanel();
		pnTrangChuTailMain.add(pnSX);
		TitledBorder titlePNSX = new TitledBorder(b, "Sắp Xếp");
		pnSX.setBorder(titlePNSX);
		pnSX.setLayout(new GridLayout(2, 2, 9, 9));
		
		radSXTheoTen = new JRadioButton("Name"); 
		radSXTheoPrice = new JRadioButton("Price");
		radSXTheoAmount = new JRadioButton("Amount"); 
		radDefault = new JRadioButton("Default");
		
		radSXTheoTen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radSXTheoPrice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radSXTheoAmount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radDefault.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radDefault.setSelected(true);
		
		groundSX = new ButtonGroup();
		groundSX.add(radSXTheoTen);
		groundSX.add(radSXTheoPrice);
		groundSX.add(radSXTheoAmount);
		groundSX.add(radDefault);
		
		pnSX.add(radSXTheoTen);
		pnSX.add(radSXTheoPrice);
		pnSX.add(radSXTheoAmount);
		pnSX.add(radDefault);
		
		String str = "Created by \"Nguyễn Mạnh Cường\"";
		JPanel pnIntroduceLeft = new JPanelWordRun(30, str, 0, 15, 200, 25);
		pnIntroduceLeft.setPreferredSize(new Dimension(500, 0));
		pnTrangChuTailMain.add(pnIntroduceLeft);
		
		JPanel pnTrangChuTailRight = new JPanel();
		//pnTrangChuTailRight.setBorder(b);
		pnTrangChuTailRight.setLayout(new BorderLayout());
		pnTrangChuTailRight.setPreferredSize(new Dimension(200, 0));
		pnTrangChuTail.add(pnTrangChuTailRight, BorderLayout.EAST);
		
		JPanel pnTrangChuTailRightNorth = new JPanel();
		pnTrangChuTailRightNorth.setPreferredSize(new Dimension(0, 15));
		JPanel pnTrangChuTailRightSouth = new JPanel();
		pnTrangChuTailRightSouth.setPreferredSize(new Dimension(0, 15));
		JPanel pnTrangChuTailRightEast = new JPanel();
		pnTrangChuTailRightEast.setPreferredSize(new Dimension(20, 0));
		JPanel pnTrangChuTailRightWest = new JPanel();
		pnTrangChuTailRightWest.setPreferredSize(new Dimension(50, 0));

		pnTrangChuTailRight.add(pnTrangChuTailRightNorth, BorderLayout.NORTH);
		pnTrangChuTailRight.add(pnTrangChuTailRightSouth, BorderLayout.SOUTH);
		pnTrangChuTailRight.add(pnTrangChuTailRightEast, BorderLayout.EAST);
		pnTrangChuTailRight.add(pnTrangChuTailRightWest, BorderLayout.WEST);
		
		
		btnTrangChu = new JButton("  Quay Lại");
		btnTrangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgHome = new ImageIcon("img/imgProduct/home.png").getImage();
		btnTrangChu.setIcon(new ImageIcon(imgHome));
		pnTrangChuTailRight.add(btnTrangChu, BorderLayout.CENTER);
	}

	private void initTableWithAllProduct() {
		// TODO Auto-generated method stub
		ArrayList<ProductInformation> arrProduct = ProductService.getAllProduct();
		dtmSanPham.setRowCount(0);
		for(int i = 0;i<arrProduct.size();i++) {
			Object[] o = new Object[5];
			o[0] = arrProduct.get(i).getId();
			o[1] = arrProduct.get(i).getProductName();
			o[2] = arrProduct.get(i).getProductTrademark();
			o[3] = arrProduct.get(i).getProductPrice();
			o[4] = arrProduct.get(i).getProductAmount();
			dtmSanPham.addRow(o);
		}
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
		menuMenuAccount = new JMenuItem("Account");
		menuMenuAccount.setIcon(new ImageIcon(imgMenuAccount));
		menuMenu.add(menuMenuAccount);
		menuMenuBill = new JMenuItem("Bill");
		menuMenuBill.setIcon(new ImageIcon(imgMenuBill));
		menuMenu.add(menuMenuBill);
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

		menuMenuTrangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		// TODO Auto-generated method stub
		QuanLySanPhamUI ui = new QuanLySanPhamUI("Quản Lý Sản Phẩm");
		ui.showWindow();
	}

}
