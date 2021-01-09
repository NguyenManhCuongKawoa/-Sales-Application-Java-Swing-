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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;

import createdbyNguyenManhCuong.connect.BillService;
import createdbyNguyenManhCuong.connect.LoginService;
import createdbyNguyenManhCuong.connect.ProductService;
import createdbyNguyenManhCuong.model.Bill;
import createdbyNguyenManhCuong.model.ProductInformation;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class QuanLyDonHangUI extends JFrame{
	
	Bill bill;
	JPanel pnCenterCenter;
	
	DefaultTableModel  dtmDonHangDaDat;
	JTable  tblDonHangDaDat;
	
	JButton btnTimKiem, btnReportBill, btnQuayLai;
	
	JRadioButton radSXTheoTen, radSXTheoPrice, radSXTheoAmount, radDefault;
	ButtonGroup groundSX;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	JMenuBar menuBar;
	JMenu menuMenu, menuSetting, menuHelp;
	JMenuItem menuMenuTrangChu, menuMenuProduct, menuMenuAccount, menuMenuBill,menuMenuIntroduce, menuMenuChangePassword, menuMenuLogOut;
	JMenuItem menuSettingBackground, menuSettingReset;
	JMenuItem menuHelpWelcome;
	
	String imgMenu = "img/imgMenu/menu.png";
	String imgMenuSetting = "img/imgMenu/setting.png";
	String imgMenuHelp = "img/imgMenu/help.png";
	String imgMenuHome = "img/imgMenu/home.png";
	String imgMenuProduct = "img/imgMenu/ball.png";
	String imgMenuAccount = "img/imgMenu/account.png";
	String imgMenuBill = "img/imgMenu/bill.png";
	String imgMenuIntroduce = "img/imgMenu/tim.png";
	String imgMenuChangePassword = "img/imgMenu/keyNew.png";
	String imgMenuLogOut = "img/imgMenu/logOut.png";
	String imgMenuSettingBackground = "img/imgMenu/background.png";
	String imgMenuSettingReset = "img/imgMenu/reset.gif";
	
	public QuanLyDonHangUI(String title) {
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
		addControls();
		addEvents();
	}
	
	private void addEvents() {
		// TODO Auto-generated method stub
		menuMenuTrangChu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				GetUI.accountAdminUI.setVisible(true);
			}
		});
		menuMenuProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(GetUI.quanLySanPhamUI == null) {
					GetUI.quanLySanPhamUI = new QuanLySanPhamUI("Quản Lý Sản Phảm");
					GetUI.quanLySanPhamUI.showWindow();
					setVisible(false);
				}
				else {
					setVisible(false);
					GetUI.quanLySanPhamUI.setVisible(true);
					//System.out.println("skfdjsl");
				}
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
		radDefault.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(radDefault.isSelected()) {
					xuLyShowBill();
				}
			}
		});
		radSXTheoTen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowBill();
			}
		});
		radSXTheoPrice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowBill();
			}
		});
		radSXTheoAmount.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowBill();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyTimKiem();
			}
		});
		btnQuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				GetUI.accountAdminUI.setVisible(true);
			}
		});
		tblDonHangDaDat.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(tblDonHangDaDat.getSelectedRow() != -1) {
					Object o =  tblDonHangDaDat.getValueAt(tblDonHangDaDat.getSelectedRow(), 0);
					int id = Integer.parseInt((String)o);
					bill = BillService.getBillFromID(id);
					pnCenterCenter.removeAll();
					JPanel pnMain = new DetailBill(bill);
					pnCenterCenter.add(pnMain, BorderLayout.CENTER);
					setVisible(true);
				}
			}
		});
		btnReportBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyXuatDanhSachHoaDon();
			}
		});
	}
	protected void xuLyXuatDanhSachHoaDon() {
		// TODO Auto-generated method stub
		try {
			//Hashtable map = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("gui/ListBill.jrxml");
			
			//map.put("", idhd);
			
			JasperPrint p = JasperFillManager.fillReport(report, null, LoginService.connection);
			JasperViewer.viewReport(p, false);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	protected void xuLyShowBill() {
		// TODO Auto-generated method stub
		ArrayList<Bill> arrBill = BillService.GetAllBillIsActived(1);
		
		if(arrBill.size() != 0) {

			if(radSXTheoTen.isSelected()) {
				Bill.typeCompare = Bill.TypeCompare.name;
			}
			else if(radSXTheoPrice.isSelected()) {
				Bill.typeCompare = Bill.TypeCompare.price;
			}
			else if(radSXTheoAmount.isSelected()) {
				Bill.typeCompare = Bill.TypeCompare.amount;
			}
			else Bill.typeCompare = Bill.TypeCompare.macDinh;
			
			if(Bill.typeCompare != Bill.TypeCompare.macDinh) {
				Collections.sort(arrBill); 
			}
		}
		dtmDonHangDaDat.setRowCount(0);
		
		for(int i = 0;i<arrBill.size();i++) {
			Vector<String> vBill = new Vector<String>();
			vBill.add(String.valueOf(arrBill.get(i).getId()));
			vBill.add(arrBill.get(i).getBillName());
			vBill.add(String.valueOf(sdf.format(arrBill.get(i).getDateOfOrder())));
			vBill.add(String.valueOf(sdf.format(arrBill.get(i).getNgayNhanHang())));
			vBill.add(String.valueOf(arrBill.get(i).getAmountProduct()));
			vBill.add(String.valueOf(arrBill.get(i).getPriceTotal()));
			if(arrBill.get(i).getActiveBill() == 1) {
				dtmDonHangDaDat.addRow(vBill);
			}
		}
	}

	protected void xuLyTimKiem() {
		// TODO Auto-generated method stub
		Image imgTimKiem = new ImageIcon("img/imgProduct/timKiem.png").getImage();
		String tenBill = (String) JOptionPane.showInputDialog(null, "Vui lòng Nhập Tên Hóa Đơn Cần tìm kiếm", "Tìm Kiếm", 
				JOptionPane.OK_CANCEL_OPTION, new ImageIcon(imgTimKiem), null, "Nhập Tên Hóa Đơn");
		if (tenBill != null) {
			if (tenBill.length() == 0) {
				int choose = JOptionPane.showConfirmDialog(null, "Bạn chưa Nhập Tên! Bạn có muốn Nhận lại hay không?",
						"Xác Nhận", JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION)
					xuLyTimKiem();
			} else {
				ArrayList<Bill> arrBill = BillService.getBillFromName(tenBill);
				if (arrBill.size() == 0)
					JOptionPane.showMessageDialog(null, "Tên Bill Này Không Có Trong Danh Sách!");
				else {
					dtmDonHangDaDat.setRowCount(0);
					
					for(int i = 0;i<arrBill.size();i++) {
						Vector<String> vBill = new Vector<String>();
						vBill.add(String.valueOf(arrBill.get(i).getId()));
						vBill.add(arrBill.get(i).getBillName());
						vBill.add(String.valueOf(sdf.format(arrBill.get(i).getDateOfOrder())));
						vBill.add(String.valueOf(sdf.format(arrBill.get(i).getNgayNhanHang())));
						vBill.add(String.valueOf(arrBill.get(i).getAmountProduct()));
						vBill.add(String.valueOf(arrBill.get(i).getPriceTotal()));
						if(arrBill.get(i).getActiveBill() == 1) {
							dtmDonHangDaDat.addRow(vBill);
						}
					}
				}
			}
		}
	}
	private void addControls() {
		// TODO Auto-generated method stub
		createMenu();
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnTop = new JPanel();
		con.add(pnTop, BorderLayout.NORTH);
		pnTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnTop.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
		JLabel lblTopTitle = new  JLabel("Quản Lý Hóa Đơn");
		lblTopTitle.setFont(new Font("serif", Font.ITALIC, 33));
		pnTop.add(lblTopTitle);
		
		JPanel pnLeft = new JPanel();
		con.add(pnLeft, BorderLayout.WEST);
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setBackground(Color.BLUE);
		pnLeft.setPreferredSize(new Dimension(500, HEIGHT));
		
		JPanel pnLeftLeft = new JPanel();
		pnLeft.add(pnLeftLeft, BorderLayout.WEST);
		pnLeftLeft.setPreferredSize(new Dimension(10, HEIGHT));
		JPanel pnLeftRight = new JPanel();
		pnLeft.add(pnLeftRight, BorderLayout.EAST);
		pnLeftRight.setPreferredSize(new Dimension(10, HEIGHT));
		
		
		JPanel pnLeftTop = new JPanel();
		pnLeft.add(pnLeftTop, BorderLayout.NORTH);
		pnLeftTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnLeftTop.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
		JLabel lblLeftTopTitle = new  JLabel("Danh Sách Đơn Hàng");
		lblLeftTopTitle.setFont(new Font("serif", Font.ITALIC, 17));
		pnLeftTop.add(lblLeftTopTitle);
		
		JPanel pnLeftCenter = new JPanel();
		pnLeft.add(pnLeftCenter, BorderLayout.CENTER);
		pnLeftCenter.setLayout(new BorderLayout());
		
		dtmDonHangDaDat = new DefaultTableModel();
		tblDonHangDaDat = new JTable(dtmDonHangDaDat);
		JScrollPane scDSDonHangDaDat = new JScrollPane(tblDonHangDaDat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnLeftCenter.add(scDSDonHangDaDat);
		dtmDonHangDaDat.addColumn("ID");
		dtmDonHangDaDat.addColumn("Tên Bill");
		dtmDonHangDaDat.addColumn("Ngày đặt");
		dtmDonHangDaDat.addColumn("Ngày nhận");
		dtmDonHangDaDat.addColumn("SL SP");
		dtmDonHangDaDat.addColumn("Price Total");
		tblDonHangDaDat.getColumn("ID").setPreferredWidth(0);
		tblDonHangDaDat.getColumn("Tên Bill").setPreferredWidth(98);
		tblDonHangDaDat.getColumn("Ngày đặt").setPreferredWidth(40);
		tblDonHangDaDat.getColumn("Ngày nhận").setPreferredWidth(40);
		tblDonHangDaDat.getColumn("SL SP").setPreferredWidth(10);
		tblDonHangDaDat.getColumn("Price Total").setPreferredWidth(60);
		initDanhSachDonHang();
		
		JPanel pnLeftBottom = new JPanel();
		pnLeft.add(pnLeftBottom, BorderLayout.SOUTH);
		pnLeftBottom.setPreferredSize(new Dimension(WIDTH, 100));
		pnLeftBottom.setLayout(new BorderLayout());
		
		JPanel pnLeftBottomLeft = new JPanel();
		pnLeftBottom.add(pnLeftBottomLeft, BorderLayout.WEST);
		pnLeftBottomLeft.setPreferredSize(new Dimension(20, HEIGHT));
		JPanel pnLeftBottomRight = new JPanel();
		pnLeftBottom.add(pnLeftBottomLeft, BorderLayout.EAST);
		pnLeftBottomLeft.setPreferredSize(new Dimension(20, HEIGHT));
		JPanel pnLeftBottomNorth = new JPanel();
		pnLeftBottom.add(pnLeftBottomNorth, BorderLayout.NORTH);
		pnLeftBottomNorth.setPreferredSize(new Dimension(0, 15));
		JPanel pnLeftBottomSouth = new JPanel();
		pnLeftBottom.add(pnLeftBottomSouth, BorderLayout.SOUTH);
		pnLeftBottomSouth.setPreferredSize(new Dimension(0, 15));
		
		
		JPanel pnLeftBottomMain = new JPanel();
		pnLeftBottom.add(pnLeftBottomMain, BorderLayout.CENTER);
		pnLeftBottomMain.setPreferredSize(new Dimension(WIDTH, 100));
		pnLeftBottomMain.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		
		btnReportBill = new JButton("Report Bill");
		btnReportBill.setFocusable(false);
		Image imgReportBill = new ImageIcon("img/report.png").getImage();
		btnReportBill.setIcon(new ImageIcon(imgReportBill));
		btnReportBill.setPreferredSize(new Dimension(130, 40));
		btnReportBill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnLeftBottomMain.add(btnReportBill);
		
		btnTimKiem = new JButton("  Tìm Kiếm");
		btnTimKiem.setFocusable(false);
		btnTimKiem.setPreferredSize(new Dimension(120, 40));
		btnTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgTimKiem = new ImageIcon("img/timKiem.png").getImage();
		btnTimKiem.setIcon(new ImageIcon(imgTimKiem));
		pnLeftBottomMain.add(btnTimKiem);
		
		JPanel pnSX = new JPanel();
		pnSX.setPreferredSize(new Dimension(160, 70));
		pnLeftBottomMain.add(pnSX);
		Border b = BorderFactory.createLineBorder(Color.black);
		TitledBorder titlePNSX = new TitledBorder(b, "Sắp Xếp");
		pnSX.setBorder(titlePNSX);
		pnSX.setLayout(new GridLayout(2, 2, 10, 10));
		
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
		
		
		JPanel pnCenter = new JPanel();
		con.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(Color.green);
		pnCenter.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.black));
		

		JPanel pnRightLeft = new JPanel();
		pnCenter.add(pnRightLeft, BorderLayout.WEST);
		pnRightLeft.setPreferredSize(new Dimension(22, HEIGHT));
		JPanel pnRightRight = new JPanel();
		pnCenter.add(pnRightRight, BorderLayout.EAST);
		pnRightRight.setPreferredSize(new Dimension(22, HEIGHT));
		
		
		JPanel pnCenterTop = new JPanel();
		pnCenter.add(pnCenterTop, BorderLayout.NORTH);
		pnCenterTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnCenterTop.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
		JLabel lblCenterTopTitle = new  JLabel("Chi Tiết Đơn Hàng");
		lblCenterTopTitle.setFont(new Font("serif", Font.ITALIC, 17));
		pnCenterTop.add(lblCenterTopTitle);
		
		
		pnCenterCenter = new JPanel();
		pnCenter.add(pnCenterCenter, BorderLayout.CENTER);
		pnCenterCenter.setLayout(new BorderLayout());
		
		/*Bill bill = BillService.GetAllBillFromAccount(5).get(0);
		JPanel pnMain = new DetailBill(bill);
		pnCenterCenter.add(pnMain, BorderLayout.CENTER);*/
		
		

		
		JPanel pnBottom = new JPanel();
		con.add(pnBottom, BorderLayout.SOUTH);
		pnBottom.setPreferredSize(new Dimension(WIDTH, 60));
		pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -0));
		pnBottom.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		
		String str = "Created by \"Nguyễn Mạnh Cường\"";
		JPanel pnBottomLeft = new JPanelWordRun(20, str, 0, 10, 300, 25);
		pnBottom.add(pnBottomLeft);
		pnBottomLeft.setLayout(new BorderLayout());
		pnBottomLeft.setPreferredSize(new Dimension(700, 60));

		
		btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.setPreferredSize(new Dimension(120, 40));
		btnQuayLai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgQuayLai = new ImageIcon("img/quayLai.png").getImage();
		btnQuayLai.setIcon(new ImageIcon(imgQuayLai));
		pnBottom.add(btnQuayLai);
	}
	public void initDanhSachDonHang() {
		// TODO Auto-generated method stub		
		
		ArrayList<Bill> arrBill = BillService.GetAllBillIsActived(1);
		//ArrayList<Bill> arrBill = BillService.GetAllBillFromAccount(5);
		
		tblDonHangDaDat.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(1).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(2).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(3).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(4).setCellRenderer(new CustomRenderer());
		tblDonHangDaDat.getColumnModel().getColumn(5).setCellRenderer(new CustomRenderer());
		
		dtmDonHangDaDat.setRowCount(0);
		
		for(int i = 0;i<arrBill.size();i++) {
			Vector<String> vBill = new Vector<String>();
			vBill.add(String.valueOf(arrBill.get(i).getId()));
			vBill.add(arrBill.get(i).getBillName());
			vBill.add(String.valueOf(sdf.format(arrBill.get(i).getDateOfOrder())));
			vBill.add(String.valueOf(sdf.format(arrBill.get(i).getNgayNhanHang())));
			vBill.add(String.valueOf(arrBill.get(i).getAmountProduct()));
			vBill.add(String.valueOf(arrBill.get(i).getPriceTotal()));
			if(arrBill.get(i).getActiveBill() == 1) {
				dtmDonHangDaDat.addRow(vBill);
			}
		}
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
		this.setSize(900, 600);
		this.setResizable(false); // không cho phóng to màn hình
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		QuanLyDonHangUI ui = new QuanLyDonHangUI("Quản Lý Đơn Hàng");
		ui.showWindow();
	}

}

