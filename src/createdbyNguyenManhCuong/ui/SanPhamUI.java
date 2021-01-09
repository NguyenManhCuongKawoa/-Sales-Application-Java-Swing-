package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import createdbyNguyenManhCuong.connect.BillService;
import createdbyNguyenManhCuong.connect.ProductService;
import createdbyNguyenManhCuong.model.Bill;
import createdbyNguyenManhCuong.model.ProductInformation;
 
class PopUPListBill extends JPopupMenu {
	
	JMenuItem popUpDeleteBill, popUpShowBill;
	
	public PopUPListBill () {
		popUpDeleteBill = new JMenuItem("Delete");
		popUpShowBill =  new JMenuItem("View");
		add(popUpDeleteBill);
		add(popUpShowBill);
		
		popUpShowBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(GetUI.sanPhamUI.tblDanhSachDonHang.getSelectedRow() != -1) {
					Object o =  GetUI.sanPhamUI.tblDanhSachDonHang.getValueAt(GetUI.sanPhamUI.tblDanhSachDonHang.getSelectedRow(), 0);
					int id = Integer.parseInt((String)o);
					Bill bill = BillService.getBillFromID(id);
					GioHangUI ui = new GioHangUI("Giỏ Hàng", bill);
					ui.btnDatHang.setEnabled(true);
					ui.showWindow();
				}
				else JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Đơn Hàng");
			}
		});
		popUpDeleteBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GetUI.sanPhamUI.tblDanhSachDonHang.getSelectedRow() != -1) {
					int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc muốn xóa Bill này không?", "Xác Nhận Xóa", JOptionPane.YES_NO_OPTION);
					if(choose == JOptionPane.YES_OPTION) {
						Object o =  GetUI.sanPhamUI.tblDanhSachDonHang.getValueAt(GetUI.sanPhamUI.tblDanhSachDonHang.getSelectedRow(), 0);
						int id = Integer.parseInt((String)o);
						if(BillService.deleteBill(id) == true) {
							JOptionPane.showMessageDialog(null, "Bạn Đã Xóa Bill Thành Công");
							GetUI.sanPhamUI.initDanhSachDonHang();
						}
						else JOptionPane.showMessageDialog(null, "Đã Sảy Ra Lỗi Khi Xóa Bill");
					}
				}
				else JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Đơn Hàng");
			}
		});
	}
}
class PopClickListenerBill extends MouseAdapter {
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
		PopUPListBill menu = new PopUPListBill();
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}


public class SanPhamUI extends JFrame{
	
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
	
	String imgBackgroundProduct = "img/imgGift/backgroundProduct.gif";
	
	
	JButton btnGioHang, btnTimKiem, btnTrangChu, btnNewBill;
	JTextField txtTimKiem;
	
	JRadioButton radSXTheoTen, radSXTheoPrice, radSXTheoAmount, radDefault;
	ButtonGroup groundSX;
	
	JCheckBox cboShoes, cboBalls, cboAccessories;
	
	JPanel pnCenterMain;
	//ArrayList<JPanel> arrJpanelProduct;
	
	DefaultTableModel dtmDanhSachDonHang, dtmDonHangDaDat;
	public JTable tblDanhSachDonHang, tblDonHangDaDat;

