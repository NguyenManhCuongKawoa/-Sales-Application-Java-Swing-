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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import createdbyNguyenManhCuong.connect.BillService;
import createdbyNguyenManhCuong.connect.ProductService;
import createdbyNguyenManhCuong.model.Bill;
import createdbyNguyenManhCuong.model.ProductInformation;

public class GioHangUI extends JDialog {
	
	  Bill bill;
	  JPanel pnCenter;
	  JPanel pn;
	  JPanel pnTop;
	
	String imgStrDateFeedback = "img/imgFeedback/date.png";
	String imgStrTen = "img/imgFeedback/person.png";
	String imgBill = "img/bill.png";
	
	String imgPhone = "img/imgNewBill/phone.png";
	String imgAmountProduct = "img/imgNewBill/amountProduct.png";
	String imgPriceTotal = "img/imgNewBill/priceTotal.png";
	String imgAddress = "img/imgNewBill/home.png";
	String imgDescribe = "img/imgNewBill/describe.png";
	
	JButton btnDatHang, btnCancel;
	
	public GioHangUI(String title, Bill bill) {
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
		this.bill = bill;
		GetUI.setGioHangUI(this);
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
		btnDatHang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Integer> arrProductID = BillService.getProductFromBillID(bill.getId());
				if(arrProductID.size() == 0) {
					JOptionPane.showMessageDialog(null, "Bạn Chưa Có Sản Phẩm Nào Ở Trong Hóa Đơn Hết!");
				}
				else {
					if(BillService.setActiveBill(1, bill.getId()) == true) {
						JOptionPane.showMessageDialog(null, "Đơn Hàng đã được Đặt. Xin Cảm Ơn Quý Khách Đã Tin Tưởng Chúng Tôi");
						setVisible(false);
						GetUI.sanPhamUI.initDanhSachDonHang();
					
					}
					else JOptionPane.showMessageDialog(null, "Đã Sảy Ra lỗi Khi Đặt Hàng!");
				}
			}
		});
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		
		pn = new JPanel();
		con.add(pn);
		pn.setLayout(new BorderLayout());
		
		initInformationBill(bill);
		
		/*JPanel pnTopLeft = new JPanel();
		pnTopLeft.setPreferredSize(new Dimension(20, 0));
		pnTop.add(pnTopLeft, BorderLayout.WEST);*/
		
		
		pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		JScrollPane scCenter = new JScrollPane(pnCenter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pn.add(scCenter, BorderLayout.CENTER);
		
		initProduct();
		
		
		// Bottom
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 8));
		pnBottom.setPreferredSize(new Dimension(0, 50));
		//pnBottom.setBackground(Color.BLUE);
		pn.add(pnBottom, BorderLayout.SOUTH);
		
		btnDatHang = new JButton("Đặt Hàng");
		btnDatHang.setFocusable(false);
		btnDatHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgDatHang = new ImageIcon("img/imgFeedback/order.png").getImage();
		btnDatHang.setIcon(new ImageIcon(imgDatHang));
		pnBottom.add(btnDatHang);
		
	
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFocusable(false);
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgCancel = new ImageIcon("img/changePassword/cancel.png").getImage();
		btnCancel.setIcon(new ImageIcon(imgCancel));
		pnBottom.add(btnCancel);
		
	}

	public void initInformationBill(Bill bill1) {
		// TODO Auto-generated method stub
		if(pnTop != null) pn.remove(pnTop);
		pnTop = new InformationBill(bill1);
		pn.add(pnTop, BorderLayout.NORTH);
	}

	public void initProduct() {
		// TODO Auto-generated method stub
		ArrayList<Integer> arrProductID = BillService.getProductFromBillID(bill.getId());
		pnCenter.removeAll();
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
	}

	public void showWindow() {
		this.setSize(350, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		Bill b = BillService.GetAllBillFromAccount(5).get(0);
		GioHangUI ui = new GioHangUI("Giỏ Hàng", b);
		ui.showWindow();
	}
}

class InformationBill extends JPanel {
	
	String imgStrDateFeedback = "img/imgFeedback/date.png";
	String imgStrTen = "img/imgFeedback/person.png";
	String imgBill = "img/bill.png";
	
	String imgPhone = "img/imgNewBill/phone.png";
	String imgAmountProduct = "img/imgNewBill/amountProduct.png";
	String imgPriceTotal = "img/imgNewBill/priceTotal.png";
	String imgAddress = "img/imgNewBill/home.png";
	String imgDescribe = "img/imgNewBill/describe.png";
	
	public InformationBill(Bill bill) {
		// Top
				//JPanel pnTop = new JPanel();
				//pnTop.setLayout(new BorderLayout());
				//pnTop.setPreferredSize(new Dimension(0, 110));
				//pnTop.setBackground(Color.BLUE);
				//pn.add(pnTop, BorderLayout.NORTH);
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0, 110));
				
				JPanel pnTopTitle = new JPanel();
				this.add(pnTopTitle, BorderLayout.NORTH);
				pnTopTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lblTopTitle = new JLabel("Giỏ Hàng");
				lblTopTitle.setFont(new Font("serif", Font.ITALIC, 24));
				ImageIcon imgGioHang = new ImageIcon("img/imgProduct/cart-icon.png");
				lblTopTitle.setIcon(imgGioHang);
				pnTopTitle.add(lblTopTitle);
				
				Image imgTenBill = new ImageIcon(imgBill).getImage();
				Image imgDate = new ImageIcon(imgStrDateFeedback).getImage();
				
				Font fontBill = new Font("serif", Font.ITALIC, 15);
				JPanel pnTopMain = new JPanel();
				JScrollPane scTopMain = new JScrollPane(pnTopMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				Border bTopMain = BorderFactory.createLineBorder(Color.black, 1);
				TitledBorder titleTopMain = new TitledBorder(bTopMain);
				titleTopMain.setTitle("Thông Tin Hóa Đơn");
				scTopMain.setBorder(titleTopMain);
				this.add(scTopMain, BorderLayout.CENTER);
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
	}
}
