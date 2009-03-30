/*
 * GraphMagic, package for scientists working in graph theory.
 * Copyright (C) 2009 Dzmitry Lazerka dlazerka@dlazerka.name
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
 * Author: Dzmitry Lazerka dlazerka@dlazerka.name
 */

package name.dlazerka.gm.ui;

import name.dlazerka.gm.GraphMagicPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * @author Dzmitry Lazerka www.dlazerka.name
 */
public class PluginsTableModel extends AbstractTableModel {
	private static final Logger logger = LoggerFactory.getLogger(PluginsTableModel.class);

	private ArrayList<GraphMagicPlugin> pluginList = new ArrayList<GraphMagicPlugin>();

	public void addPlugin(GraphMagicPlugin plugin) {
		this.setValueAt(plugin, getRowCount(), 0);
	}

	@Override
	public int getRowCount() {
		return pluginList.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public GraphMagicPlugin getValueAt(int rowIndex, int columnIndex) {
		return pluginList.get(rowIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (!(aValue instanceof GraphMagicPlugin)) {
			throw new IllegalArgumentException("Cell value must be of type " + GraphMagicPlugin.class.getName());
		}
		GraphMagicPlugin plugin = (GraphMagicPlugin) aValue;
		if (pluginList.size() <= rowIndex) {
			pluginList.add(rowIndex, plugin);
			fireTableRowsInserted(rowIndex, rowIndex);
		}
		else {
			pluginList.set(rowIndex, plugin);
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

//	private class PluginRow extends JLabel {
//		private GraphMagicPlugin plugin;
//
//		public PluginRow(GraphMagicPlugin plugin) {
//			super(plugin.getName());
//			this.plugin = plugin;
//
//			setComponentPopupMenu(new PopupMenu());
//
//			addMouseListener(new MouseListener());
//		}
//
//
//		private class MouseListener extends MouseAdapter {
//		}
//	}
}
