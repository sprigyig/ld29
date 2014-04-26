package Graphics;

import javax.swing.JFrame;

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
		BoatWindow bw = new BoatWindow(bp = new BoatPanel());

		GridLines gl = new GridLines();
		
		while(true) {
			Thread.sleep(1000);
			bp.addRenderable(RenderLayer.OVERLAY, gl);
			Thread.sleep(1000);
			bp.removeRenderable(RenderLayer.OVERLAY, gl);
		}
	}
}
