package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import things.GridLines;
import things.SpriteRenderer;
import things.UserSubmarine;

public class BoatWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public BoatWindow(BoatPanel bp) {
		add(bp);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		BoatPanel bp;
		new BoatWindow(bp = new BoatPanel());

		GridLines gl = new GridLines();
		
		bp.addRenderable(RenderLayer.UNDER, new UserSubmarine());
		
		BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		g.setColor(Color.black);
		g.drawString("test", 50, 50);
		
		SpriteRenderer sr;
		bp.addRenderable(RenderLayer.ON, sr = new SpriteRenderer(bi, 50, 50));
		
		bp.addRenderable(RenderLayer.OVERLAY, gl);
		
		while(true) {
			sr.setRotation((float) (Math.PI*2 * (System.currentTimeMillis()%4000)/4000f));
			Thread.sleep(30);
			
		}
	}
}