	public SanPhamUI(String title) {
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
		GetUI.setSanPhamUI(this);
		addControls();
		addEvents();
		
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
            	setVisible(false);
				GetUI.accountUserUI.setVisible(true);
            }
        });
		
		menuMenuTrangChu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				GetUI.accountUserUI.setVisible(true);
			}
		});
		menuMenuProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//xuLyMenuProductUser();
			}
		});
		menuMenuAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountUI ui = new AccountUI("User", GetUI.loginUI.account);
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
		btnTrangChu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				GetUI.accountUserUI.setVisible(true);
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
		btnGioHang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblDanhSachDonHang.getSelectedRow() != -1) {
					Object o =  tblDanhSachDonHang.getValueAt(tblDanhSachDonHang.getSelectedRow(), 0);
					int id = Integer.parseInt((String)o);
					Bill bill = BillService.getBillFromID(id);
					GioHangUI ui = new GioHangUI("Giỏ Hàng", bill);
					ui.btnDatHang.setEnabled(true);
					ui.showWindow();
				}
				else JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Đơn Hàng");
			}
		});
		tblDonHangDaDat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tblDonHangDaDat.getSelectedRow() != -1) {
					Object o =  tblDonHangDaDat.getValueAt(tblDonHangDaDat.getSelectedRow(), 0);
					int id = Integer.parseInt((String)o);
					Bill bill = BillService.getBillFromID(id);
					GioHangUI ui = new GioHangUI("Giỏ Hàng", bill);
					ui.btnDatHang.setEnabled(false);
					ui.showWindow();

				}
			}
		});
	}
	protected void xuLyShowProduct() {
		// TODO Auto-generated method stub
		ArrayList<ProductInformation> arrProduct = new ArrayList<ProductInformation>();
		if(cboBalls.isSelected() && cboShoes.isSelected() && cboAccessories.isSelected()) {
			ArrayList<ProductInformation> arrProduct1 = ProductService.getAllProduct();
			arrProduct.addAll(arrProduct1);
		}
		else {
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
		}
		showProduct(arrProduct);
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
					showProduct(arrProduct);
				}
			}
		}
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

	private void addControls() {
		// TODO Auto-generated method stub
		createMenu();
		Border borderButtom = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.RED);
		Border b = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red);
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		//Image imgBackground = new ImageIcon(imgBackgroundProduct).getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);		
		//JPanel pn = new BackGroundImage(imgBackground);
		JPanel pn = new  JPanel();
		pn.setLayout(new BorderLayout());
		con.add(pn);
		
		
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0,0,0,0));
		pnTitle.setLayout(new BorderLayout());
		pn.add(pnTitle, BorderLayout.NORTH);
		//pnTitle.setLayout(new BoxLayout(pnTitle, BoxLayout.Y_AXIS));
		pnTitle.setPreferredSize(new Dimension(0, 85));
		pnTitle.setBorder(b);
		JPanel pnTrang = new JPanel();
		pnTrang.setBackground(new Color(0,0,0,0));
		pnTitle.add(pnTrang);
		JPanel pnTitleMain = new JPanel();
		pnTitleMain.setBackground(new Color(0,0,0,0));
		pnTitleMain.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel pnTrang0 = new JPanel();
		pnTrang0.setPreferredSize(new Dimension(80, 0));
		pnTrang0.setBackground(new Color(0,0,0,0));
		pnTitleMain.add(pnTrang0);
		//pnTitleMain.setBorder(borderButtom);
		JLabel lblTitle = new JLabel("==>PRODUCT<==");
		Font fontlblTitle = new Font("ravie", Font.BOLD, 55);
		lblTitle.setFont(fontlblTitle);
		lblTitle.setForeground(Color.red);
		pnTitleMain.add(lblTitle);
		pnTitle.add(pnTitleMain, BorderLayout.CENTER);
		JPanel pnTrang1 = new JPanel();
		pnTrang1.setPreferredSize(new Dimension(70, 0));
		pnTrang1.setBackground(new Color(0,0,0,0));
		pnTitleMain.add(pnTrang1);
		
		JPanel pnGioHang = new JPanel();
		pnGioHang.setLayout(new BorderLayout());
		pnTitleMain.add(pnGioHang);
		
		Image imgGioHang = new ImageIcon("img/imgProduct/cart-icon.png").getImage();//.getScaledInstance(124, 124, Image.SCALE_DEFAULT);
		btnGioHang = new JButton("Giỏ Hàng");
		btnGioHang.setFocusable(false);
		//btnGioHang.setPreferredSize(new Dimension(80, 80));
		btnGioHang.setIcon(new ImageIcon(imgGioHang));
		pnGioHang.add(btnGioHang, BorderLayout.CENTER);
		
		JPanel pnGioHangBottom = new JPanel();
		pnGioHangBottom.setPreferredSize(new Dimension(0, 20));
		pnGioHang.add(pnGioHangBottom, BorderLayout.SOUTH);
		
		
		JPanel pnTitleTop = new JPanel();
		pnTitleTop.setBackground(Color.pink);
		JPanel pnTitleButtom = new JPanel();
		pnTitleButtom.setBackground(Color.pink);
		JPanel pnTitleLeft = new JPanel();
		pnTitleLeft.setBackground(Color.pink);
		JPanel pnTitleRight = new JPanel();
		pnTitleRight.setBackground(Color.pink);
		pnTitle.add(pnTitleTop, BorderLayout.NORTH);
		pnTitle.add(pnTitleButtom, BorderLayout.SOUTH);
		pnTitle.add(pnTitleLeft, BorderLayout.WEST);
		pnTitle.add(pnTitleRight, BorderLayout.EAST);
		
		
		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setBorder(b);
		pnLeft.setBackground(new Color(0, 0, 0, 0));
		pnLeft.setPreferredSize(new Dimension(30, 0));
		pn.add(pnLeft, BorderLayout.WEST);
		
		JPanel pnLeftTop = new JPanel();
		pnLeftTop.setBackground(Color.pink);
		JPanel pnLeftButtom = new JPanel();
		pnLeftButtom.setBackground(Color.pink);
		JPanel pnLeftLeft = new JPanel();
		pnLeftLeft.setBackground(Color.pink);
		JPanel pnLeftRight = new JPanel();
		pnLeftRight.setBackground(Color.pink);
		pnLeft.add(pnLeftTop, BorderLayout.NORTH);
		pnLeft.add(pnLeftButtom, BorderLayout.SOUTH);
		pnLeft.add(pnLeftLeft, BorderLayout.WEST);
		pnLeft.add(pnLeftRight, BorderLayout.EAST);
		
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBorder(b);
		pnCenter.setBackground(Color.GRAY);
		pnCenter.setLayout(new BorderLayout());
		pn.add(pnCenter, BorderLayout.CENTER);
		
		JPanel pnCenterTop = new JPanel();
		pnCenterTop.setBackground(Color.pink);
		JPanel pnCenterButtom = new JPanel();
		pnCenterButtom.setBackground(Color.pink);
		JPanel pnCenterLeft = new JPanel();
		pnCenterLeft.setBackground(Color.pink);
		JPanel pnCenterRight = new JPanel();
		pnCenterRight.setBackground(Color.pink);
		pnCenter.add(pnCenterTop, BorderLayout.NORTH);
		pnCenter.add(pnCenterButtom, BorderLayout.SOUTH);
		pnCenter.add(pnCenterLeft, BorderLayout.WEST);
		pnCenter.add(pnCenterRight, BorderLayout.EAST);
		
		
		
		Image imgCenterMain = new ImageIcon("img/imgGift/kuroko11.gif").getImage().getScaledInstance(535, 380, Image.SCALE_DEFAULT);
		pnCenterMain = new BackGroundImage(imgCenterMain);
		pnCenterMain.setLayout(new BoxLayout(pnCenterMain, BoxLayout.Y_AXIS));
		JScrollPane sc = new JScrollPane(pnCenterMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnCenter.add(sc, BorderLayout.CENTER);
		
		initShowProduct();
		
		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BorderLayout());
		pnRight.setBorder(b);
		pnRight.setBackground(new Color(0, 0, 0, 0));
		pnRight.setPreferredSize(new Dimension(300, 0));
		pn.add(pnRight, BorderLayout.EAST);
		
		JPanel pnRightTop = new JPanel();
		pnRightTop.setBackground(Color.pink);
		JPanel pnRightButtom = new JPanel();
		pnRightButtom.setBackground(Color.pink);
		JPanel pnRightLeft = new JPanel();
		pnRightLeft.setBackground(Color.pink);
		JPanel pnRightRight = new JPanel();
		pnRightRight.setBackground(Color.pink);
		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightButtom, BorderLayout.SOUTH);
		pnRight.add(pnRightLeft, BorderLayout.WEST);
		pnRight.add(pnRightRight, BorderLayout.EAST);
		
		JPanel pnRightMain = new JPanel();
		
		pnRightMain.setBackground(Color.BLACK);
		pnRightMain.setLayout(new BorderLayout());
		pnRight.add(pnRightMain, BorderLayout.CENTER);
		
		JPanel pnRightMainTop = new JPanel();
		pnRightMainTop.setPreferredSize(new Dimension(0, 70));
		pnRightMainTop.setLayout(new BorderLayout());
		pnRightMain.add(pnRightMainTop, BorderLayout.NORTH);
		
		JPanel pnRightMainTopTop = new JPanel();
		JPanel pnRightMainTopButtom = new JPanel();
		pnRightMainTopButtom.setBackground(Color.pink);
		pnRightMainTopButtom.setBorder(borderButtom);
		JPanel pnRightMainTopLeft = new JPanel();
		pnRightMainTopLeft.setPreferredSize(new Dimension(3, 0));
		JPanel pnRightMainTopRight = new JPanel();
		pnRightMainTop.add(pnRightMainTopTop, BorderLayout.NORTH);
		pnRightMainTop.add(pnRightMainTopButtom, BorderLayout.SOUTH);
		pnRightMainTop.add(pnRightMainTopLeft, BorderLayout.WEST);
		pnRightMainTop.add(pnRightMainTopRight, BorderLayout.EAST);
		
		JPanel pnRightMainTopCenter = new JPanel();
		pnRightMainTopCenter.setLayout(new GridLayout(2, 3, 3, 0));
		pnRightMainTop.add(pnRightMainTopCenter, BorderLayout.CENTER);
		
		
		JLabel lblShoes = new JLabel("Giày ");
		Image imgShoes = new ImageIcon("img/imgProduct/shoes.png").getImage();
		lblShoes.setIcon(new ImageIcon(imgShoes));
		lblShoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pnRightMainTopCenter.add(lblShoes);
		
		JLabel lblBalls = new JLabel("Bóng   ");
		Image imgBalls = new ImageIcon("img/imgProduct/basketball-ball.png").getImage();
		lblBalls.setIcon(new ImageIcon(imgBalls));
		lblBalls.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pnRightMainTopCenter.add(lblBalls);
		
		JLabel lblAccessories = new JLabel("Phụ Kiện ");
		Image imgAccessories = new ImageIcon("img/imgProduct/accessories.png").getImage();
		lblAccessories.setIcon(new ImageIcon(imgAccessories));
		lblAccessories.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pnRightMainTopCenter.add(lblAccessories);
		
		cboShoes = new JCheckBox();
		cboShoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		cboBalls = new JCheckBox();
		cboBalls.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		cboAccessories = new JCheckBox();
		cboAccessories.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pnRightMainTopCenter.add(cboShoes);
		pnRightMainTopCenter.add(cboBalls);
		pnRightMainTopCenter.add(cboAccessories);
		
		JPanel pnRightMainCenter = new JPanel();
		pnRightMainCenter.setLayout(new BorderLayout());
		pnRightMain.add(pnRightMainCenter, BorderLayout.CENTER);
		
		JPanel pnRightMainCenterTitle = new  JPanel();
		pnRightMainCenterTitle.setBorder(borderButtom);
		pnRightMainCenterTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblRightMainCenterTitle = new JLabel("Danh Sách đơn Hàng   ");
		Font fontlblRightMainCenterTitle = new Font("Serif", Font.BOLD, 20);
		lblRightMainCenterTitle.setFont(fontlblRightMainCenterTitle);
		pnRightMainCenterTitle.add(lblRightMainCenterTitle);
		pnRightMainCenter.add(pnRightMainCenterTitle, BorderLayout.NORTH);

		ImageIcon imgNewBill = new ImageIcon("img/imgProduct/newIcon.png");
		JPanel pnNewBill = new BackGroundImage(imgNewBill.getImage());
		pnNewBill.setPreferredSize(new Dimension(33, 33));
		pnNewBill.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pnNewBill.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnNewBill.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				xuLyNewBill();
			}
		});
		pnRightMainCenterTitle.add(pnNewBill);
		
		
		JPanel pnDonHang = new JPanel();
		//pnDonHang.setBorder(b);
		pnRightMainCenter.add(pnDonHang, BorderLayout.CENTER);
		pnDonHang.setLayout(new BoxLayout(pnDonHang, BoxLayout.Y_AXIS));

		dtmDanhSachDonHang = new DefaultTableModel();
		tblDanhSachDonHang = new JTable(dtmDanhSachDonHang);
		tblDanhSachDonHang.addMouseListener(new PopClickListenerBill());
		tblDanhSachDonHang.setSelectionBackground(Color.green);
		JScrollPane scDSDonHang = new JScrollPane(tblDanhSachDonHang, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnDonHang.add(scDSDonHang);
		scDSDonHang.setPreferredSize(new Dimension(0, 350));
		Border bDonHang1 = BorderFactory.createLineBorder(Color.black, 1);
		TitledBorder titleBorder = new TitledBorder(bDonHang1);
		titleBorder.setTitle("Đơn Hàng chưa Đặt");
		scDSDonHang.setBorder(titleBorder);
		//scDSDonHang.setBorder(b);
		dtmDanhSachDonHang.addColumn("ID");
		dtmDanhSachDonHang.addColumn("Tên Bill");
		dtmDanhSachDonHang.addColumn("Ngày đặt");
		dtmDanhSachDonHang.addColumn("Ngày nhận");
		tblDanhSachDonHang.getColumn("ID").setPreferredWidth(0);
		tblDanhSachDonHang.getColumn("Tên Bill").setPreferredWidth(78);
		tblDanhSachDonHang.getColumn("Ngày đặt").setPreferredWidth(50);
		tblDanhSachDonHang.getColumn("Ngày nhận").setPreferredWidth(50);
		
		JPanel pnDonHangTrang = new JPanel();
		pnDonHangTrang.setBackground(Color.PINK);
		pnDonHangTrang.setPreferredSize(new Dimension(0, 2));
		pnDonHang.add(pnDonHangTrang);
		
		dtmDonHangDaDat = new DefaultTableModel();
		tblDonHangDaDat = new JTable(dtmDonHangDaDat);
		JScrollPane scDSDonHangDaDat = new JScrollPane(tblDonHangDaDat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnDonHang.add(scDSDonHangDaDat);
		Border bDonHang2 = BorderFactory.createLineBorder(Color.black, 1);
		TitledBorder titleBorder1 = new TitledBorder(bDonHang2);
		titleBorder1.setTitle("Đơn Hàng đã Đặt");
		scDSDonHangDaDat.setBorder(titleBorder1);
		//scDSDonHangDaDat.setBorder(b);
		dtmDonHangDaDat.addColumn("ID");
		dtmDonHangDaDat.addColumn("Tên Bill");
		dtmDonHangDaDat.addColumn("Ngày đặt");
		dtmDonHangDaDat.addColumn("Ngày nhận");
		tblDonHangDaDat.getColumn("ID").setPreferredWidth(0);
		tblDonHangDaDat.getColumn("Tên Bill").setPreferredWidth(78);
		tblDonHangDaDat.getColumn("Ngày đặt").setPreferredWidth(50);
		tblDonHangDaDat.getColumn("Ngày nhận").setPreferredWidth(50);
		
		initDanhSachDonHang();

		JPanel pnTail = new JPanel();
		pnTail.setBorder(b);
		//pnTail.setBackground(new Color(0, 0, 0, 0));
		pnTail.setPreferredSize(new Dimension(0, 80));
		pnTail.setLayout(new BorderLayout());
		pn.add(pnTail, BorderLayout.SOUTH);
		
		JPanel pnTailTop = new JPanel();
		pnTailTop.setBackground(Color.pink);
		JPanel pnTailButtom = new JPanel();
		pnTailButtom.setBackground(Color.pink);
		JPanel pnTailLeft = new JPanel();
		pnTailLeft.setBackground(Color.pink);
		JPanel pnTailRight = new JPanel();
		pnTailRight.setBackground(Color.pink);
		pnTail.add(pnTailTop, BorderLayout.NORTH);
		pnTail.add(pnTailButtom, BorderLayout.SOUTH);
		pnTail.add(pnTailLeft, BorderLayout.WEST);
		pnTail.add(pnTailRight, BorderLayout.EAST);
		
		JPanel pnTrangChuTail = new JPanel();
		//pnTrangChuTail.setBorder(borderButtom);
		pnTrangChuTail.setPreferredSize(new Dimension(0, 75));
		pnTrangChuTail.setLayout(new BorderLayout());
		pnTail.add(pnTrangChuTail, BorderLayout.CENTER);


		JPanel pnTrangChuTailLeft = new JPanel();
		//pnTrangChuTailLeft.setBorder(b);
		pnTrangChuTailLeft.setPreferredSize(new Dimension(20, 0));
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
		Image imgTimKiem = new ImageIcon("img/timKiem.png").getImage();
		btnTimKiem.setIcon(new ImageIcon(imgTimKiem));
	//	btnTimKiem.setPreferredSize(new Dimension(80, 0));
		pnTimKiem.add(btnTimKiem, BorderLayout.CENTER);
		pnTrangChuTailMain.add(pnTimKiem);
		
		JPanel pnTimKiemNorth = new JPanel();
		pnTimKiemNorth.setPreferredSize(new Dimension(0, 12));
		JPanel pnTimKiemSouth = new JPanel();
		pnTimKiemSouth.setPreferredSize(new Dimension(0, 12));
		JPanel pnTimKiemEast = new JPanel();
		pnTimKiemEast.setPreferredSize(new Dimension(35, 0));
		JPanel pnTimKiemWest = new JPanel();
		pnTimKiemWest.setPreferredSize(new Dimension(30, 0));

		pnTimKiem.add(pnTimKiemNorth, BorderLayout.NORTH);
		pnTimKiem.add(pnTimKiemSouth, BorderLayout.SOUTH);
		pnTimKiem.add(pnTimKiemEast, BorderLayout.EAST);
		pnTimKiem.add(pnTimKiemWest, BorderLayout.WEST);
		
		JPanel pnSX = new JPanel();
		pnTrangChuTailMain.add(pnSX);
		TitledBorder titlePNSX = new TitledBorder(b, "Sắp Xếp");
		pnSX.setBorder(titlePNSX);
		pnSX.setLayout(new GridLayout(2, 2, 5, 5));
		
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
		JPanel pnIntroduceLeft = new JPanelWordRun(30, str, 0, 15, 350, 25);
		pnIntroduceLeft.setPreferredSize(new Dimension(350, 0));
		pnTrangChuTailMain.add(pnIntroduceLeft);
		
		JPanel pnTrangChuTailRight = new JPanel();
		//pnTrangChuTailRight.setBorder(b);
		pnTrangChuTailRight.setLayout(new BorderLayout());
		pnTrangChuTailRight.setPreferredSize(new Dimension(180, 0));
		pnTrangChuTail.add(pnTrangChuTailRight, BorderLayout.EAST);
		
		JPanel pnTrangChuTailRightNorth = new JPanel();
		pnTrangChuTailRightNorth.setPreferredSize(new Dimension(0, 12));
		JPanel pnTrangChuTailRightSouth = new JPanel();
		pnTrangChuTailRightSouth.setPreferredSize(new Dimension(0, 12));
		JPanel pnTrangChuTailRightEast = new JPanel();
		pnTrangChuTailRightEast.setPreferredSize(new Dimension(20, 0));
		JPanel pnTrangChuTailRightWest = new JPanel();
		pnTrangChuTailRightWest.setPreferredSize(new Dimension(50, 0));

		pnTrangChuTailRight.add(pnTrangChuTailRightNorth, BorderLayout.NORTH);
		pnTrangChuTailRight.add(pnTrangChuTailRightSouth, BorderLayout.SOUTH);
		pnTrangChuTailRight.add(pnTrangChuTailRightEast, BorderLayout.EAST);
		pnTrangChuTailRight.add(pnTrangChuTailRightWest, BorderLayout.WEST);
		
		
		btnTrangChu = new JButton("  Quay Lại");
		//btnTrangChu.setPreferredSize(new Dimension(100, 35));
		btnTrangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgHome = new ImageIcon("img/house.png").getImage();
		btnTrangChu.setIcon(new ImageIcon(imgHome));
		pnTrangChuTailRight.add(btnTrangChu, BorderLayout.CENTER);
		
	}
	protected void xuLyNewBill() {
		// TODO Auto-generated method stub
		NewBillUI ui = new NewBillUI("New Bill");
		ui.showWindow();
	}

	public void initDanhSachDonHang() {
		// TODO Auto-generated method stub		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<Bill> arrBill = BillService.GetAllBillFromAccount(GetUI.loginUI.account.getId());
		//ArrayList<Bill> arrBill = BillService.GetAllBillFromAccount(5);
		
		tblDonHangDaDat.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(1).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(2).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(3).setCellRenderer(new CustomRenderer());
		
		dtmDonHangDaDat.setRowCount(0);
		dtmDanhSachDonHang.setRowCount(0);
		
		for(int i = 0;i<arrBill.size();i++) {
			Vector<String> vBill = new Vector<String>();
			vBill.add(String.valueOf(arrBill.get(i).getId()));
			vBill.add(arrBill.get(i).getBillName());
			vBill.add(String.valueOf(sdf.format(arrBill.get(i).getDateOfOrder())));
			vBill.add(String.valueOf(sdf.format(arrBill.get(i).getNgayNhanHang())));
		
			if(arrBill.get(i).getActiveBill() == 1) {
				dtmDonHangDaDat.addRow(vBill);
			}
			else {
				dtmDanhSachDonHang.addRow(vBill);
			}
		}
	}

	private void showProduct(ArrayList<ProductInformation> arr) {
		pnCenterMain.removeAll();
		JPanel pnTemp = null;
		if(arr.size() == 0) {
			Image img0 = new ImageIcon("img/imgGift/nba1.gif").getImage().getScaledInstance(535, 380, Image.SCALE_DEFAULT);
			JPanel pnTemp0 = new BackGroundImage(img0);
			pnTemp0.setPreferredSize(new Dimension(535, 380));
			pnCenterMain.add(pnTemp0);
			Image img1 = new ImageIcon("img/imgGift/kuroko11.gif").getImage().getScaledInstance(535, 380, Image.SCALE_DEFAULT);
			JPanel pnTemp1 = new BackGroundImage(img1);
			pnTemp1.setPreferredSize(new Dimension(535, 380));
			pnCenterMain.add(pnTemp1);
			Image img2 = new ImageIcon("img/imgGift/kuroko2.gif").getImage().getScaledInstance(535, 380, Image.SCALE_DEFAULT);
			JPanel  pnTemp2 = new BackGroundImage(img2);
			pnTemp2.setPreferredSize(new Dimension(535, 380));
			pnCenterMain.add(pnTemp2);
			Image img3 = new ImageIcon("img/imgGift/kuroko1.gif").getImage().getScaledInstance(535, 380, Image.SCALE_DEFAULT);
			JPanel  pnTemp3 = new BackGroundImage(img3);
			pnTemp3.setPreferredSize(new Dimension(535, 380));
			pnCenterMain.add(pnTemp3);
			Image img4 = new ImageIcon("img/imgGift/kuroko8.gif").getImage().getScaledInstance(535, 380, Image.SCALE_DEFAULT);
			JPanel  pnTemp4 = new BackGroundImage(img4);
			pnTemp4.setPreferredSize(new Dimension(535, 380));
			pnCenterMain.add(pnTemp4);
		}
		else {
			for (int i = 0; i < arr.size(); i++) {
				pnTemp = new ChiTietSanPham(arr.get(i));
				pnCenterMain.add(pnTemp);
			}
		}
		this.setVisible(true);
	}
	private void initShowProduct() {
		// TODO Auto-generated method stub
		ArrayList<ProductInformation> arr = new ArrayList<ProductInformation>();
		showProduct(arr);
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
		this.setSize(920, 610);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		SanPhamUI ui = new SanPhamUI("PRODUCT");
		ui.showWindow();
	}
}

class CustomRenderer extends DefaultTableCellRenderer
{
       public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
       {
           Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           setForeground(Color.RED); 
           return c;
       }
}
