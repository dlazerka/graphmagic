package name.dlazerka.gc.bean;


import name.dlazerka.gc.util.LinkedSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Lazerka www.dlazerka.name
 */
public class Graph {
	private static final Logger logger = LoggerFactory.getLogger(Graph.class);

	private final Set<Vertex> vertexSet = new LinkedSet<Vertex>();
	private final Set<Edge> edgeSet = new LinkedSet<Edge>();
	private final List<GraphChangeListener> changeListeners = new LinkedList<GraphChangeListener>();

	public Graph() {
		create4();
	}

	public boolean addChangeListener(GraphChangeListener listener) {
		return changeListeners.add(listener);
	}

	private void create1() {
		Vertex vertex1 = new Vertex(1);

		add(vertex1);
	}

	private void create2() {
		Vertex vertex1 = new Vertex(1);
		Vertex vertex2 = new Vertex(2);

		add(vertex1);
		add(vertex2);

		Edge edge1 = new Edge(vertex1, vertex2);
		add(edge1);
	}

	private void create4() {
		Vertex vertex1 = new Vertex(1);
		Vertex vertex2 = new Vertex(2);
		Vertex vertex3 = new Vertex(3);
		Vertex vertex4 = new Vertex(4);

		add(vertex1);
		add(vertex2);
		add(vertex3);
		add(vertex4);

		Edge edge1 = new Edge(vertex1, vertex2);
		Edge edge2 = new Edge(vertex2, vertex3);
		Edge edge3 = new Edge(vertex3, vertex1);
		Edge edge4 = new Edge(vertex3, vertex4);
		Edge edge5 = new Edge(vertex4, vertex1);

		add(edge1);
		add(edge2);
		add(edge3);
		add(edge4);
		add(edge5);
	}

	public Set<Vertex> getVertexSet() {
		return vertexSet;
	}

	public Set<Edge> getEdgeSet() {
		return edgeSet;
	}
	public Vertex add(Vertex vertex) {
		logger.trace("{}", vertex);

		vertexSet.add(vertex);

		for (GraphChangeListener listener : changeListeners) {
			listener.vertexAdded(vertex);
		}

		return vertex;
	}

	/**
	 * Firstly removes adjacent edges one by one, then removes the vertex notifying {@link #changeListeners}
	 * @param vertex vertex to remove
	 */
	public void remove(Vertex vertex) {
		for (Edge edge : vertex.getAdjacentEdgeSet()) {
			remove(edge);
		}

		vertexSet.remove(vertex);

		for (GraphChangeListener changeListener : changeListeners) {
			changeListener.vertexDeleted(vertex);
		}
	}

	public void add(Edge edge) {
		logger.trace("{}", edge);

		edgeSet.add(edge);

		for (GraphChangeListener listener : changeListeners) {
			listener.edgeAdded(edge);
		}
	}

	public void remove(Edge edge) {
		edgeSet.remove(edge);

		for (GraphChangeListener changeListener : changeListeners) {
			changeListener.edgeDeleted(edge);
		}
	}

	public Vertex addVertex() {
		int max = 0;
		for (Vertex vertex : vertexSet) {
			if (vertex.getNumber() > max) {
				max = vertex.getNumber();
			}
		}
		Vertex vertex = new Vertex(max + 1);

		return add(vertex);
	}
}
