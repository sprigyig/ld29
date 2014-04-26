package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BoatPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private float vpx, vpy;
	private float zoom;

	private Map<RenderLayer, Set<WorldRenderable>> renderStuff;
	private ArrayList<RenderQueueAction> actions;

	public BoatPanel() {
		renderStuff = new HashMap<RenderLayer, Set<WorldRenderable>>();
		for (RenderLayer rl : RenderLayer.values()) {
			renderStuff.put(rl, new HashSet<WorldRenderable>());
		}
		actions = new ArrayList<BoatPanel.RenderQueueAction>();

		setBackground(ConstantHacks.seaColor());
		setDoubleBuffered(true);
		new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}).start();
		addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {
				zoom += e.getWheelRotation() * 0.1f;
			}
		});
	}

	public void paint(Graphics g) {

		g.clearRect(0, 0, getWidth(), getHeight());

		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		float zoomFactor = (float) Math.pow(1.1, zoom);

		float worldWidth = getWidth() / zoomFactor;
		float worldHeight = getHeight() / zoomFactor;
		float pixelWidth = worldWidth / getWidth();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.translate(getWidth() / 2, getHeight() / 2);// center 0,0
		g2.scale(zoomFactor, -zoomFactor);// -y makes cartesian co-ords

		g2.translate(-vpx, -vpy);

		g2.setColor(new Color(255, 254, 255));
		g2.drawOval(-5, -5, 10, 10);

		synchronized (actions) {
			for (int i = 0; i < actions.size(); i++) {
				RenderQueueAction rqa = actions.get(i);
				if (rqa.add) {
					renderStuff.get(rqa.layer).add(rqa.wr);
				} else {
					renderStuff.get(rqa.layer).remove(rqa.wr);
				}
			}
			actions.clear();
		}

		for (RenderLayer rl : new RenderLayer[] { RenderLayer.UNDER,
				RenderLayer.ON, RenderLayer.OVERLAY }) {
			for (WorldRenderable wr : renderStuff.get(rl)) {
				wr.paint(vpx, vpy, worldWidth, worldHeight, pixelWidth, g2);
			}
		}
	}

	public void addRenderable(RenderLayer rl, WorldRenderable wr) {
		synchronized (actions) {
			actions.add(new RenderQueueAction(rl, true, wr));
		}
	}

	public void removeRenderable(RenderLayer rl, WorldRenderable wr) {
		synchronized (actions) {
			actions.add(new RenderQueueAction(rl, false, wr));
		}
	}

	private static class RenderQueueAction {
		RenderLayer layer;
		boolean add;
		WorldRenderable wr;

		RenderQueueAction(RenderLayer layer, boolean add, WorldRenderable wr) {
			this.layer = layer;
			this.add = add;
			this.wr = wr;
		}
	}
}
