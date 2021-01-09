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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import createdbyNguyenManhCuong.connect.BillService;
import createdbyNguyenManhCuong.connect.FeedbackProductOtherService;
import createdbyNguyenManhCuong.connect.LoginService;
import createdbyNguyenManhCuong.connect.ProductService;
import createdbyNguyenManhCuong.model.AccountInformation;
import createdbyNguyenManhCuong.model.FeedbackProduct;
import createdbyNguyenManhCuong.model.ProductInformation;

public class FeedbackProductUI extends JDialog{
	
	ProductInformation product;
	
	String imgStrTen = "img/imgFeedback/person.png";
	String imgStrTrademark = "img/imgFeedback/nmc.png";
	//String imgStrProductType = "img/imgFeedback/person.png";
	String imgStrPrice = "img/imgFeedback/price.png";
	String imgStrAmount = "img/imgFeedback/amount.png";
	String imgStrPriceTotal = "img/imgFeedback/priceTotal.png";
	String imgStrDescribe = "img/imgFeedback/describe.png";
	String imgStrStar = "img/imgFeedback/star.png";
	String imgStrComment = "img/imgFeedback/comment.png";
	
	String imgStrDateFeedback = "img/imgFeedback/date.png";
	String imgStrFeedback = "img/imgFeedback/feedback.png";
	
	JButton btnDatHang, btnCancel;
	
	JPanel pnOtherProducts;
	
	String strGach = "-------------------------------------------";
	
	JComboBox<Integer> cboDanhGia;
	JTextArea txtMyComment;
	JButton btnFeedback;
	
	
	JPanel pnDanhGiaCenterLeftMain;
	JCheckBox cbFeedbackOther;
		
