package things;

import java.awt.Color;
import java.awt.Graphics2D;

import graphics.WorldRenderable;

public class UserSubmarine implements WorldRenderable {

	public void paint(float viewCenterX, float viewCenterY,
			float viewportWidth, float viewportHeight, float pixelWidth,
			Graphics2D g2) {
		
		g2.setColor(new Color(0xeeeeeeff));
		g2.drawOval(-5,-5,10,10);
	}
	
}
