package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import createdbyNguyenManhCuong.connect.BillService;
import createdbyNguyenManhCuong.model.ProductInformation;

public class ChiTietSanPham extends JPanel{
	
	 ProductInformation product;
	 
	 JButton btnShow, btnAdd;
	
	public ChiTietSanPham(ProductInformation product) {
		
		this.product = product; 
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
		addControls();
		addEvents();
		//this.setResizable(false);
		Border b = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.blue);
		this.setBorder(b);
		this.setPreferredSize(new Dimension(520, 380));
		this.setVisible(true);
		this.setToolTipText("Sản Phảm Chất Lượng Cao. Created By : \"Nguyễn Mạnh Cường\"");
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				xuLyShowProduct();
                
            }
		});
		btnShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyShowProduct();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyThemSanPhamVaoGioHang();
			}
		});
	}

	protected void xuLyShowProduct() {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "Bạn Đã ấn chọn sản phẩm có id: " + id);
		if(GetUI.feedbackProductUI != null) GetUI.feedbackProductUI.setVisible(false);
		FeedbackProductUI ui = new FeedbackProductUI("Feedback Product", product);
		ui.showWindow();
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


	private void addControls() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(10, 0));
		JPanel pnRight = new JPanel();
		pnRight.setPreferredSize(new Dimension(10, 0));
		JPanel pnTop = new JPanel();
		pnTop.setPreferredSize(new Dimension(0, 23));
		
		/*String str = "Created by \"Nguyễn Mạnh Cường\"";
		JPanel pnButtom = new JPanelWordRun(30, str, 0, 0, 500);
		pnButtom.setPreferredSize(new Dimension(0, 35));*/
		JPanel pnButtom = new JPanel();
		pnButtom.setPreferredSize(new Dimension(0, 55));
		pnButtom.setLayout(new BorderLayout());
		
		JPanel pnButtomMain = new JPanel();
		//pnButtomMain.setPreferredSize(new Dimension(0, 35));
		pnButtomMain.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnButtom.add(pnButtomMain, BorderLayout.CENTER);
		
		JPanel pnButtomTrang2 = new JPanel();
		pnButtomTrang2.setPreferredSize(new Dimension(10, 5));
		pnButtomMain.add(pnButtomTrang2);
		
		JPanel pnButtomMainGiaSp  = new JPanel();
		pnButtomMainGiaSp.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnButtomMainGiaSp.setPreferredSize(new Dimension(250, 35));
		Font fontGiaSp = new Font("Serif", Font.BOLD, 20);
		JLabel lblGiaSP = new JLabel();
		lblGiaSP.setForeground(Color.BLUE);
		lblGiaSP.setFont(fontGiaSp);
		lblGiaSP.setText("Price: " + product.getProductPrice() + " $");
		pnButtomMainGiaSp.add(lblGiaSP);
		pnButtomMain.add(pnButtomMainGiaSp);
		
		JPanel pnButtomTrang3 = new JPanel();
		pnButtomTrang3.setPreferredSize(new Dimension(10, 5));
		pnButtomMain.add(pnButtomTrang3);
		
		btnShow = new JButton("Show");
		btnShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnButtomMain.add(btnShow);
		Image imgShow = new ImageIcon("img/show.png").getImage();
		btnShow.setIcon(new ImageIcon(imgShow));
		
		JPanel pnButtomTrang1 = new JPanel();
		pnButtomTrang1.setPreferredSize(new Dimension(20, 5));
		pnButtomMain.add(pnButtomTrang1);
		
		btnAdd = new JButton("Add");
		Image imgAdd = new ImageIcon("img/imgProduct/add.png").getImage();
		btnAdd.setIcon(new ImageIcon(imgAdd));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnButtomMain.add(btnAdd);
		
		JPanel pnButtomTrang = new JPanel();
		pnButtomTrang.setPreferredSize(new Dimension(50, 5));
		pnButtomMain.add(pnButtomTrang);
		
		this.add(pnLeft, BorderLayout.WEST);
		this.add(pnRight, BorderLayout.EAST);
		this.add(pnTop, BorderLayout.NORTH);
		this.add(pnButtom, BorderLayout.SOUTH);
		
		JPanel pnTrangChuRight = new JPanel();
		pnTrangChuRight.setLayout(new BorderLayout());
		this.add(pnTrangChuRight, BorderLayout.CENTER);
		
		JPanel pnButtomButtom = new JPanel();
		pnButtomButtom.setPreferredSize(new Dimension(0, 7));
		pnButtom.add(pnButtomButtom, BorderLayout.SOUTH);
		
		JPanel pnTrangChuRightMain = new JPanel();
		pnTrangChuRightMain.setLayout(new BorderLayout());
		pnTrangChuRight.add(pnTrangChuRightMain, BorderLayout.CENTER);
		
		JPanel pnTrangChuRightMainLeft = new JPanel();
		pnTrangChuRightMainLeft.setPreferredSize(new Dimension(260, 0));
		pnTrangChuRightMainLeft.setLayout(new BoxLayout(pnTrangChuRightMainLeft, BoxLayout.Y_AXIS));
		pnTrangChuRightMain.add(pnTrangChuRightMainLeft, BorderLayout.WEST);
		
		JPanel  pnTrangChuRightTop = new JPanel();
		JLabel lblTitle = new JLabel(); 
		lblTitle.setText(product.getProductName());
		lblTitle.setForeground(Color.RED);
		Font fontTitle = new Font("ravie", Font.BOLD, 35);
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
		txtID.setText(String.valueOf(product.getId()));
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
		txtTen.setText(product.getProductName());
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
		JTextField txtNhanHieu = new JTextField(5);
		txtNhanHieu.setText(product.getProductTrademark());
		txtNhanHieu.setEditable(false);
		pnNhanHieuMain.add(txtNhanHieu);
		pnNhanHieu.add(pnNhanHieuMain);
		pnTrangChuRightMainLeft.add(pnNhanHieu);
		
		JPanel pnType = new JPanel();
		JComboBox<String> cboLoai = new JComboBox<String>();
		cboLoai.setPreferredSize(new Dimension(99, 27));
		cboLoai.addItem(product.getProductType());
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
		txtPrice.setText(String.valueOf(product.getProductPrice()));
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
		txtSoLuong.setText(String.valueOf(product.getProductAmount()));
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
		txtTongTien.setText(String.valueOf(product.getProductAmount() * product.getProductPrice()));
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
		pnTrangChuRightMainRight.setPreferredSize(new Dimension(240, 0));
		pnTrangChuRightMainRight.setLayout(new BoxLayout(pnTrangChuRightMainRight, BoxLayout.Y_AXIS));
		pnTrangChuRightMain.add(pnTrangChuRightMainRight, BorderLayout.EAST);
		
		JPanel pnImgProduct = new JPanel();
		pnImgProduct.setPreferredSize(new Dimension(120, 101));
		pnImgProduct.setLayout(new BorderLayout());
		pnTrangChuRightMainRight.add(pnImgProduct);
		
		JPanel pnImgProductTop = new JPanel();
		pnImgProductTop.setPreferredSize(new Dimension(0, 30));
		pnImgProduct.add(pnImgProductTop, BorderLayout.NORTH);
		JPanel pnImgProductLeft = new JPanel();
		pnImgProductLeft.setPreferredSize(new Dimension(56, 0));
		pnImgProduct.add(pnImgProductLeft, BorderLayout.WEST);
		JPanel pnImgProductRight = new JPanel();
		pnImgProductRight.setPreferredSize(new Dimension(54, 0));
		pnImgProduct.add(pnImgProductRight, BorderLayout.EAST);
		
		JPanel pnHeadRightAvatarMain = new JPanel();
		Border borderHeadRightAvatarMain = BorderFactory.createLineBorder(Color.BLACK, 3);
		pnHeadRightAvatarMain.setBorder(borderHeadRightAvatarMain);
		pnHeadRightAvatarMain.setLayout(new BorderLayout());
		pnImgProduct.add(pnHeadRightAvatarMain, BorderLayout.CENTER);

		Image img = new ImageIcon(product.getProductImage()).getImage().getScaledInstance(124, 124, Image.SCALE_DEFAULT);
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
		pnMoTa.setPreferredSize(new Dimension(0, 0));
		pnMoTa.setLayout(new BorderLayout());
		JLabel lblMoTa = new JLabel("   Mô Tả");
		pnMoTa.add(lblMoTa, BorderLayout.NORTH);
		
		JTextArea txtMoTa = new JTextArea();
		txtMoTa.setText(product.getProductDescribe());
		txtMoTa.setEditable(false);
		JScrollPane scMoTa = new JScrollPane(txtMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txtMoTa.setLineWrap(true);
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setEditable(false);
		pnMoTa.add(scMoTa);
		pnTrangChuRightMainRight.add(pnMoTa);
		
		JPanel pnMoTaButtom = new JPanel();
		pnMoTaButtom.setPreferredSize(new Dimension(0, 13));
		pnTrangChuRightMainRight.add(pnMoTaButtom);
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ChiTietSanPham pn = new ChiTietSanPham();
	}
}