	public FeedbackProductUI(String title, ProductInformation product) {
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
		this.product = product;
		GetUI.setFeedbackProductUI(this);
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
				//JOptionPane.showMessageDialog(null, "Chưa được làm");
				xuLyThemSanPhamVaoGioHang();
			}
		});
		cbFeedbackOther.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				initFeedbackProductOther();
				setVisible(true);
			}
		});
		btnFeedback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyFeedback();
			}
		});
	}
	protected void xuLyThemSanPhamVaoGioHang() {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "Bạn Đã ấn chọn thêm sản phẩm có id: " + product.getId());
		if(GetUI.sanPhamUI.tblDanhSachDonHang.getSelectedRow() != -1) {
			Object objBill = GetUI.sanPhamUI.tblDanhSachDonHang.getValueAt(GetUI.sanPhamUI.tblDanhSachDonHang.getSelectedRow(), 0);
			int billID = Integer.parseInt((String)objBill);
			
			ArrayList<Integer> arrProductID = BillService.getProductFromBillID(billID);
			int flag = 1;
			for(int i = 0;i<arrProductID.size();i++) {
				if(arrProductID.get(i) == product.getId()) {
					flag = 0;
					break;
				}
			}
			if(flag == 1) {
				if(BillService.addProductIntoBill(billID, product.getId()) == true && BillService.updateAmountProduct_PriceTotalFromBill(billID, 1, (int)product.getProductPrice()) == true) {
					JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm Thành Công");
				}
				else JOptionPane.showMessageDialog(null, "Đã Gặp Sự Cố Khi Thêm Sản Phẩm Vô Bill");
			}
			else JOptionPane.showMessageDialog(null, "Sản Phẩm Đã Có Trong Giỏ Hàng");
		}
		else {
			JOptionPane.showMessageDialog(null, "Bạn Vui Lòng Hãy Chọn Hóa Đơn Để Thực Hiện Chức Năng Này!");
		}
	}
	protected void xuLyFeedback() {
		// TODO Auto-generated method stub
		int choose = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Gửi Feedback Này Đi Không", "Xác Nhận", JOptionPane.YES_NO_OPTION);
		if(choose == JOptionPane.YES_OPTION) {
			FeedbackProduct f = new FeedbackProduct();
			f.setAccountID(GetUI.loginUI.account.getId());
			f.setProductID(product.getId());
			f.setProductAssessment((int) cboDanhGia.getSelectedItem());
			f.setProductComment(txtMyComment.getText());
			if(FeedbackProductOtherService.insertFeedbackProduct(f) == true) {
				JOptionPane.showMessageDialog(null, "Thêm Feedback thành công");
				initFeedbackProductOther();
				this.setVisible(true);
			}
			else JOptionPane.showMessageDialog(null, "Hệ thống đang gặp sự cố");
		}
	}
	private void addControls() {
		// TODO Auto-generated method stub
		Border borderBottom = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK);
		Border borderTop = BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK);
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		// TOP
		JPanel pnTitle = new JPanel();
		pnTitle.setBorder(borderBottom);
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitle = new JLabel("Feedback Product");
		Font fontTitle = new Font("ravie", Font.BOLD, 28);
		lblTitle.setFont(fontTitle);
		pnTitle.add(lblTitle);
		con.add(pnTitle, BorderLayout.NORTH);
		
		
		// CENTER
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		JScrollPane scCenter = new JScrollPane(pnCenter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		con.add(scCenter, BorderLayout.CENTER);
		
		JPanel pnCenterInformation =  new JPanel();
		pnCenter.add(pnCenterInformation);
		//Border borderpnCenterInformation = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK);
		//pnCenterInformation.setBorder(borderpnCenterInformation);
		pnCenterInformation.setPreferredSize(new Dimension(0, 800));
		//pnCenterInformation.setLayout(new BorderLayout());
		
		JPanel pnInformation = new JPanel();
		pnInformation.setLayout(new BorderLayout());
		pnCenterInformation.add(pnInformation);
		
		JPanel pnImgProduct = new JPanel();
		pnImgProduct.setPreferredSize(new Dimension(365, 450));
		//pnImgProduct.setBorder(borderpnCenterInformation);
		pnImgProduct.setLayout(new BorderLayout());
		Image img = new ImageIcon(product.getProductImage()).getImage().getScaledInstance(365, 355, Image.SCALE_SMOOTH);
		JPanel pnAvatar = new BackGroundImage(img);
		pnImgProduct.add(pnAvatar, BorderLayout.CENTER);
		
		pnInformation.add(pnImgProduct, BorderLayout.NORTH);
		
		JPanel pnImgProductTop = new JPanel();
		pnImgProductTop.setPreferredSize(new  Dimension(0, 20));
		pnImgProduct.add(pnImgProductTop, BorderLayout.NORTH);
		/*JPanel pnImgProductLeft = new JPanel();
		pnImgProductLeft.setPreferredSize(new  Dimension(20, 0));
		pnImgProduct.add(pnImgProductLeft, BorderLayout.WEST);
		JPanel pnImgProductRight = new JPanel();
		pnImgProductRight.setPreferredSize(new  Dimension(20, 0));
		pnImgProduct.add(pnImgProductRight, BorderLayout.EAST);*/
		
		JPanel pnImgProductBottom = new JPanel();
		pnImgProductBottom.setPreferredSize(new  Dimension(0, 60));
		pnImgProduct.add(pnImgProductBottom, BorderLayout.SOUTH);
		JLabel lblProductName = new JLabel();
		Font fontProductName = new Font("Serif", Font.ITALIC, 25);
		lblProductName.setFont(fontProductName);
		lblProductName.setText(strGach + product.getProductName() + strGach);
		pnImgProductBottom.add(lblProductName);
		
		
		JPanel pnThongTinSanPham = new JPanel();
		pnThongTinSanPham.setPreferredSize(new Dimension(0, 400));
	//	pnThongTinSanPham.setBorder(borderpnCenterInformation);
		pnThongTinSanPham.setLayout(new BoxLayout(pnThongTinSanPham, BoxLayout.Y_AXIS));
		pnInformation.add(pnThongTinSanPham, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightTitle = new JPanel();
		pnTrangChuRightTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTrangChuRightTitle = new JLabel("Chi Tiết Sản Phẩm");
		lblTrangChuRightTitle.setForeground(new Color(79, 213, 157));
		Font fontlblTrangChuRightTitle = new Font("Serif", Font.BOLD, 23);
		lblTrangChuRightTitle.setFont(fontlblTrangChuRightTitle);
		pnTrangChuRightTitle.add(lblTrangChuRightTitle);
		pnThongTinSanPham.add(pnTrangChuRightTitle);
		
		JLabel lblID1 = new JLabel("  < ID: ");
		lblID1.setForeground(Color.RED);
		JLabel lblID2 = new JLabel(" >  ");
		lblID2.setForeground(Color.RED);
		JTextField txtID = new JTextField(3);
		txtID.setText(String.valueOf(product.getId()));
		txtID.setEditable(false);
		pnTrangChuRightTitle.add(lblID1);
		pnTrangChuRightTitle.add(txtID);
		pnTrangChuRightTitle.add(lblID2);
		
		Font fontThongTin = new Font("Serif", Font.ITALIC, 17);
		
		
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new BoxLayout(pnTen, BoxLayout.Y_AXIS));
		JPanel pnTenMain = new JPanel();
		pnTenMain.setLayout(new FlowLayout());
		JLabel lblTen = new JLabel("Name: ");
		Image imgTen = new ImageIcon(imgStrTen).getImage();
		lblTen.setIcon(new ImageIcon(imgTen));
		lblTen.setFont(fontThongTin);
		pnTenMain.add(lblTen);
		JTextField txtTen = new JTextField(18);
		txtTen.setText(product.getProductName());
		txtTen .setEditable(false);
		pnTenMain.add(txtTen);
		pnTen.add(pnTenMain);
		pnThongTinSanPham.add(pnTen);
		
		JPanel pnNhanHieu = new JPanel();
		pnNhanHieu.setLayout(new BoxLayout(pnNhanHieu, BoxLayout.Y_AXIS));
		JPanel pnNhanHieuMain = new JPanel();
		pnNhanHieuMain.setLayout(new FlowLayout());
		JLabel lblNhanHieu = new JLabel("Nhãn Hiệu: ");
		lblNhanHieu.setFont(fontThongTin);
		Image imgNhanHieu = new ImageIcon(imgStrTrademark).getImage();
		lblNhanHieu.setIcon(new ImageIcon(imgNhanHieu));
		pnNhanHieuMain.add(lblNhanHieu);
		JTextField txtNhanHieu = new JTextField(8);
		txtNhanHieu.setText(product.getProductTrademark());
		txtNhanHieu.setEditable(false);
		pnNhanHieuMain.add(txtNhanHieu);
		pnNhanHieu.add(pnNhanHieuMain);
		pnThongTinSanPham.add(pnNhanHieu);
		
		JPanel pnType = new JPanel();
		JComboBox<String> cboLoai = new JComboBox<String>();
		cboLoai.setPreferredSize(new Dimension(96, 28));
		cboLoai.addItem(product.getProductType());
		cboLoai.setEnabled(false);
		pnNhanHieuMain.add(cboLoai);
		
		JPanel pnPrice = new JPanel();
		pnPrice.setLayout(new BoxLayout(pnPrice, BoxLayout.Y_AXIS));
		JPanel pnPriceMain = new JPanel();
		pnPriceMain.setLayout(new FlowLayout());
		JLabel lblPrice = new JLabel("Price($): ");
		lblPrice.setFont(fontThongTin);
		Image imgPrice = new ImageIcon(imgStrPrice).getImage();
		lblPrice.setIcon(new ImageIcon(imgPrice));
		pnPriceMain.add(lblPrice);
		JTextField txtPrice = new JTextField(18);
		txtPrice.setEditable(false);
		txtPrice.setText(String.valueOf(product.getProductPrice()));
		pnPriceMain.add(txtPrice);
		pnPrice.add(pnPriceMain);
		pnThongTinSanPham.add(pnPrice);
		
		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new BoxLayout(pnSoLuong, BoxLayout.Y_AXIS));
		JPanel pnSoLuongMain = new JPanel();
		pnSoLuongMain.setLayout(new FlowLayout());
		JLabel lblSoLuong = new JLabel("Số Lượng: ");
		lblSoLuong.setFont(fontThongTin);
		Image imgSoLuong = new ImageIcon(imgStrAmount).getImage();
		lblSoLuong.setIcon(new ImageIcon(imgSoLuong));
		pnSoLuongMain.add(lblSoLuong);
		JTextField txtSoLuong = new JTextField(18);
		txtSoLuong.setText(String.valueOf(product.getProductAmount()));
		txtSoLuong.setEditable(false);
		pnSoLuongMain.add(txtSoLuong);
		pnSoLuong.add(pnSoLuongMain);
		pnThongTinSanPham.add(pnSoLuong);
		
		JPanel pnTongTien = new JPanel();
		pnTongTien.setLayout(new BoxLayout(pnTongTien, BoxLayout.Y_AXIS));
		JPanel pnTongTienMain = new JPanel();
		pnTongTienMain.setLayout(new FlowLayout());
		JLabel lblTongTien = new JLabel("Tổng Tiền($): ");
		lblTongTien.setFont(fontThongTin);
		Image imgTongTien = new ImageIcon(imgStrPriceTotal).getImage();
		lblTongTien.setIcon(new ImageIcon(imgTongTien));
		pnTongTienMain.add(lblTongTien);
		JTextField txtTongTien = new JTextField(18);
		txtTongTien.setText(String.valueOf(product.getProductAmount() * product.getProductPrice()));
		txtTongTien.setEditable(false);
		pnTongTienMain.add(txtTongTien);
		pnTongTien.add(pnTongTienMain);
		pnThongTinSanPham.add(pnTongTien);
		
		lblTen.setPreferredSize(lblTongTien.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTongTien.getPreferredSize());
		lblPrice.setPreferredSize(lblTongTien.getPreferredSize());
		lblNhanHieu.setPreferredSize(lblTongTien.getPreferredSize());
		
		JPanel pnMoTa = new JPanel();
		pnMoTa.setPreferredSize(new Dimension(0, 90));
		pnMoTa.setLayout(new BoxLayout(pnMoTa, BoxLayout.Y_AXIS));
		JPanel pnMoTaTitle = new JPanel();
		pnMoTaTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTrang = new JLabel("      ");
		pnMoTaTitle.add(lblTrang);
		JLabel lblMoTa = new JLabel("Mô Tả-------------------------------------------");
		lblMoTa.setFont(fontThongTin);
		Image imgMoTa = new ImageIcon(imgStrDescribe).getImage();
		lblMoTa.setIcon(new ImageIcon(imgMoTa));
		pnMoTaTitle.add(lblMoTa);
		pnMoTa.add(pnMoTaTitle);
		
		JPanel pnMoTaMain = new JPanel();
		pnMoTaMain.setLayout(new BorderLayout());
		JTextArea txtMoTa = new JTextArea();
		txtMoTa.setText(product.getProductDescribe());
		txtMoTa.setEditable(false);
		JScrollPane scMoTa = new JScrollPane(txtMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txtMoTa.setLineWrap(true);
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setEditable(false);
		pnMoTaMain.add(scMoTa, BorderLayout.CENTER);
		pnMoTa.add(pnMoTaMain);
		pnThongTinSanPham.add(pnMoTa);
		
		
		JPanel pnMoTaLeft = new JPanel();
		pnMoTaLeft.setPreferredSize(new Dimension(30, 0));
		pnMoTaMain.add(pnMoTaLeft, BorderLayout.WEST);
		JPanel pnMoTaRight = new JPanel();
		pnMoTaRight.setPreferredSize(new Dimension(30, 0));
		JPanel pnMoTaTop = new JPanel();
		pnMoTaTop.setPreferredSize(new Dimension(0, 2));
		pnMoTaMain.add(pnMoTaTop, BorderLayout.NORTH);
		pnMoTaMain.add(pnMoTaRight, BorderLayout.EAST);
		JPanel pnMoTabottom = new JPanel();
		pnMoTabottom.setPreferredSize(new Dimension(0, 20));
		pnMoTaMain.add(pnMoTabottom, BorderLayout.SOUTH);
		
		JPanel pntrang = new JPanel();
		pntrang.setPreferredSize(new Dimension(0, 50));
		pnThongTinSanPham.add(pntrang);
		
		JPanel pnDanhgia = new JPanel();
		pnDanhgia.setLayout(new BorderLayout());
		pnDanhgia.setBackground(Color.blue);
		pnDanhgia.setPreferredSize(new Dimension(0, 210));
		pnCenter.add(pnDanhgia);
		
		JPanel pnDanhGiaTop = new JPanel();
		//pnDanhGiaTop.setBackground(Color.pink);
		pnDanhGiaTop.setPreferredSize(new Dimension(0, 30));
		JLabel lblDanhGia = new JLabel("-----------------Feedback Product--------------------------------------------------");
		//lblDanhGia.setForeground(Color.red);
		Font fontlblDanhGia= new Font("Serif", Font.ITALIC, 17);
		lblDanhGia.setFont(fontlblDanhGia);
		pnDanhGiaTop.add(lblDanhGia);
		pnDanhgia.add(pnDanhGiaTop, BorderLayout.NORTH);
		
		JPanel pnDanhGiaCenter = new JPanel();
		pnDanhGiaCenter.setLayout(new BorderLayout());
	//	pnDanhGiaCenter.setBackground(Color.black);
		pnDanhgia.add(pnDanhGiaCenter, BorderLayout.CENTER);
		
		JPanel pnDanhGiaCenterLeft =  new JPanel();
		pnDanhGiaCenterLeft.setLayout(new BorderLayout());
		pnDanhGiaCenterLeft.setBackground(Color.CYAN);
		pnDanhGiaCenterLeft.setPreferredSize(new Dimension(202, 0));
		pnDanhGiaCenter.add(pnDanhGiaCenterLeft, BorderLayout.WEST);
		// Title Left
		JPanel pnOtherDanhGiaTitle = new JPanel();
		pnOtherDanhGiaTitle.setPreferredSize(new Dimension(0, 40));
		pnOtherDanhGiaTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnDanhGiaCenterLeft.add(pnOtherDanhGiaTitle, BorderLayout.NORTH);
		JLabel lblTitleDanhGiaOther = new JLabel("Other Feedback");
		Font fontTitleDanhGiaOther = new Font("ravie", Font.ITALIC, 14);
		lblTitleDanhGiaOther.setFont(fontTitleDanhGiaOther);
		pnOtherDanhGiaTitle.add(lblTitleDanhGiaOther);
		cbFeedbackOther = new JCheckBox();
		cbFeedbackOther.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbFeedbackOther.setVerticalAlignment(SwingConstants.TOP);
		pnOtherDanhGiaTitle.add(cbFeedbackOther);
		
		// Main Left
		pnDanhGiaCenterLeftMain = new JPanel();
		pnDanhGiaCenterLeftMain.setLayout(new BoxLayout(pnDanhGiaCenterLeftMain, BoxLayout.Y_AXIS));
		JScrollPane scDanhGiaOther = new JScrollPane(pnDanhGiaCenterLeftMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);;
		pnDanhGiaCenterLeft.add(scDanhGiaOther, BorderLayout.CENTER);
		initFeedbackProductOther();
		
		// Trắng left
		JPanel pnMyDanhGiaCenterLeftTrang1 = new JPanel();
		pnMyDanhGiaCenterLeftTrang1.setPreferredSize(new Dimension(5, 0));
		pnDanhGiaCenterLeft.add(pnMyDanhGiaCenterLeftTrang1, BorderLayout.EAST);
		
		// Right
		JPanel pnDanhGiaCenterRight =  new JPanel();
		pnDanhGiaCenterRight.setLayout(new BorderLayout());
		pnDanhGiaCenter.add(pnDanhGiaCenterRight, BorderLayout.EAST);
		// title
		JPanel pnMyDanhGiaTitle = new JPanel();
		pnMyDanhGiaTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnDanhGiaCenterRight.add(pnMyDanhGiaTitle, BorderLayout.NORTH);
		JLabel lblTitleDanhGiaMy = new JLabel("My Feedback");
		Font fontTitleDanhGia = new Font("ravie", Font.ITALIC, 14);
		lblTitleDanhGiaMy.setFont(fontTitleDanhGia);
		pnMyDanhGiaTitle.add(lblTitleDanhGiaMy);
		
		JPanel pnMyDanhGiaCenter = new JPanel();
		pnMyDanhGiaCenter.setLayout(new BorderLayout());
		pnDanhGiaCenterRight.add(pnMyDanhGiaCenter, BorderLayout.CENTER);
		// Đánh giá sao
		JPanel pnMyDanhGiaCenterDanhGia = new JPanel();
		//pnMyDanhGiaCenterDanhGia.setPreferredSize(new Dimension(100, 20));
		pnMyDanhGiaCenterDanhGia.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnMyDanhGiaCenter.add(pnMyDanhGiaCenterDanhGia, BorderLayout.NORTH);
		JLabel lblDanhGiaMy = new JLabel("Assessment: ");
		Image imgStar = new ImageIcon(imgStrStar).getImage();
		lblDanhGiaMy.setIcon(new ImageIcon(imgStar));
		pnMyDanhGiaCenterDanhGia.add(lblDanhGiaMy);
		cboDanhGia = new JComboBox<Integer>();
		cboDanhGia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnMyDanhGiaCenterDanhGia.add(cboDanhGia);
		cboDanhGia.addItem(1);
		cboDanhGia.addItem(2);
		cboDanhGia.addItem(3);
		cboDanhGia.addItem(4);
		cboDanhGia.addItem(5);
		cboDanhGia.setPreferredSize(new Dimension(40, 24));
		// Đánh giá bằng comment
		JPanel pnMyDanhGiaCenterBinhLuan = new JPanel();
		pnMyDanhGiaCenter.add(pnMyDanhGiaCenterBinhLuan, BorderLayout.CENTER);
		pnMyDanhGiaCenterBinhLuan.setLayout(new BorderLayout());
		JLabel lblMyComment = new JLabel(" My Comment ");
		Image imgComment = new ImageIcon(imgStrComment).getImage();
		lblMyComment.setIcon(new ImageIcon(imgComment));
		lblMyComment.setPreferredSize(new Dimension(0, 20));
		pnMyDanhGiaCenterBinhLuan.add(lblMyComment, BorderLayout.NORTH);
		
		txtMyComment = new JTextArea();
		txtMyComment.setText("Hãy Viết Bình Luận Của Bạn Về Sản Phẩm Này! Xin Cảm Ơn!");
		txtMyComment.setLineWrap(true);
		txtMyComment.setWrapStyleWord(true);
		JScrollPane scMyComment = new JScrollPane(txtMyComment, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnMyDanhGiaCenterBinhLuan.add(scMyComment, BorderLayout.CENTER);
		
		JPanel pnMyDanhGiaCenterRightTrang = new JPanel();
		pnMyDanhGiaCenterRightTrang.setPreferredSize(new Dimension(9, 0));
		pnDanhGiaCenterRight.add(pnMyDanhGiaCenterRightTrang, BorderLayout.WEST);
		
		JPanel pnMyDanhGiaCenterRightBottom = new JPanel();
		pnMyDanhGiaCenterRightBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnMyDanhGiaCenterRightBottom.setPreferredSize(new Dimension(0, 30));
		pnMyDanhGiaCenter.add(pnMyDanhGiaCenterRightBottom, BorderLayout.SOUTH);
		btnFeedback = new JButton("Feedback");
		btnFeedback.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFeedback.setPreferredSize(new Dimension(110, 25));
		Image imgFeedback = new ImageIcon(imgStrFeedback).getImage();
		btnFeedback.setIcon(new ImageIcon(imgFeedback));
		pnMyDanhGiaCenterRightBottom.add(btnFeedback);
		
		
		JPanel pnDanhGiaLeft = new JPanel();
		pnDanhGiaLeft.setPreferredSize(new Dimension(5, 0));
		pnDanhgia.add(pnDanhGiaLeft, BorderLayout.WEST);
		JPanel pnDanhGiaRight = new JPanel();
		pnDanhGiaRight.setPreferredSize(new Dimension(8, 0));
		pnDanhgia.add(pnDanhGiaRight, BorderLayout.EAST);
		/*JPanel pnDanhGiaBottom = new JPanel();
		pnDanhGiaBottom.setPreferredSize(new Dimension(0, 10));
		pnDanhgia.add(pnDanhGiaBottom, BorderLayout.SOUTH);*/
		

		JPanel pnCentertrang = new JPanel();
		//pnCentertrang.setBackground(Color.pink);
		pnCentertrang.setPreferredSize(new Dimension(0, 40));
		JLabel lblOtherProductsTitle = new JLabel("------------------Other Products--------------------");
		//lblOtherProductsTitle.setForeground(Color.red);
		Font fontlblOtherProductsTitle = new Font("Serif", Font.ITALIC, 40);
		lblOtherProductsTitle.setFont(fontlblTrangChuRightTitle);
		pnCentertrang.add(lblOtherProductsTitle);
		pnCenter.add(pnCentertrang);
		
		JPanel pnOtherProductsMain = new JPanel();
		pnOtherProductsMain.setPreferredSize(new Dimension(0, 210));
		pnOtherProductsMain.setLayout(new BorderLayout());
		pnCenter.add(pnOtherProductsMain);
		
		pnOtherProducts = new JPanel();
		pnOtherProducts.setLayout(new BoxLayout(pnOtherProducts, BoxLayout.Y_AXIS));
		//Border borderOtherProducts = BorderFactory.createLineBorder(Color.blue, 1);
		//pnOtherProducts.setBorder(borderOtherProducts);
		//pnOtherProducts.setPreferredSize(new Dimension(0, 200));
		JScrollPane scOtherProducts = new JScrollPane(pnOtherProducts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		initOtherProducts();
		pnOtherProductsMain.add(scOtherProducts);
		
		String str = "Đây là sản phẩm đầu tay nên còn nhiều sai sót, mong mọi người bỏ qua cho";
		JPanel pnIntroduceCenter = new JPanelWordRun(30, str, 0, 5, 200, 25);
		pnIntroduceCenter.setPreferredSize(new Dimension(0, 45));
		pnCenter.add(pnIntroduceCenter);
		
		
		// BOTTOM
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(0, 60));
		pnBottom.setBorder(borderTop);
		con.add(pnBottom, BorderLayout.SOUTH);
		
		JPanel pnBottomTop = new JPanel();
		pnBottomTop.setPreferredSize(new Dimension(0, 5));
		pnBottom.add(pnBottomTop, BorderLayout.NORTH);
		
		JPanel pnBottomMain = new JPanel();
		pnBottomMain.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//pnBottomMain.setPreferredSize(new Dimension(0, 60));
		//pnBottomMain.setBorder(borderTop);
		pnBottom.add(pnBottomMain, BorderLayout.CENTER);

		btnDatHang = new JButton("Add To Cart"); // thêm vào giỏ hàng
		btnDatHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgDatHang = new ImageIcon("img/imgProduct/cart-icon.png").getImage();
		btnDatHang.setIcon(new ImageIcon(imgDatHang));
		pnBottomMain.add(btnDatHang);
		
		JPanel pnBottomTrang1 = new JPanel();
		pnBottomTrang1.setPreferredSize(new Dimension(10, 0));
		pnBottomMain.add(pnBottomTrang1);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Image imgCancel = new ImageIcon("img/changePassword/cancel.png").getImage();
		btnCancel.setIcon(new ImageIcon(imgCancel));
		pnBottomMain.add(btnCancel);
		
		JPanel pnBottomTrang2 = new JPanel();
		pnBottomTrang2.setPreferredSize(new Dimension(10, 0));
		pnBottomMain.add(pnBottomTrang2);
		
	}
	private void initFeedbackProductOther() {
		// TODO Auto-generated method stub
		ArrayList<FeedbackProduct> arrFeedback = FeedbackProductOtherService.getAllFeedbackFromProduct(product.getId());
		 pnDanhGiaCenterLeftMain.removeAll();
		if (cbFeedbackOther.isSelected() == false) {
			Image img0 = new ImageIcon("img/imgGift/nba1.gif").getImage().getScaledInstance(178, 136,
					Image.SCALE_DEFAULT);
			JPanel pnTemp0 = new BackGroundImage(img0);
			//pnTemp0.setPreferredSize(new Dimension(0, 120));
			pnDanhGiaCenterLeftMain.add(pnTemp0);
		} else {
			if (arrFeedback.size() > 0) {
				JPanel arrPnFeedback = new JPanel();
				for (int i = 0; i < arrFeedback.size(); i++) {
					AccountInformation account = LoginService.getAccount(arrFeedback.get(i).getAccountID());

					JPanel pnFeedback = new JPanel();
					Border b = BorderFactory.createLineBorder(Color.black, 1);
					TitledBorder titleBorder = new TitledBorder(b);
					titleBorder.setTitle(account.getAccountName());
					pnFeedback.setBorder(titleBorder);
					// pnFeedback.setBackground(Color.BLACK);
					pnFeedback.setPreferredSize(new Dimension(0, 170));
					pnDanhGiaCenterLeftMain.add(pnFeedback);
					pnFeedback.setLayout(new BorderLayout());

					JPanel pnFeedbackTop = new JPanel();
					pnFeedbackTop.setLayout(new BoxLayout(pnFeedbackTop, BoxLayout.Y_AXIS));
					pnFeedback.add(pnFeedbackTop, BorderLayout.NORTH);
					// Cách để string xuống dòng trong JLabel là: jLabel.setText("<html>dong
					// 1<br>dong 2<br>dong 3");
					JLabel lblTen = new JLabel("<html>-User Name: " + account.getUserName());
					Image imgTen = new ImageIcon(imgStrTen).getImage();
					lblTen.setIcon(new ImageIcon(imgTen));
					pnFeedbackTop.add(lblTen);
					JLabel lblDate = new JLabel("-Date: " + arrFeedback.get(i).getDateOfAssessment());
					Image imgDate = new ImageIcon(imgStrDateFeedback).getImage();
					lblDate.setIcon(new ImageIcon(imgDate));
					pnFeedbackTop.add(lblDate);
					JLabel lblStar = new JLabel("-Star: " + arrFeedback.get(i).getProductAssessment());
					Image imgStar = new ImageIcon(imgStrStar).getImage();
					lblStar.setIcon(new ImageIcon(imgStar));
					pnFeedbackTop.add(lblStar);
					JLabel lblComment = new JLabel("-Comment---------------------");
					Image imgComment = new ImageIcon(imgStrComment).getImage();
					lblComment.setIcon(new ImageIcon(imgComment));
					pnFeedbackTop.add(lblComment);

					JPanel pnFeedbackComment = new JPanel();
					pnFeedbackComment.setLayout(new BorderLayout());
					pnFeedback.add(pnFeedbackComment, BorderLayout.CENTER);
					JTextArea txtComment = new JTextArea();
					txtComment.setEditable(false);
					txtComment.setText(arrFeedback.get(i).getProductComment());
					txtComment.setLineWrap(true);
					txtComment.setWrapStyleWord(true);
					JScrollPane scComment = new JScrollPane(txtComment, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					pnFeedbackComment.add(scComment);

					// System.out.println(arrFeedback.get(i));
				}
			} else {
				JPanel pnFeedbackTop = new JPanel();
				// pnFeedbackTop.setPreferredSize(new Dimension(0, 116));
				pnFeedbackTop.setLayout(new BorderLayout());
				JLabel lbl1 = new JLabel("<html>   <br> ------------THÔNG BÁO-------------");
				pnFeedbackTop.add(lbl1, BorderLayout.NORTH);
				JPanel pnCenter = new JPanel();
				pnFeedbackTop.setPreferredSize(new Dimension(0, 116));
				pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
				JLabel lbl = new JLabel();
				lbl.setText("<html> <br>\t-Hiện Tại Không Có Feedback Nào Của Khách Hàng!");
				pnCenter.add(lbl);
				pnFeedbackTop.add(pnCenter, BorderLayout.CENTER);
				pnDanhGiaCenterLeftMain.add(pnFeedbackTop);
				//this.setVisible(true);
			}
		}
		//this.setVisible(true);
	}
	private void initOtherProducts() {
		// TODO Auto-generated method stub
		
		
		ArrayList<ProductInformation> arr = ProductService.getAllProductFromType(product.getProductType());
		int n = arr.size();
		if(n > 3) {
			n = 3;
			for(int i = 0;i < n;i++) {
				JPanel pn =  new OtherProductsUI(arr.get(i));
				pnOtherProducts.add(pn);
			}
		}
	}
	public void showWindow() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		ProductInformation p = ProductService.getProduct(2);
		FeedbackProductUI ui = new FeedbackProductUI("Feedback Product", p);
		ui.showWindow();
	}
}
