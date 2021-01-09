package createdbyNguyenManhCuong.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


class SurfaceRect extends JPanel implements ActionListener {

	int widthFirst = 35;
	int widthSecond = 35;
	int maxWidthSecond = 250;
	int heightFirst = 47;
	int heightSecond = 47;
	int DELAY = 20;
	Timer timer;

	public SurfaceRect() {
		initTimer();
	}

	private void initTimer() {
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.PINK);
		BasicStroke bs2 = new BasicStroke(18, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs2);
        g2d.drawLine(widthFirst, heightFirst, widthSecond, heightSecond);
		widthSecond += 1;
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}

	private void step() {

		if (widthSecond >= maxWidthSecond) {
			widthSecond = maxWidthSecond;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		step();
		repaint();
	}
}

public class NextUI extends JDialog implements Runnable {
	
	public static boolean flag = false;
	public static int phanTram = 0;
	JLabel lblLoad;
	JPanel pnLoad;

	public NextUI(String title) {
		flag = false;
		phanTram = 0;
		this.setTitle(title);
		ImageIcon icon = new ImageIcon("img/icon.png"); 
		this.setIconImage(icon.getImage()); 
		addControls();
		this.showWindow();
		callThread();
	}
    public void callThread() {
    	Thread thread = new Thread(this);
		thread.start();
    }
	public void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		
		JPanel pn = new SurfaceRect();
		//pn.setBackground(new Color(0,0,0,0));
		pn.setLayout(new BorderLayout());
		
		JPanel pnBackGround = BubbleWallpaper.showSurface(100000); // Bubble Wallpaper : hình nền bong bóng
		//JPanel pnBackGround = BackGroundImage.initbackGroundImage("img/imgBackground/background0.jpg");
		pnBackGround.setLayout(new BorderLayout());
		pnBackGround.setBackground(new Color(0,0,0,0));
		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		//pnMain.setBackground(new Color(0,0,0,0));

		pnMain.add(pnBackGround);
		pn.add(pnMain);
		con.add(pn);
		

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnTitle.setBackground(new Color(0,0,0,0));
		JLabel lblTitle = new JLabel(" Welcome To Basketball Shop");
		Font font2 = new Font("Serif", Font.BOLD, 18);
		lblTitle.setFont(font2);
		lblTitle.setForeground(Color.BLUE);
		pnTitle.add(lblTitle);
		pnBackGround.add(pnTitle, BorderLayout.NORTH);

		
		pnLoad = new JPanel();
		pnLoad.setBackground(new Color(0,0,0,0));
		pnLoad.setLayout(new FlowLayout(FlowLayout.CENTER));
		lblLoad = new JLabel();
		
		pnLoad.add(lblLoad);
		pnBackGround.add(pnLoad, BorderLayout.CENTER);

		JPanel pnKhoangTrang = new JPanel();
		pnKhoangTrang.setBackground(new Color(0,0,0,0));
		pnBackGround.add(pnKhoangTrang, BorderLayout.SOUTH);

		JPanel pnKhoangTrangLeft = new JPanel();
		pnKhoangTrangLeft.setBackground(new Color(0,0,0,0));
		pnBackGround.add(pnKhoangTrangLeft, BorderLayout.WEST);
		JPanel pnKhoangTrangRight = new JPanel();
		pnKhoangTrangRight.setBackground(new Color(0,0,0,0));
		pnBackGround.add(pnKhoangTrangRight, BorderLayout.EAST);

	}

	public void showWindow() {
		this.setSize(300, 106);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setModal(true);
		this.setVisible(true);
	}

	@Override
	public void run() {

		while (phanTram < 100) {
			if(this.isVisible() == false) break;
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			phanTram++;
			lblLoad.setText(String.valueOf(phanTram) + " %");
		}
		flag = true; // thread đã dừng chạy
		//System.out.println(flag);
		this.setVisible(false);
	}
}
