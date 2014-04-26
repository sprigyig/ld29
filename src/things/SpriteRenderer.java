package things;

import graphics.WorldRenderable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;


public class SpriteRenderer implements WorldRenderable {
	private Image bi;
	private float rotation;
	private int cx;
	private int cy;
	
	private float px, py;

	public SpriteRenderer(Image bi, int cx, int cy) {
		this.bi = bi;
		this.cx = cx;
		this.cy = cy;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public void setPosition(float px, float py) {
		this.px = px;
		this.py = py;
	}

	@Override
	public void paint(float viewCenterX, float viewCenterY,
			float viewportWidth, float viewportHeight, float pixelWidth,
			Graphics2D g2) {
		AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
		at.rotate(rotation);
		at.translate(-cx, -cy);
		
		g2.setColor(new Color(0x00000000));
		g2.setBackground(new Color(0x00000000));
		//g2.setRenderingHint(, hintValue)
		AffineTransform st = g2.getTransform();
		g2.translate(-px, -py);
		g2.drawImage(bi, at, null);
		g2.setTransform(st);
	}
	
	
}
