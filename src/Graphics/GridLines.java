package Graphics;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class GridLines implements WorldRenderable {

	public void paint(float cx, float cy, float vpWidth, float vpHeight, float pixelWidth, 
			Graphics2D g2) {
		
		int linePow = (int) Math.round(Math.log(vpWidth) / Math.log(5) - 1.1);
		int lineSpace = (int) Math.pow(5, linePow);

		int xitrs = (int) (vpWidth / lineSpace);
		int basex = Math.round(cx / lineSpace) * lineSpace;

		int yitrs = (int) (vpHeight / lineSpace);
		int basey = Math.round(cy / lineSpace) * lineSpace;

		g2.setColor(ConstantHacks.mapLineColor());

		g2.setStroke(new BasicStroke(pixelWidth));

		for (int i = -xitrs / 2 - 2; i < xitrs / 2 + 2; i++) {
			g2.drawLine(basex + i * lineSpace, (int) (cy - vpHeight / 2), basex
					+ i * lineSpace, (int) (cy + vpHeight / 2));
		}

		for (int i = -yitrs / 2 - 2; i < yitrs / 2 + 2; i++) {
			g2.drawLine((int) (cx - vpWidth / 2), basex + i * lineSpace,
					(int) (cx + vpWidth / 2), (int) (basey + i * lineSpace));
		}
	}

}
