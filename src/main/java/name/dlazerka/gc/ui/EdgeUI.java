package name.dlazerka.gc.ui;

import name.dlazerka.gc.bean.Edge;

import java.awt.*;

/**
 * @author Dzmitry Lazerka
 */
public class EdgeUI {
	private static final Color COLOR_ARC = new Color(0, 0, 0);
	private static final Stroke STROKE_ARC = new BasicStroke(2.0f);

	private final Edge edge;
	private final VertexUI headUI;
	private final VertexUI tailUI;

	public EdgeUI(Edge edge, VertexUI headUI, VertexUI tailUI) {
		this.edge = edge;
		this.headUI = headUI;
		this.tailUI = tailUI;
	}

	public  void draw(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();

		g2.setColor(COLOR_ARC);
		g2.setStroke(STROKE_ARC);
		g2.drawLine(headUI.getCenter().x, headUI.getCenter().y, tailUI.getCenter().x, tailUI.getCenter().y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EdgeUI)) return false;

		EdgeUI edgeUI = (EdgeUI) o;

		if (!edge.equals(edgeUI.edge)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return edge.hashCode();
	}
}