class DetailBill extends JPanel {
	
	Bill bill;
	JButton btnReportDetailBill;
	
	String imgStrDateFeedback = "img/imgFeedback/date.png";
	String imgStrTen = "img/imgFeedback/person.png";
	String imgBill = "img/bill.png";
	
	String imgPhone = "img/imgNewBill/phone.png";
	String imgAmountProduct = "img/imgNewBill/amountProduct.png";
	String imgPriceTotal = "img/imgNewBill/priceTotal.png";
	String imgAddress = "img/imgNewBill/home.png";
	String imgDescribe = "img/imgNewBill/describe.png";
	
	public DetailBill(Bill bill) {
		
		this.bill = bill; 
		addControls();
		addEvents();
		//Border b = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.blue);
		//this.setBorder(b);
		//this.setPreferredSize(new Dimension(520, 380));
		this.setVisible(true);
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnReportDetailBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(null, bill.getBillName());
				xulyReportDetailBill(bill.getId());
			}
		});
	}

	protected void xulyReportDetailBill(int id) {
		// TODO Auto-generated method stub
		try {
			Hashtable map = new Hashtable();
			JasperReport report1 = JasperCompileManager.compileReport("gui/ChiTietHoaDon.jrxml");
			
			map.put("idBill", id);
			
			
			JasperPrint p1 = JasperFillManager.fillReport(report1, map, LoginService.connection);
			JasperViewer.viewReport(p1, false);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void addControls() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());
		
		
		// Top
				JPanel pnTop = new JPanel();
				pnTop.setLayout(new BorderLayout());
				pnTop.setPreferredSize(new Dimension(0, 110));
				pnTop.setBackground(Color.BLUE);
				this.add(pnTop, BorderLayout.NORTH);
				
				/*JPanel pnTopTitle = new JPanel();
				pnTop.add(pnTopTitle, BorderLayout.NORTH);
				pnTopTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lblTopTitle = new JLabel(bill.getBillName());
				lblTopTitle.setFont(new Font("serif", Font.ITALIC, 24));
				ImageIcon imgGioHang = new ImageIcon("img/imgProduct/cart-icon.png");
				lblTopTitle.setIcon(imgGioHang);
				pnTopTitle.add(lblTopTitle);*/
				
				Image imgTenBill = new ImageIcon(imgBill).getImage();
				Image imgDate = new ImageIcon(imgStrDateFeedback).getImage();
				
				Font fontBill = new Font("serif", Font.ITALIC, 15);
				JPanel pnTopMain = new JPanel();
				JScrollPane scTopMain = new JScrollPane(pnTopMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				Border bTopMain = BorderFactory.createLineBorder(Color.black, 1);
				TitledBorder titleTopMain = new TitledBorder(bTopMain);
				titleTopMain.setTitle("Thông Tin Hóa Đơn");
				scTopMain.setBorder(titleTopMain);
				pnTop.add(scTopMain, BorderLayout.CENTER);
				pnTopMain.setLayout(new BoxLayout(pnTopMain, BoxLayout.Y_AXIS));
				JLabel lblTopMainName = new JLabel(" Bill Name: " + bill.getBillName());
				lblTopMainName.setIcon(new ImageIcon(imgTenBill));
				lblTopMainName.setFont(fontBill);
				JLabel lblTopMainPhone = new JLabel(" Phone: " + bill.getPhoneBill());
				lblTopMainPhone.setIcon(new ImageIcon(imgPhone));
				lblTopMainPhone.setFont(fontBill);
				JLabel lblTopMainDate = new JLabel(" Date Of Order: " + bill.getDateOfOrder());
				lblTopMainDate.setIcon(new ImageIcon(imgDate));
				lblTopMainDate.setFont(fontBill);
				JLabel lblTopMainDate2 = new JLabel(" Ngày Nhận Hàng: " + bill.getNgayNhanHang());
				lblTopMainDate2.setIcon(new ImageIcon(imgDate));
				lblTopMainDate2.setFont(fontBill);
				JLabel lblTopMainAmountProduct = new JLabel(" Amount Product: " + bill.getAmountProduct());
				lblTopMainAmountProduct.setIcon(new ImageIcon(imgAmountProduct));
				lblTopMainAmountProduct.setFont(fontBill);
				JLabel lblTopMainPriceTotal = new JLabel(" Price Total: " + bill.getPriceTotal() + " $");
				lblTopMainPriceTotal.setIcon(new ImageIcon(imgPriceTotal));
				lblTopMainPriceTotal.setFont(fontBill);
				JLabel lblTopMainAddress = new JLabel("<html>Address: " + bill.getAddressNhanHang());
				lblTopMainAddress.setPreferredSize(new Dimension(100, 50));
				lblTopMainAddress.setIcon(new ImageIcon(imgAddress));
				lblTopMainAddress.setFont(fontBill);
				JLabel lblTopMainDescribe = new JLabel();
				lblTopMainDescribe.setPreferredSize(new Dimension(10, 50));
				lblTopMainDescribe.setText("<html> Describe: " + bill.getDescribeBill());
				lblTopMainDescribe.setIcon(new ImageIcon(imgDescribe));
				lblTopMainDescribe.setFont(fontBill);
				
				JLabel lblNganCach1 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach2 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach3 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach4 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach5 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach6 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach7 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach8 = new JLabel("------------------------------------------------------------------------");
				JLabel lblNganCach9 = new JLabel("------------------------------------------------------------------------");
				lblNganCach1.setFont(fontBill);
				lblNganCach2.setFont(fontBill);
				lblNganCach3.setFont(fontBill);
				lblNganCach4.setFont(fontBill);
				lblNganCach5.setFont(fontBill);
				lblNganCach6.setFont(fontBill);
				lblNganCach7.setFont(fontBill);
				pnTopMain.add(lblNganCach1);
				pnTopMain.add(lblTopMainName);
				pnTopMain.add(lblNganCach2);
				pnTopMain.add(lblTopMainPhone);
				pnTopMain.add(lblNganCach3);
				pnTopMain.add(lblTopMainDate);
				pnTopMain.add(lblNganCach4);
				pnTopMain.add(lblTopMainDate2);
				pnTopMain.add(lblNganCach5);
				pnTopMain.add(lblTopMainAmountProduct);
				pnTopMain.add(lblNganCach6);
				pnTopMain.add(lblTopMainPriceTotal);
				pnTopMain.add(lblNganCach7);
				pnTopMain.add(lblTopMainAddress);
				pnTopMain.add(lblNganCach8);
				pnTopMain.add(lblTopMainDescribe);
				pnTopMain.add(lblNganCach9);
				
				/*JPanel pnTopLeft = new JPanel();
				pnTopLeft.setPreferredSize(new Dimension(20, 0));
				pnTop.add(pnTopLeft, BorderLayout.WEST);*/
				
				
				JPanel pnCenter = new JPanel();
				pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
				JScrollPane scCenter = new JScrollPane(pnCenter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				this.add(scCenter, BorderLayout.CENTER);
				
				ArrayList<Integer> arrProductID = BillService.getProductFromBillID(bill.getId());
	
				if (arrProductID.size() > 0) {
					for (int i = 0; i < arrProductID.size(); i++) {
						ProductInformation p = ProductService.getProduct(arrProductID.get(i));
						JPanel pn = new BillProductUI(p, bill.getActiveBill());
						pnCenter.add(pn);
					}
					if (arrProductID.size() == 1) {
						JPanel pn = new JPanel();
						pn.setPreferredSize(new Dimension(0, 130));
						pn.setLayout(new BorderLayout());
						pnCenter.add(pn);
					}
				}
				else {
					Image img = new ImageIcon("img/imgGift/curry.gif").getImage().getScaledInstance(330, 330, Image.SCALE_DEFAULT);
					JPanel pn = new BackGroundImage(img);
					pnCenter.add(pn);
				}
				
				
				JPanel pnCenterBottom = new JPanel();
				this.add(pnCenterBottom, BorderLayout.SOUTH);
				pnCenterBottom.setPreferredSize(new Dimension(WIDTH, 60));
				pnCenterBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
				btnReportDetailBill = new JButton("Report Detail Bill");
				pnCenterBottom.add(btnReportDetailBill);
				btnReportDetailBill.setPreferredSize(new Dimension(170, 40));
				btnReportDetailBill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Image imgReport = new ImageIcon("img/report.png").getImage();
				btnReportDetailBill.setIcon(new ImageIcon(imgReport));
	}
	
}
