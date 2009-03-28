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

import name.dlazerka.gm.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Dzmitry Lazerka www.dlazerka.name
 */
public class MainFrame extends JFrame {
	private final static Logger logger = LoggerFactory.getLogger(MainFrame.class);

	private JPanel contentPane = new JPanel();
	private GraphPanel graphPanel = new GraphPanel();
	private JTabbedPane controlsTabbedPane;
	private JTable pluginsTable;
	private JButton addPluginButton = new JButton(Main.getString("add.plugin"));

	public MainFrame() {
		setupUI();

		setContentPane(contentPane);
		setTitle(Main.getString("main.title"));

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		registerCommands();
	}

	private void registerCommands() {
		// call onClose() when cross is clicked
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					onClose();
				}
			}
		);

// call onClose() on ESCAPE
		contentPane.registerKeyboardAction(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onClose();
				}
			}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
		);

		addPluginButton.addActionListener(new AddPluginActionListener(MainFrame.this));
	}

	private void onClose() {
		dispose();
	}


	private void setupUI() {
		contentPane.setLayout(new GridBagLayout());
		contentPane.setBackground(new Color(-986896));
		contentPane.setEnabled(true);
		contentPane.setInheritsPopupMenu(false);
		contentPane.setRequestFocusEnabled(true);
		contentPane.setToolTipText("");
//		contentPane.setPreferredSize(new Dimension(800, 600));

		final JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(Main.getString("file"));
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem loadPluginMenuItem = new JMenuItem(Main.getString("load.plugin"));
		loadPluginMenuItem.addActionListener(new AddPluginActionListener(MainFrame.this));
		loadPluginMenuItem.setMnemonic(KeyEvent.VK_P);
		fileMenu.add(loadPluginMenuItem);
		JMenuItem exitMenuItem = new JMenuItem(Main.getString("exit"));
		exitMenuItem.setMnemonic(KeyEvent.VK_X);
		exitMenuItem.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClose();
				}
			}
		);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		this.getRootPane().setJMenuBar(menuBar);


		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(200);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0);
		contentPane.add(
			splitPane
			, new GridBagConstraints(
				GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
				1, 1,
				1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0
//				1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
//				com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
//				null, null, null, 0, false
			)
		);

		final JPanel graphCardLayoutPanel = new JPanel(new CardLayout());
		splitPane.setRightComponent(graphCardLayoutPanel);
		graphPanel.setBackground(new Color(-1));
		graphCardLayoutPanel.add(
			graphPanel, "graph1"
//			graphPanel, new com.intellij.uiDesigner.core.GridConstraints(
//				0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
//				com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
//				null, new Dimension(800, 600), null, 0, false
//			)
		);
		controlsTabbedPane = new JTabbedPane();
		splitPane.setLeftComponent(controlsTabbedPane);
		final JPanel controlsPanel = new JPanel();
//		controlsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		controlsTabbedPane.addTab(Main.getString("controls"), controlsPanel);
		final JPanel pluginsPanel = new JPanel(new GridBagLayout());
//		pluginsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		controlsTabbedPane.addTab(Main.getString("plugins"), pluginsPanel);
		controlsTabbedPane.setSelectedIndex(1);

		pluginsPanel.add(
			addPluginButton, new GridBagConstraints(
				GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
				1, 1,
				1, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0),
				0, 0
			)
//			, new com.intellij.uiDesigner.core.GridConstraints(
//				0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
//				com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false
//			)
		);

		pluginsTable = new JTable();
		pluginsTable.addColumn(new TableColumn());
		pluginsPanel.add(
			pluginsTable, new GridBagConstraints(
				0, GridBagConstraints.RELATIVE,
				1, 1,
				1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0
			)
//			 , new com.intellij.uiDesigner.core.GridConstraints(
//				1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
//				com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
//				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null,
//				0, false
//			)
		);
	}

	public JComponent getRootComponent() {
		return contentPane;
	}
}
