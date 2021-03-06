/*
 * GraphMagic, package for scientists working in graph theory.
 * Copyright (C) 2010 Dzmitry Lazerka www.dlazerka.name
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Author: Dzmitry Lazerka www.dlazerka.name
 */
package name.dlazerka.gm.shell.store;

import java.awt.Color;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import name.dlazerka.gm.Edge;
import name.dlazerka.gm.Vertex;
import name.dlazerka.gm.basic.BasicEdge;
import name.dlazerka.gm.basic.BasicGraph;
import name.dlazerka.gm.basic.BasicVertex;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphmlStorerTest {
	private static final Logger logger = LoggerFactory.getLogger(GraphmlStorerTest.class);
	private static final URL xmlSource = GraphmlStorerTest.class.getResource("graphml.xml");
	private static final URL xmlSourceWithData = GraphmlStorerTest.class.getResource("graphml2.xml");
	private BasicGraph graph;

	@Before
	public void setUp() {
		graph = new BasicGraph(false);
		BasicVertex vertex1 = graph.createVertex();
		vertex1.getVisual().setColor(Color.RED);
		BasicVertex vertex2 = graph.createVertex();
		vertex2.getVisual().setColor(Color.WHITE);
		BasicVertex vertex3 = graph.createVertex();
		vertex3.getVisual().setColor(Color.RED);
		BasicEdge edge1 = graph.createEdge(vertex1, vertex2);
		edge1.getVisual().setColor(Color.BLACK);
		BasicEdge edge2 = graph.createEdge(vertex1, vertex3, true);
		edge2.getVisual().setColor(Color.GREEN);
	}

	@Test
	public void testLoad() throws Exception {
		File file = new File(xmlSource.toURI());
		BasicGraph actual = GraphmlStorer.load(file);
		Assert.assertEquals(graph, actual);

		for (Vertex expectedVertex : graph.getVertexSet()) {
			Vertex actualVertex = actual.getVertex(expectedVertex.getId());
			Assert.assertEquals(expectedVertex.getMark(), actualVertex.getMark());
			Assert.assertEquals(expectedVertex.getVisual(), actualVertex.getVisual());
		}
		for (Edge expectedEdge : graph.getEdgeSet()) {
			Edge actualEdge = actual.getEdge(expectedEdge.getSource(), expectedEdge.getTarget());
			Assert.assertEquals(expectedEdge.getMark(), actualEdge.getMark());
			Assert.assertEquals(expectedEdge.getVisual(), actualEdge.getVisual());
		}
	}

	@Test
	public void testLoadWithData() throws Exception {
		BasicGraph expected = new BasicGraph();
		BasicVertex vertex0 = expected.createVertex("0");
		BasicVertex vertex1 = expected.createVertex("1");
		BasicVertex vertex2 = expected.createVertex("2");
		BasicVertex vertex3 = expected.createVertex("3");
		BasicVertex vertex4 = expected.createVertex("4");
		BasicVertex vertex5 = expected.createVertex("5");
		expected.createEdge(vertex0, vertex2);
		expected.createEdge(vertex0, vertex1);
		expected.createEdge(vertex1, vertex3);
		expected.createEdge(vertex3, vertex2);
		expected.createEdge(vertex2, vertex4);
		expected.createEdge(vertex3, vertex5);
		expected.createEdge(vertex5, vertex4);

		File file = new File(xmlSourceWithData.toURI());
		BasicGraph actual = GraphmlStorer.load(file);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSave() throws Exception {
		StringWriter writer = new StringWriter();
		GraphmlStorer.save(graph, writer);

		String string = writer.toString();

		String expected;
		InputStream in = xmlSource.openStream();
		try {
			expected = IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
		Assert.assertEquals(expected, string);
	}
}
