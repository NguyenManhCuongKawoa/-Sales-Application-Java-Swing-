package createdbyNguyenManhCuong.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JPanelWordRun extends JPanel implements Runnable {

	int xStart, xTail, y;
	int xMax;
	int delay;
	JLabel lblWordRun;
	Dimension size; 
	
	public JPanelWordRun(int delay, String str, int x, int y, int xMax, int sizeWord) {
		init(delay, str, x, y, xMax, sizeWord);
		callThread();
	}
	private void callThread() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(this);
		thread.start();
	}
	private void init(int delay, String str, int x, int y, int xMax, int sizeWord) {
		// TODO Auto-generated method stub
		this.delay = delay;
		this.xStart = this.xTail = x;
		this.xMax = xMax;
		this.y = y;
		lblWordRun = new JLabel(str);
		this.add(lblWordRun);
		//Border border = BorderFactory.createLineBorder(Color.black);
		lblWordRun.setForeground(Color.DARK_GRAY);
		Font font = new Font("Serif", Font.ITALIC, sizeWord);
		lblWordRun.setFont(font);
		lblWordRun.setBounds(xStart, y, lblWordRun.getPreferredSize().width, lblWordRun.getPreferredSize().height);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			xTail++;
			if(xTail > xMax) xTail = xStart - lblWordRun.getPreferredSize().width;
			lblWordRun.setBounds(xTail, y, lblWordRun.getPreferredSize().width, lblWordRun.getPreferredSize().height);
			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
