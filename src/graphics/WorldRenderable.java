package graphics;

import java.awt.Graphics2D;

public interface WorldRenderable {
	/* Paint youself at your normal coords (in cartesian space).
	 * The view center and size are provided so LOD can be applied.
	 */
	public void paint(float viewCenterX, float viewCenterY,
			float viewportWidth, float viewportHeight, float pixelWidth, Graphics2D g2);
}
