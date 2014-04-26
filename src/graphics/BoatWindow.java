package graphics;

import javax.swing.JFrame;

import things.GridLines;
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
		
		while(true) {
			Thread.sleep(1000);
			bp.addRenderable(RenderLayer.OVERLAY, gl);
			Thread.sleep(1000);
			bp.removeRenderable(RenderLayer.OVERLAY, gl);
		}
	}
}
