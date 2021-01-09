package createdbyNguyenManhCuong.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class BackGroundImage extends JPanel{
	public Image image;

	public BackGroundImage(Image image) {   
	       this.image = image;
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters 
    }
}
