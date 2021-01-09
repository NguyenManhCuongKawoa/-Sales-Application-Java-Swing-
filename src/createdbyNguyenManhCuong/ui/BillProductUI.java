package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import createdbyNguyenManhCuong.connect.BillService;
import createdbyNguyenManhCuong.model.Bill;
import createdbyNguyenManhCuong.model.ProductInformation;

public class BillProductUI extends JPanel {
	
	ProductInformation product;
	
	String imgStrTen = "img/imgFeedback/person.png";
	String imgStrTrademark = "img/imgFeedback/nmc.png";
	String imgStrType = "img/imgFeedback/type.png";
	String imgStrPrice = "img/imgFeedback/price.png";
	
	JButton btnDelete, btnShow;
	int acticeBill;
	
	public BillProductUI(ProductInformation product, int acticeBill) {
		super();
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
		this.product = product;
		this.acticeBill = acticeBill;
		addControls();
		addEvents();
		this.setPreferredSize(new Dimension(300, 200));
		Border b = BorderFactory.createMatteBorder(0 , 0, 2, 0, Color.pink);
		this.setBorder(b);
		this.setVisible(true);
	}
	
	private void addControls() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());
		
		JPanel pnMain = new JPanel();
		this.add(pnMain);
		pnMain.setLayout(new BorderLayout());
		
		Font fontlblInformation = new Font("Serif", Font.ITALIC, 15);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(184, 0));
		pnLeft.setLayout(new BorderLayout());
		pnMain.add(pnLeft, BorderLayout.WEST);
		
		JPanel pnLeftWest = new JPanel();
		pnLeftWest.setPreferredSize(new Dimension(14, 0));
		pnLeft.add(pnLeftWest, BorderLayout.WEST);
		
		JPanel pnLeftMain = new JPanel();
		pnLeft.add(pnLeftMain, BorderLayout.CENTER);
		pnLeftMain.setLayout(new BoxLayout(pnLeftMain, BoxLayout.Y_AXIS));
		
		JLabel lblTen = new JLabel("Name: "  + product.getProductName());
		lblTen.setFont(fontlblInformation);
		Image imgTen = new ImageIcon(imgStrTen).getImage();
		lblTen.setIcon(new ImageIcon(imgTen));
		pnLeftMain.add(lblTen);
		
		JLabel lbl = new JLabel("	");
		pnLeftMain.add(lbl);
		
		JLabel lblNhanHieu = new JLabel("Nhãn Hiệu: " + product.getProductTrademark());
		lblNhanHieu.setFont(fontlblInformation);
		Image imgNhanHieu = new ImageIcon(imgStrTrademark).getImage();
		lblNhanHieu.setIcon(new ImageIcon(imgNhanHieu));
		pnLeftMain.add(lblNhanHieu);
		
		JLabel lbl1 = new JLabel("	");
		pnLeftMain.add(lbl1);
		
		JLabel lblType = new JLabel("Type: " + product.getProductType());
		lblType.setFont(fontlblInformation);
		Image imgType = new ImageIcon(imgStrType).getImage();
		lblType.setIcon(new ImageIcon(imgType));
		pnLeftMain.add(lblType);
		
		JLabel lbl2 = new JLabel("	");
		pnLeftMain.add(lbl2);
		
		JLabel lblPrice = new JLabel("Price($): " + product.getProductPrice());
		lblPrice.setFont(fontlblInformation);
		Image imgPrice = new ImageIcon(imgStrPrice).getImage();
		lblPrice.setIcon(new ImageIcon(imgPrice));
		pnLeftMain.add(lblPrice);
		
		
		
		JPanel pnRight = new JPanel();
		pnRight.setPreferredSize(new Dimension(35, 0));
		pnMain.add(pnRight, BorderLayout.EAST);
		JPanel pnTop = new JPanel();
		pnTop.setPreferredSize(new Dimension(0, 15));
		pnMain.add(pnTop, BorderLayout.NORTH);
		
		
		JPanel pnBottom = new JPanel();
		pnMain.add(pnBottom, BorderLayout.SOUTH);
		if(GetUI.loginUI.account.getId() != 1) pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 12));
		pnBottom.setPreferredSize(new Dimension(0, 55));
		JLabel lblBottomName = new JLabel(product.getProductName()+ "     ");
		lblBottomName.setForeground(Color.blue);
		Font fontlblBottomName = new Font("serif", Font.ITALIC, 22);
		lblBottomName.setFont(fontlblBottomName);
		pnBottom.add(lblBottomName);
		
		/*Panel pnBottomButtom = new JPanel();
		pnBottom.add(pnBottomButtom);
		pnBottomButtom.setBackground(Color.black);
		pnBottomButtom.setLayout(new FlowLayout(FlowLayout.RIGHT));*/
		if(GetUI.loginUI.account.getId() != 1) {
			btnShow = new JButton("Show");
			btnShow.setFocusable(false);
			btnShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			pnBottom.add(btnShow);
			Image imgShow = new ImageIcon("img/view.png").getImage();
			btnShow.setIcon(new ImageIcon(imgShow));
		
			btnDelete= new JButton("delete");
			if(acticeBill == 1) btnDelete.setEnabled(false);
			btnDelete.setFocusable(false);
			Image imgAdd = new ImageIcon("img/delete.png").getImage();
			btnDelete.setIcon(new ImageIcon(imgAdd));
			btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			pnBottom.add(btnDelete);
		}
		
		// phần image
		JPanel pnMainRight = new JPanel();
		pnMainRight.setLayout(new BorderLayout());
		pnMain.add(pnMainRight, BorderLayout.CENTER);
		
		JPanel pnTop1 = new JPanel();
		pnTop1.setPreferredSize(new Dimension(0, 20));
		pnMainRight.add(pnTop1, BorderLayout.NORTH);
		JPanel pnWest1 = new JPanel();
		pnWest1.setPreferredSize(new Dimension(10, 0));
		pnMainRight.add(pnWest1, BorderLayout.WEST);
		JPanel pnBottom1 = new JPanel();
		pnBottom1.setPreferredSize(new Dimension(0, 20));
		pnMainRight.add(pnBottom1, BorderLayout.SOUTH);
		
		Image imgProduct = new ImageIcon(product.getProductImage()).getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT);
		JPanel pnCenter = new BackGroundImage(imgProduct);
		pnMainRight.add(pnCenter, BorderLayout.CENTER);
		Border b = BorderFactory.createLineBorder(Color.BLACK, 2);
		pnCenter.setBorder(b);
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		this.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  if(GetUI.gioHangUI != null) GetUI.gioHangUI.setVisible(false);
				  FeedbackProductUI ui = new FeedbackProductUI("Feedback Product", product);
				  ui.showWindow();
	          }
		});
		if (GetUI.loginUI.account.getId() != 1) {
			btnShow.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (GetUI.gioHangUI != null)
						GetUI.gioHangUI.setVisible(false);
					FeedbackProductUI ui = new FeedbackProductUI("Feedback Product", product);
					ui.showWindow();
				}
			});
			btnDelete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object objBill = GetUI.sanPhamUI.tblDanhSachDonHang
							.getValueAt(GetUI.sanPhamUI.tblDanhSachDonHang.getSelectedRow(), 0);
					int billID = Integer.parseInt((String) objBill);
					if (BillService.deleteProduct(billID, product.getId()) == true) {
						JOptionPane.showMessageDialog(null, "Xóa Sản Phẩm Thành Công");
						Bill bill = BillService.getBillFromID(billID);
						GetUI.gioHangUI.initInformationBill(bill);
						GetUI.gioHangUI.initProduct();
						GetUI.gioHangUI.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "Đã Gặp Sự Cố Khi Xóa Sản Phẩm Vô Bill");
				}
			});
		}
	}
}
