package createdbyNguyenManhCuong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import createdbyNguyenManhCuong.connect.ProductService;
import createdbyNguyenManhCuong.model.ProductInformation;

public class OtherProductsUI extends JPanel {

	ProductInformation product;
	
	String imgStrTen = "img/imgFeedback/person.png";
	String imgStrTrademark = "img/imgFeedback/nmc.png";
	String imgStrType = "img/imgFeedback/type.png";
	String imgStrPrice = "img/imgFeedback/price.png";
	
	public OtherProductsUI(ProductInformation product) {
		super();
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
		this.setPreferredSize(new Dimension(300, 200));
		Border b = BorderFactory.createMatteBorder(0 , 0, 2, 0, Color.pink);
		this.setBorder(b);
		this.setVisible(true);
	}
	
	private void addControls() {
		// TODO Auto-generated method stub
		//Container con = getContentPane();
		//con.setLayout(new BorderLayout());
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
		
		JLabel lblTen = new JLabel("Name: " + product.getProductName());
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
		pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnBottom.setPreferredSize(new Dimension(0, 55));
		JLabel lblBottomName = new JLabel(product.getProductName());
		lblBottomName.setForeground(Color.blue);
		Font fontlblBottomName = new Font("Italic", Font.ITALIC, 22);
		lblBottomName.setFont(fontlblBottomName);
		pnBottom.add(lblBottomName);
		pnMain.add(pnBottom, BorderLayout.SOUTH);
		
		Image imgProduct = new ImageIcon(product.getProductImage()).getImage();
		JPanel pnCenter = new BackGroundImage(imgProduct);
		Border b = BorderFactory.createLineBorder(Color.BLACK, 2);
		pnCenter.setBorder(b);
		pnMain.add(pnCenter, BorderLayout.CENTER);
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		this.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  GetUI.feedbackProductUI.setVisible(false);
				  FeedbackProductUI ui = new FeedbackProductUI("Feedback Product", product);
				  ui.showWindow();
	          }
		});
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductInformation p = ProductService.getProduct(1);
		OtherProductsUI ui = new OtherProductsUI(p);
		
	}

}
